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
	
	private String lastVarsName = "";
	
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
    	
    	report_info("type uso", null);
	}
    
    // ----------------------------------------------------------
    // *** VAR DECLARATIONS ***
    
    // izlaz
    public void visit(VarDeclaration varDecl) {
    	report_info("varDecl posetio", null);
    }
    
    public void visit(VarDeclarationList varDeclList){ 
    	report_info("varDeclList posetio", null);
	}
    
    public void visit(VarDeclarationListEnd varDecl) {
    	report_info("varDeclListEnd posetio", null);
    }
    
    public void visit(Array arr) {
    	report_info("Array posetio", null);
    }
    
    public void visit(NoArray noArr) {
    	report_info("NoArray posetio", null);
    }
    
    // ---------------------------------------------------------------
    // *** CONST DECLARATIONS ***
    
    
    
    // --------------------------------------------------------------
    // *** METHOD DECLARATIONS ***
    
    
    // ---------------------------------------------------------------
}
