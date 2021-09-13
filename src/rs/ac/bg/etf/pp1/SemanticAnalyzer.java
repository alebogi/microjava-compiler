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
	private boolean methodIsVoid = false;
		
	private LinkedList<MyVariable> varDeclarations = new LinkedList<MyVariable>();
	private LinkedList<MyVariable> constDeclarations = new LinkedList<MyVariable>();
	private LinkedList<MyVariable> methodArgs = new LinkedList<MyVariable>();
	
	private LinkedList<MyMethod> methods = new LinkedList<MyMethod>();
	
	private boolean isArray = false;
	private boolean isArrayUsed = false;
	
	private int lastConstIntVal;
	private char lastConstCharVal;
	private boolean lastConstBoolVal;
	private Struct lastTypeRight;
	private Type lastTypeLeft;
	
	private int firstConstIntVal;
	private char firstConstCharVal;
	private boolean firstConstBoolVal;
	private int constCnt = 0;
	
	private Scope globalScope;
	

	
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
	// =========================
	
		public boolean methodExists(String name) {
			boolean res = false;
			
			for(MyMethod m: methods) {
				if(m.getName().equals(name)) {
					res = true;
					return res;
				}
			}
			
			return res;
		}

	//==============================================================================
	   // Ulaz u program
    public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
		globalScope = Tab.currentScope;

		report_info("Pocetak obrade programa " + progName.getProgName() , progName);
	}

    // Kraj programa
    public void visit(Program program) {
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    	
    	if (!hasMain) {
			report_error("Semanticka greska - ne postoji metoda MAIN!", null);
		}
    	
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
    	//Obj obj = Tab.find(varName);  	
    	Obj obj = Tab.currentScope.findSymbol(varName);
    	boolean exists = false;   	
		if (obj == null) { //Tab.noObj
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
				if(globalScope == Tab.currentScope)
					report_info("Deklarisan globalni niz " + var.getName(), varDecl);
				else
					report_info("Deklarisan lokalni niz " + var.getName(), varDecl);
			}else {		
	    		Obj varNode2 = Tab.insert(Obj.Var, var.getName(), varType.struct);
	    		if(globalScope == Tab.currentScope)
	    			report_info("Deklarisana globalna promenljiva " + var.getName(), null);
				else
					report_info("Deklarisana lokalna promenljiva " + var.getName(), null);
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
    	//Obj obj = Tab.find(varName);  	
    	Obj obj = Tab.currentScope.findSymbol(varName);
    	boolean exists = false;   	
		if (obj == null) { //Tab.noObj
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
					newVar.setCharVal(firstConstCharVal);
				}else if (constTypeLeft.struct == Tab.intType) {
					newVar.setIntVal(firstConstIntVal);
				}else if (constTypeLeft.struct == boolType) {
					newVar.setBoolVal(firstConstBoolVal);
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
				constNode.setAdr(var.getCharVal());
			}else if (constTypeLeft.struct == Tab.intType) {
				constNode.setAdr(var.getIntVal());
			}else if (constTypeLeft.struct == boolType) {
				if(var.isBoolVal())
					constNode.setAdr(1);
				else
					constNode.setAdr(0);
			}
    		
		}
		
		constDeclarations.clear();
		constCnt = 0;
    }
       
    public void visit(NumConst consts) {
    	report_info("NumConst posetio, vrednost=" + consts.getNumValue(), null);
    	
    	if(constCnt<1)
    		firstConstIntVal = consts.getNumValue();
    	else
    		lastConstIntVal = consts.getNumValue();
    	lastTypeRight = Tab.intType;
    	consts.struct = Tab.intType;
    	constCnt++;
    }
    
    public void visit(CharConst consts) {
    	report_info("CharConst posetio, vrednost=" + consts.getCharValue(), null);
    	
    	if(constCnt<1)
    		firstConstCharVal = consts.getCharValue();
    	else
    		lastConstCharVal = consts.getCharValue();
    	lastTypeRight = Tab.charType;
    	consts.struct = Tab.charType;
    	constCnt++;
    }

	public void visit(BoolConst consts) {
		report_info("BoolConst posetio, vrednost=" + consts.getBoolValue(), null);
		
		if(constCnt<1)
    		firstConstBoolVal = consts.getBoolValue();
    	else
    		lastConstBoolVal = consts.getBoolValue();
		lastTypeRight = boolType;
		consts.struct = boolType;
		constCnt++;
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
    
    // izlaz - root - NE
    public void visit(MethodDeclList mdl) {
    	report_info("MethodDeclList posetili", null);
    }
    
    // izlaz - root - DAAAAA
    public void visit(MethodsDeclarationList mdl) {
    	report_info("MethodsDeclarationList posetili", null);
    	
    	if (!methodIsVoid && !hasReturn) {
			report_error("Semanticka greska: metoda " + currentMethod.getName() + " treba da ima povratnu vrednost!", null);
		}
    	
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	
    	report_info("Zavrsetak obrade metode " + currentMethod.getName(), mdl);
    	hasReturn = false;
    	currentMethod = null;
    	
    	
    }
    
    // izlaz iz rekurzije 
    public void visit(NoMethodsDeclarationList mdl) {
    	report_info("NoMethodsDeclarationList posetili", null);
    }
    
    //izlaz - NE
    public void visit(MethodDecl md) {
    	report_info("MethodsDecl posetili", null);
    }
    
    // IZLAZ KAD IMAMO FORM PARS
    public void visit(MethodDeclarationFormPars mdfp) {
    	report_info("MethodDeclarationFormPars posetili", null);
    }
    
    // IZLAZ KAD NEMAMO FORM PARS
    public void visit(MethodDeclaration md) {
    	report_info("MethodDeclaration posetili", null);
    }
    
    public void visit(MethodWithType mt) {
    	report_info("MethodWithType posetili", null);
    	
    	String name = mt.getMethodName();
    	Type type = mt.getType();	
    	currentMethod = Tab.insert(Obj.Meth, name, type.struct);
    	mt.obj = currentMethod;
    	Tab.openScope();
		report_info("Obradjuje se funkcija " + name, mt);
		methodIsVoid = false;
		if (name.equals("main"))
			report_error("Greska - main metoda mora da bude tipa void ", mt);
		else
			report_info("Obradjuje se metoda: " + name + " , koja ima povratnu vrednost: " + type.struct.toString(), mt);
    	
    }
    
    public void visit(MethodVoid mv) {
    	report_info("MethodVoid posetili", null);
    	
    	String name = mv.getMethodName();
    	currentMethod = Tab.insert(Obj.Meth, name, Tab.noType);
    	mv.obj = currentMethod;
    	Tab.openScope();
		report_info("Obradjuje se void metoda: " + name, mv);
		methodIsVoid = true;
		if(name.equals("main")) {
			hasMain = true;
		}
    }
    
    public void visit(FormParams fp) {
    	report_info("FormParams posetili", null);
    	
    	String varName = fp.getFormParsName();
    	Type varType = fp.getType();
    	//Obj obj = Tab.find(varName);  	
    	Obj obj = Tab.currentScope.findSymbol(varName);  
    	boolean exists = false;   		
		if (obj == null) { //Tab.noObj
			for(MyVariable var: methodArgs) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska: Argument s nazivom '" + varName + "' vec postoji", fp);
					exists = true;
					break;
				}
			}
			
			if(!exists) {
				MyVariable newVar = new MyVariable(varName, (Object)fp, varType.struct);
				newVar.setConstant(false);
				if(!isArrayUsed) {
					newVar.setArr(isArray);
					isArrayUsed = true;
				}			
				methodArgs.addFirst(newVar); 	
			}
		}else { 			
			report_error("Semanticka greska: Argument s nazivom '" + varName + "' vec postoji", fp);
		}
				
		//dodavanje svih vars u tabelu simbola
		for(MyVariable var: methodArgs) {
			String name = var.getName();
			if(var.isArr()) {
				Obj varNode = Tab.insert(Obj.Var, var.getName(), new Struct(Struct.Array, varType.struct));
				int lvl = currentMethod.getLevel(); //lvl = broj form.argumenata
				varNode.setFpPos(lvl); //fpPos=redni broj argumenta == trenutni broj form.argumenata
				currentMethod.setLevel(lvl + 1); //povecavamo trenutni broj form.argumenata
	    		report_info("Deklarisan niz " + var.getName() + " kao argument metode.", null);
			}else {
				report_info("Deklarisana promenljiva " + var.getName() + " kao argument metode.", null);
	    		Obj varNode2 = Tab.insert(Obj.Var, var.getName(), varType.struct);
	    		int lvl2 = currentMethod.getLevel(); //lvl = broj form.argumenata
				varNode2.setFpPos(lvl2); //fpPos=redni broj argumenta == trenutni broj form.argumenata
				currentMethod.setLevel(lvl2 + 1); //povecavamo trenutni broj form.argumenata
			}
		}
		
		methodArgs.clear();
    }
    
    public void visit(FormParsList fpl) {
    	report_info("FormParsList posetili", null);
    }
    
    public void visit(FormParamsListEnd fple) {
    	report_info("FormParsListEnd posetili", null);
    	
    	String varName = fple.getFpListName();
    	Type varType = fple.getType();
    	//Obj obj = Tab.find(varName);  
    	Obj obj = Tab.currentScope.findSymbol(varName);
    	boolean exists = false;   	
		if (obj == null) { //Tab.noObj
			for(MyVariable var: methodArgs) {
				if(var.getName().equals(varName)) {
					report_error("Semanticka greska: Argument s nazivom '" + varName + "' vec postoji", fple);
					exists = true;
					break;
				}
			}
			
			if(!exists) {
				MyVariable newVar = new MyVariable(varName, (Object)fple, varType.struct);
				newVar.setConstant(false);
				if(!isArrayUsed) {
					newVar.setArr(isArray);
					isArrayUsed = true;
				}			
				methodArgs.add(newVar); 	
			}
		}else { 			
			report_error("Semanticka greska: Argument s nazivom '" + varName + "' vec postoji", fple);
		}
		   	

    }
    
    public void visit(ListVarDeclaration lvd) {
    	report_info("ListVarDeclaration posetili", null);
    }
    
    // ---------------------------------------------------------------
    // *** STATEMENTS ***
    
    // ---------DESIGNATOR
    public void visit(FactorDesign fd) { //??	ZASTO OVDE ULAZI
    	report_info("FactorDesign posetili", null);
    	fd.struct = fd.getDesignator().obj.getType();
    }
   
    public void visit(DesignatorIdent d) {
    	report_info("Designator posetili", null);
    	
    	String name = d.getDsgnName();
    	Obj obj = Tab.find(name);
    	if(obj == Tab.noObj){
			report_error("Semanticka greska na liniji " + d.getLine()+ ": ime "+ name+" nije deklarisano! ", null);
    	}
    	d.obj = obj;
    }
    
    public void visit(DesignatorArr d) {
    	report_info("DesignatorArr posetili", null);
    	
    	String name = d.getDsgnName();
    	Obj node = Tab.find(name);
    	if(node == Tab.noObj){
			report_error("Semanticka greska na liniji " + d.getLine()+ ": ime "+ name+" nije deklarisano! ", null);
    	}
    	
    	if(node.getType().getKind() != Struct.Array) {
    		report_error("Semanticka greska na liniji " + d.getLine()+ ": pokusaj da se pristupi elementu niza, a u pitanju je promenljiva koja nije niz ", null);
    	}
    	
    	if(d.getExpr().struct != Tab.intType) {
    		report_error("Semanticka greska na liniji " + d.getLine()+ ": indeks niza mora da bude int! ", null);
    	}
    	
    	d.obj = new Obj(Obj.Elem, name, node.getType().getElemType());	
    	
    	if(node.getFpPos() == 1) {
			d.obj.setFpPos(1);
		} else {
			d.obj.setFpPos(0);
		}
			
    }
    
    
    //--
	public void visit(DesgnStmtAsgnOp d) {
		report_info("DesgnStmtAsgnOp posetili", null);
		
		Struct leftStrType = d.getDesignator().obj.getType();
		Struct rightStrType = d.getExpr().struct;
		if(!rightStrType.assignableTo(leftStrType)) {
			report_error("Semanticka greska: nekompatibilni tipovi za dodelu vrednosti", d);
		}
	}
	
	public void visit(DesgnStmtIncr d) {
		report_info("DesgnStmtIncr posetili", null);
		
		Struct rightStrType = d.getDesignator().obj.getType();
		if (rightStrType != Tab.intType  && d.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Semanticka greska - tip za inkrement moze biti jedino int", d);
		}
	}

	public void visit(DesgnStmtDecr d) {
		report_info("DesgnStmtDecr posetili", null);
		
		Struct rightStrType = d.getDesignator().obj.getType();
		if (rightStrType != Tab.intType   && d.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Semanticka greska - tip za dekrement moze biti jedino int", d);
		}
	}
	
	//-------- IF ELSE
	
	//IF L_PAREN Condition R_PAREN StmtMatched ELSE StmtMatched  						!!!!!!!!!!!!!1
	public void visit(StmtIfElse s) { 
		report_info("StmtIfElse posetili", null);
		
	/*	if(s.getCondition().struct != boolType) {
			report_error("Semanticka greska - Condition mora biti tipa bool", s);
		}*/
		
	}
	
	//IF L_PAREN Condition R_PAREN Statement			 						!!!!!!!!!!!!!1
	public void visit(StmtUnmatchedIf s) {
		report_info("StmtUnmatchedIf posetili", null);
		
	/*	if(s.getCondition().struct != boolType) {
			report_error("Semanticka greska - Condition mora biti tipa bool", s);
		}*/
	}
	
	//IF  L_PAREN Condition R_PAREN StmtMatched ELSE StmtUnmatched		 						!!!!!!!!!!!!!1
	public void visit(StmtUnmatchedIfElse s) {
		report_info("StmtUnmatchedIfElse posetili", null);
		
	/*	if(s.getCondition().struct != boolType) {
			report_error("Semanticka greska - Condition mora biti tipa bool", s);
		}*/
	}
	
	
	// ---- CONDITION
	public void visit(Cond c) {
		report_info("Cond posetili", null);
		c.struct = c.getCondTerm().struct;
	}
	
	public void visit(CondOr c) {
		report_info("CondOr posetili", null);
		c.struct = c.getCondTerm().struct;
	}
	
	public void visit(ConditionTerm c) {
		report_info("ConditionTerm posetili", null);
		c.struct = c.getCondFact().struct;
	}

	public void visit(ConditionTermAnd c) {
		report_info("ConditionTermAnd posetili", null);
		c.struct =c.getCondFact().struct;
	}

	public void visit(ConditionFact c) {
		report_info("ConditionFact posetili", null);
		c.struct = c.getExpr().struct;
	}
	
	// --- EXPR
	public void visit(Expression e) {
		report_info("Expression posetili", null);
		e.struct = e.getTerm().struct;
	}
	
	public void visit(RelopExprExist e) {
		report_info("RelopExprExist posetili", null);
		e.struct = e.getExpr().struct;
	}
	
	// ---- TERM
	public void visit(Term t) {
		report_info("Term posetili", null);
		t.struct = t.getFactor().struct;
		report_info("Term govno ----> " + t.struct, null);
	}
	
	
	// *** FACTOR *** propagiramo ovima gore 
    public void visit(FactorNumConst f) {
    	report_info("FactorNumConst posetili", null);
    	f.struct = Tab.intType;
    }
    
    public void visit(FactorCharConst f) {
    	report_info("FactorCharConst posetili", null);
    	f.struct = Tab.charType;
    }

	public void visit(FactorBoolConst f) {
		report_info("FactorBoolConst posetili", null);
		f.struct = boolType;
	}
	
	public void visit(FactorNew f) {
	//	za klase
	}
	
	public void visit(FactorNewArr f) {
		report_info("FactorNewArr posetili", null);
		if (f.getExpr().struct != Tab.intType) {
			report_error("Semanticka greska na liniji " + f.getLine() + ": pri alociranju niza treba proslediti int", f);
			f.struct = new Struct(Struct.Array, f.getType().struct); 
		} else {
			f.struct = new Struct(Struct.Array, f.getType().struct);
		}
    }
	
	
	public void visit(FactorExpr f) {
		report_info("FactorExpr posetili", null);
		f.struct = f.getExpr().struct;
	}


	//----- MULOP ADDOP
	public void visit(ListAddopTerm l) {
		report_info("ListAddopTerm posetili", null);
		l.struct = l.getTerm().struct;
		if (l.getTerm().struct != Tab.intType) {
			report_error("Greska - Samo int tipovi mogu da se sabiraju ---> " + l.struct, l);
		}
	}
	
	public void visit(MullopFactList m) {
		report_info("MullopFactList posetili", null);
		m.struct = m.getFactor().struct;
		if (m.getFactor().struct != Tab.intType) {
			report_error("Greska  - Samo int tipovi mogu da se mnoze", m);
		}
	}


	// ------ READ
    public void visit(StmtRead sr) {
    	report_info("StmtRead posetili", null);
    	
    	Struct type = sr.getDesignator().obj.getType();
    	if (type!=Tab.charType && type!=Tab.intType && type!=boolType) {
			report_error("Semanticka greska: read na liniji " + sr.getLine() + " : " + ": dozvoljeni tipovi su int, char, bool! ", null);
    	}
    }
    
    
    // ---- PRINT
    public void visit(StmtPrintNumConst sp) {
    	report_info("StmtPrintNumConst posetili", null);
    	
    	Struct type = sp.getExpr().struct;
		if (type!=Tab.charType && type!=Tab.intType && type!=boolType) {
			report_error("Semanticka greska: print na liniji " + sp.getLine() + " : " + ": dozvoljeni tipovi su int, char, bool! ", null);
		} 
    }
    
    public void visit(StmtPrint sp) {
    	report_info("StmtPrint posetili", null);
    	
    	Struct type = sp.getExpr().struct;
		if (type!=Tab.charType && type!=Tab.intType && type!=boolType) {
			report_error("Semanticka greska: print na liniji " + sp.getLine() + " : " + ": dozvoljeni tipovi su int, char, bool! ", null);
		}
    }
   
    

    // *** RETURNS ***
    public void visit(StmtRet sr) {
    	report_info("StmtRet posetili", null);
    	
    	hasReturn = true;
    }
    
    // return expr
    public void visit(ExpreExists e) {
    	report_info("ExpreExists posetili", null);
    	Struct curMethTypeStruct = currentMethod.getType();
		if (!curMethTypeStruct.compatibleWith(e.getExpr().struct)) {
			report_error("Semanticka greska na liniji " + e.getLine()+ ": tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
					+ currentMethod.getName(), null);
		}
    }
    
    // return
    public void visit(NoExprExists e) {
    	report_info("NoExprExists posetili", null);
    	
    }
    
    // -----
    
    


    
    // -----------------------------------------------------------------
    


}
