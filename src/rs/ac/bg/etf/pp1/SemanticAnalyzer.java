package rs.ac.bg.etf.pp1;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor{
	
	private static Struct boolType = Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool)).getType();
	
	private boolean errorDetected = false;
	
	private int nVars;
	private Obj currentMethod = null;
	
	private boolean hasMain = false;
	private boolean hasReturn = false;
		
	private LinkedList<MyVariable> varDeclarations = new LinkedList<MyVariable>();
	private LinkedList<MyVariable> constDeclarations = new LinkedList<MyVariable>();
	
	private boolean isArray = false;
	private boolean isArrayUsed = false;
	
	private int lastConstIntVal;
	private char lastConstCharVal;
	private boolean lastConstBoolVal;
	private Struct lastTypeRight;
	private Type lastTypeLeft;
	
	Logger log = Logger.getLogger(getClass());
	
	//==========================================================================
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	// ----------------------------------------------
	
	public boolean passed(){
    	return !errorDetected;
    }

	//==============================================================================
	   // Ulaz u program
    public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
		
		report_info("Pocetak obrade programa " + progName.getProgName() , progName);
	}

    // Kraj programa
    public void visit(Program program) {
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    	
    	/*if (!hasMain) {
			report_error("Semanticka greska - ne postoji metoda MAIN!", null);
		}*/
    	
		report_info("Kraj semanticke obrade programa " + program.getProgName().getProgName(), null);
	}
    
    // ----------------------------------------------------------
    
    // Type - Provera tipa
    public void visit(Type type) {
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj) {
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola.", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()) {
    			type.struct = typeNode.getType();
    		}else {
    			report_error("Greska: Ime" + type.getTypeName() + " ne predstavlja tip.", type);
    			type.struct = Tab.noType;
    		}
    	}
    	
    	report_info("type posetio", null);
    	lastTypeLeft = type;
	}
    
    // =======================================================================================
    // *** VAR DECLARATIONS ***
    
    // izlaz - root
    public void visit(VarDeclaration varDecl) {
    	report_info("varDecl posetio", null);
    	
    	String varName = varDecl.getVarName();
    	Type varType = varDecl.getType();
    	Obj obj = Tab.find(varName);  	
    	boolean exists = false;   	
		if (obj == Tab.noObj) {
			for(MyVariable var: varDeclarations) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska: Promenljiva '" + varDecl.getVarName() + "' vec postoji", varDecl);
					exists = true;
					break;
				}
			}
			
			if(!exists) {
				MyVariable newVar = new MyVariable(varName, (Object)varDecl, varType.struct);
				newVar.setConstant(false);
				if(!isArrayUsed) {
					newVar.setArr(isArray);
					isArrayUsed = true;
				}			
				varDeclarations.addFirst(newVar); 	
			}
		}else { 			
			report_error("Semanticka greska: Promenljiva '" + varDecl.getVarName() + "' vec postoji", varDecl);
		}
		
		//dodavanje svih vars u tabelu simbola
		for(MyVariable var: varDeclarations) {
			String name = var.getName();
			if(var.isArr()) {
				Obj varNode = Tab.insert(Obj.Var, var.getName(), new Struct(Struct.Array, varType.struct));
	    		report_info("Deklarisan niz " + var.getName(), varDecl);
			}else {
				report_info("Deklarisana promenljiva " + var.getName(), null);
	    		Obj obj2 = Tab.insert(Obj.Var, var.getName(), varType.struct);
			}
		}
		
		varDeclarations.clear();
    }
    
    public void visit(VarDeclarationList varDeclList){ 
    	report_info("varDeclList posetio", null);
	}
    
    public void visit(NoVarDeclarationList noVarDeclList){ 
    	report_info("NoVarDeclList posetio, kraj rekurzije", null);	
	}
    
    public void visit(VarDeclarationListEnd varDecl) {
    	report_info("varDeclListEnd posetio", null);
    	
    	String varName = varDecl.getVarDeclListEndName();
    	Obj obj = Tab.find(varName);  	
    	boolean exists = false;   	
		if (obj == Tab.noObj) {
			for(MyVariable var: varDeclarations) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska: Promenljiva '" + varName + "' vec postoji", varDecl);
					exists = true;
					break;
				}
			}
			
			if(!exists) {
				MyVariable newVar = new MyVariable(varName, (Object)varDecl);
				newVar.setConstant(false);
				if(!isArrayUsed) {
					newVar.setArr(isArray);
					isArrayUsed = true;
				}	
				varDeclarations.add(newVar); 			
			}
		}else { 			
			report_error("Semanticka greska: Promenljiva '" + varName + "' vec postoji", varDecl);
		}
    }
    
    // --------------------------------------------------------------------------------------
    // *** JESTE ARRAY ILI NIJE ***
    public void visit(Array arr) {
    	report_info("Array posetio", null);
    	
    	isArray = true;
    	isArrayUsed = false;
    }
    
    public void visit(NoArray noArr) {
    	report_info("NoArray posetio", null);
    	
    	isArray = false;
    	isArrayUsed = false;
    }
    
    // ---------------------------------------------------------------
    // *** CONST DECLARATIONS ***
    
    // izlaz - root
    public void visit(ConstDeclaration constDecl) {
    	report_info("ConstDeclaration posetio", null);
    	
    	String constName = constDecl.getConstName();
    	Type constTypeLeft = constDecl.getType();
    	Obj obj = Tab.find(constName);
    	boolean exists = false;   	
		if (obj == Tab.noObj) {
			for(MyVariable var: constDeclarations) {
				if(var.getName().equals(constName)) {
					report_error("Semanticka greska: Konstanta '" + constName + "' vec postoji", constDecl);
					exists = true;
					break;
				}
			}
			
			if(!exists) {
				MyVariable newVar = new MyVariable(constName, (Object)constName, constTypeLeft.struct, false);
				newVar.setConstant(true);	
				if(lastTypeRight != constTypeLeft.struct) {
					report_error("Semanticka greska: Tipovi se ne poklapaju.", null);
				}
				else if(constTypeLeft.struct == Tab.charType) {
					newVar.setCharVal(lastConstCharVal);
				}else if (constTypeLeft.struct == Tab.intType) {
					newVar.setIntVal(lastConstIntVal);
				}else if (constTypeLeft.struct == boolType) {
					newVar.setBoolVal(lastConstBoolVal);
				}
				constDeclarations.addFirst(newVar); 	
			}
		}else { 			
			report_error("Semanticka greska: Konstanta '" + constName + "' vec postoji", constDecl);
		}
		
		//ubacivanje u tabelu simbola
		for(MyVariable var: constDeclarations) {
			String name = var.getName();
			report_info("Deklarisana konstanta " + var.getName(), null);
    		Obj constNode = Tab.insert(Obj.Con, name, constTypeLeft.struct);
    		if(constTypeLeft.struct == Tab.charType) {
				constNode.setAdr(lastConstCharVal);
			}else if (constTypeLeft.struct == Tab.intType) {
				constNode.setAdr(lastConstIntVal);
			}else if (constTypeLeft.struct == boolType) {
				if(lastConstBoolVal)
					constNode.setAdr(1);
				else
					constNode.setAdr(0);
			}
    		
		}
		
		constDeclarations.clear();
		
    }
       
    public void visit(NumConst consts) {
    	report_info("NumConst posetio, vrednost=" + consts.getNumValue(), null);
    	
    	lastConstIntVal = consts.getNumValue();
    	lastTypeRight = Tab.intType;
    	consts.struct = Tab.intType;
    }
    
    public void visit(CharConst consts) {
    	report_info("CharConst posetio, vrednost=" + consts.getCharValue(), null);
    	
    	lastConstCharVal = consts.getCharValue();
    	lastTypeRight = Tab.charType;
    	consts.struct = Tab.charType;
    }

	public void visit(BoolConst consts) {
		report_info("BoolConst posetio, vrednost=" + consts.getBoolValue(), null);
		
		lastConstBoolVal = consts.getBoolValue();
		lastTypeRight = boolType;
		consts.struct = boolType;
	}
    
    public void visit(ConstantsList constsLists) {
    	report_info("ConstantsList posetio", null);
    }
    
    public void visit(NoConstantsList noConstsLists) {
    	report_info("NoConstantsList posetio, kraj rekurzije", null);
    }
    
    public void visit(ConstantsListEnd constsListEnd) {
    	report_info("ConstantsListEnd posetio", null);
    	
    	String constName = constsListEnd.getConstEndName();
    	Obj obj = Tab.find(constName);
    	boolean exists = false;   	
		if (obj == Tab.noObj) {
			for(MyVariable var: constDeclarations) {
				if(var.getName().equals(constName)) {
					report_error("Semanticka greska: Konstanta '" + constName + "' vec postoji", constsListEnd);
					exists = true;
					break;
				}
			}
			
			if(!exists) {
				MyVariable newVar = new MyVariable(constName, (Object)constName, lastTypeLeft.struct, false);
				newVar.setConstant(true);	
				if(lastTypeRight != lastTypeLeft.struct) {
					report_error("Semanticka greska: Tipovi se ne poklapaju.", null);
					return;
				}
				else if(lastTypeLeft.struct == Tab.charType) {
					newVar.setCharVal(lastConstCharVal);
				}else if (lastTypeLeft.struct == Tab.intType) {
					newVar.setIntVal(lastConstIntVal);
				}else if (lastTypeLeft.struct == boolType) {
					newVar.setBoolVal(lastConstBoolVal);
				}
				constDeclarations.add(newVar); 	
			}
		}else { 			
			report_error("Semanticka greska: Konstanta '" + constName + "' vec postoji", constsListEnd);
		}
    }
    
    // --------------------------------------------------------------
    // *** METHOD DECLARATIONS ***
    
    // izlaz - root
    public void visit(MethodDeclList mdl) {
    	
    }
    
    // izlaz - root
    public void visit(MethodsDeclarationList mdl) {
    	
    }
    
    // izlaz iz rekurzije 
    public void visit(NoMethodsDeclarationList mdl) {
    	
    }
    
    public void visit(MethodDecl md) {
    	
    }
    
    public void visit(MethodDeclarationFormPars mdfp) {
    	
    }
    
    public void visit(MethodDeclaration md) {
    	
    }
    
    public void visit(MethodWithType mt) {
    	
    }
    
    public void visit(MethodVoid mv) {
    	
    }
    
    public void visit(FormPars fp) {
    	
    }
    
    public void visit(FormParsList fpl) {
    	
    }
    
    public void visit(FormParsListEnd fple) {
    	
    }
    
    // ---------------------------------------------------------------
}
