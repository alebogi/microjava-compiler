package rs.ac.bg.etf.pp1;



import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPC;
	private Obj currentMethod = null;

//============================
	Logger log = Logger.getLogger(getClass());
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	
	}
	
	
	//===========
	
	private LinkedList<Integer> addopStack = new LinkedList<Integer>();
	private LinkedList<Integer> mulopStack = new LinkedList<Integer>();
	private LinkedList<Integer> relopStack = new LinkedList<Integer>();
	private int currentRelop = -1;

	
	//za skokove iz uslova u if-else naredbi
	private LinkedList<LinkedList<MyCond>> ifCondsStack = new LinkedList<LinkedList<MyCond>>(); 
	//za bezuslovne skokove sa kraja if-body na mesto posle else (preskakanje else grane)
	private LinkedList<Integer> skipElseStack = new LinkedList<Integer>(); 
	// brojac and-ova, do prve pojave or-a
	private int andNumCnt = 0;
	private int orNumCnt = 0;
	private int skipElse_AdrToFix = -1;
	private int skipToElse_AdrToFix = 0;
	//private boolean skipToElse = false;
	
	//----------
	
	private LinkedList<Integer> listaAdresaZaFix_skipElse = new LinkedList<Integer>();

	
	private LinkedList<MyCond> listaAdresaZaFix_andIzraz = new LinkedList<MyCond>();
	private LinkedList<MyCond> listaAdresaZaFix_skociNaThen = new LinkedList<MyCond>();
	private LinkedList<MyCond> listaAdresaZaFix_skociNaSledeciUslov = new LinkedList<MyCond>();
	
	
	
	private LinkedList<LinkedList<Integer>> stackListi_skipElse = new LinkedList<LinkedList<Integer>>();
	private LinkedList<LinkedList<MyCond>> stackListi_andIzraz = new LinkedList<LinkedList<MyCond>>();
	private LinkedList<LinkedList<MyCond>> stackListi_skociNaThen = new LinkedList<LinkedList<MyCond>>();
	private LinkedList<LinkedList<MyCond>> stackListi_skociNaSledeciUslov = new LinkedList<LinkedList<MyCond>>();
	
	private int ifCnt = 0;
	//-----
	public int getMainPC() {
		return mainPC;
	}
	
	
	public Obj findVarInTab(String name) {
		Obj res = null;
		
		// Prvo gledamo lokalne
		if(currentMethod != null) {
			Collection<Obj> currMethLocalSymbols = currentMethod.getLocalSymbols();
			for (Obj currObj: currMethLocalSymbols) {		
				if (currObj.getName().equals(name)) {
					res = currObj;
					return res;
				}
			}
		}
		
		// Ako nema u lokalnim gledamo globalne
		Collection<Obj> globalSymbols = SemanticAnalyzer.globalScope.values();
		for (Obj currObj : globalSymbols) {				
			if (currObj.getKind() != Obj.Meth && currObj.getName().equals(name)) {
				res = currObj;
				return res;
			}
		}
		
		// gledamo universe
		res = Tab.find(name);
		if(res == Tab.noObj) {
			res = null;
		}		
			
		return res;
	}
	
	//---------------------------------
	// *** STATEMENT ***
	
	// PRINT NA SIRINI 
	public void visit(StmtPrintNumConst p) {
		Struct type = p.getExpr().struct;
		int width = p.getNumVal();
		if(width <= 0)
			width = 1;
		if(type == Tab.intType){
			Code.loadConst(width);
			Code.put(Code.print);
		}else{
			Code.loadConst(width);
			Code.put(Code.bprint);
		}
	}
	
	// PRINT
	public void visit(StmtPrint p) {
		
		Struct type = p.getExpr().struct;
		if(type == Tab.intType || type == SemanticAnalyzer.boolType){
			Code.loadConst(5);
			Code.put(Code.print);
		}else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	// READ
	public void visit(StmtRead sr) {
		Struct type = sr.getDesignator().obj.getType();
		if(type == Tab.intType || type == SemanticAnalyzer.boolType){
			Code.put(Code.read);
		}else{
			Code.put(Code.bread);
		}
		Code.store(sr.getDesignator().obj);
    }
	
	//------------------------------------------
	// *** DESIGNATORI ***
	
	// stavljamo na stek KOME dodeljujemo vrednost
	public void visit(DesgnStmtAsgnOp d) {
		Code.store(d.getDesignator().obj);
		
	}
	
	public void visit(DesgnStmtIncr d) {
		if (d.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(d.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(d.getDesignator().obj);
	}
	
	public void visit(DesgnStmtDecr d) {
		if (d.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(d.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(d.getDesignator().obj);		
	}
	
	public void visit(DesignatorIdent di) {
		SyntaxNode parent = di.getParent();
		
		//if(DesgnStmtAsgnOp.class != parent.getClass() && StmtRead.class != parent.getClass()){
		// treba nam vrednost 
		if(FactorDesign.class == parent.getClass()) {
			Code.load(di.obj);
		}
		
		
	}
	
	// PRISTUP ELEMENTU NIZA 				
	public void visit(DesignatorArr d) {
		// na steku nam treba redom adresa pa indeks
		String name = d.getDsgnName();
		Obj objNode = findVarInTab(name);	
		
		// na steku je indeks niza
		if (objNode != null) {
			Code.load(objNode);		//stavljamo adresu niza na stek
									//izgled steka:
									//  index
									//	adr 	<-- sp			
			Code.put(Code.dup_x1); 
									// izgled steka:
									// adr - ovo je dup_x1 ubacio
									// index
									// adr 	<--- sp
			Code.put(Code.pop);		//sklanjamo adr jer je visak
									// izgled steka:
									// adr 
									// index
		}		
		
		// ako nam treba VREDNOST, a ne adresa
		if(FactorDesign.class == d.getParent().getClass()) {
			Code.load(d.obj);	// izgled steka:
									// adr 
									// index
		// aload -> sloni adr i index i postavi value
			// izgled steka:
			// adr[index]
		}
	}
	
	
	// ------------------------------------------
	// *** METODE ***
	
	//VOID METODA
	public void visit(MethodVoid mv) {
		if("main".equals(mv.getMethodName())){
			mainPC = Code.pc;
		}
		
		mv.obj.setAdr(Code.pc);

		Code.put(Code.enter);
		Code.put(mv.obj.getLevel());
		Code.put( mv.obj.getLocalSymbols().size());
		
		
		currentMethod = mv.obj;
    }
	
	// METODA SA POVRATIM TIPOM
	public void visit(MethodWithType mt) {
		mt.obj.setAdr(Code.pc);

		Code.put(Code.enter);
		Code.put(mt.obj.getLevel());
		Code.put(mt.obj.getLocalSymbols().size());
		
		
		currentMethod = mt.obj;
    }
	
	// IZLAZ IZ METODE
	public void visit(MethodDeclaration methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		currentMethod = null;
	}
	
	// --------------------------------------
	// *** FACTORI ***
	public void visit(FactorNumConst f) {
		Code.loadConst(f.getNumVal());
	}
	
	public void visit(FactorCharConst f) {
		Code.loadConst(f.getNumVal());
	}
	
	public void visit(FactorBoolConst f) {
		Code.loadConst(f.getNumVal()? 1 : 0);
	}
	
	public void visit(FactorNewArr f) {	
		Struct type = f.getType().struct;
		// ocekuje duzinu niza vec na steku

		if(type == Tab.intType || type == SemanticAnalyzer.boolType) {
			Code.put(Code.newarray);
			Code.put(1); // velicina reci
		}else if (type == Tab.charType) {
			Code.put(Code.newarray);
			Code.put(0); // velicina bajta
		}
    }
	
	public void visit(FactorDesign fd) {
		//??
	}
	
	public void visit(FactorExpr fe) {
		//???
	}

	//-----------------------
	//*** OPERACIJE ***
	public void visit(OpMul op) {
		mulopStack.addLast(Code.mul);
	}
	
	public void visit(DivOp op) {
		mulopStack.addLast(Code.div);
	}
	
	public void visit(ModOp op) {
		mulopStack.addLast(Code.rem);
	}
	
	public void visit(OpAdd op) {
		addopStack.addLast(Code.add);
	}
	
	public void visit(SubOp op) {
		addopStack.addLast(Code.sub);
	}
	
	public void visit(ListAddopTerm l) {
		int op = addopStack.removeLast();
		Code.put(op);
	}
	
	public void visit(MullopFactList l) {
		int op = mulopStack.removeLast();
		Code.put(op);
	}
	
	//----------------------------------------------
	// *** RELACIJE ***
	
	public void visit(RelopE r) {
		
		relopStack.addLast(Code.eq);
		currentRelop = Code.eq;
	}

	public void visit(RelopNE r) {
		
		
		relopStack.addLast(Code.ne);	
		currentRelop = Code.ne;
	}
	
	public void visit(RelopG r) {
		
		
		relopStack.addLast(Code.gt);
		currentRelop = Code.gt;
	}
	
	public void visit(RelopGE r) {
		
		
		relopStack.addLast(Code.ge);
		currentRelop = Code.ge;
	}
	
	public void visit(RelopL r) {
		
		
		relopStack.addLast(Code.lt);
		currentRelop = Code.lt;
	}
	
	public void visit(RelopLE r) {
		
		
		relopStack.addLast(Code.le);
		currentRelop = Code.le;
	}
	
	//-----------------
	// *** TERM ***
	
	public void visit(Term t) {
		if(t.getParent().getClass() == ExpressionMinus.class) {
			Code.put(Code.neg);
		}		
	}
	
	//-------------------------------
	//*** EXPRESSION ***
	
	public void visit (Expression e) {
		
	}
	
	
	public void visit (ExpressionMinus e) {
		
	}
	
	
	
	//----------------------
	// *** CONDITIONS ***
	
	public void visit(ConditionFact c) {
		
		
		
		MyCond elem = new MyCond();
		
		if(c.getRelopExprExists().getClass() == RelopExprExist.class) {
			Code.putFalseJump(currentRelop, 0); //vec ima neki izraz na steku
			elem.setRelop(currentRelop);
		}else {
			Code.loadConst(1); //izraz je sam, poredimo ga sa true (1)
			Code.putFalseJump(Code.eq, 0); //ako true(1) NIJE == 1, skocice se na else granu
			elem.setRelop(Code.eq);
		}
		skipToElse_AdrToFix = Code.pc-2; 
	//	listaAdresaZaFix_skipToElse.add(skipToElse_AdrToFix);
		elem.setAdr(skipToElse_AdrToFix);
		elem.setPoslednji(true);
		if(listaAdresaZaFix_andIzraz.size() > 0)
			listaAdresaZaFix_andIzraz.getLast().setPoslednji(false);
		listaAdresaZaFix_andIzraz.add(elem);
		
		
	}
	
	public void visit(RelopExprExist e) {
		
	}
	
	public void visit(NoRelopExprExist e) {
		
	}
	
	public void visit(ConditionTerm c) {
		
	}
	
	public void visit(ConditionTermAnd c) {
		
		
		//???nesto
		
		if(c.getParent().getClass() == CondOr.class) {
			//report_info("PARENT JE COND OR", null);
		}
		
		//!!!!!!!!!!!!
		/*		while(!listaAdresaZaFix_skipToElse.isEmpty()) {
					int adr = listaAdresaZaFix_skipToElse.removeLast();
					listaAdresaZaFix_and_skip.add(adr);
				}*/
		
	}
	
	public void visit(Cond c) {
		
		
	}
	
	public void visit(CondOr c) {
		
		

	}
	
	public void visit(LogOr lo) {
		
		
		
				

		while(!listaAdresaZaFix_andIzraz.isEmpty()) {
			MyCond elem = listaAdresaZaFix_andIzraz.removeFirst();
			int adr = elem.getAdr();
			if (elem.isPoslednji()) {
				//u pitanju je poslednji, promeni uslov skakanja
				//i promeni adresu skakakanja na then
				int oldRelop = elem.getRelop();
				elem.setRelop(Code.inverse[oldRelop]);
				listaAdresaZaFix_skociNaThen.add(elem);
			}else {
				//promeni adresu skakanja sa else next cond
				listaAdresaZaFix_skociNaSledeciUslov.add(elem);
			}
		}
		// KAD SE POZOVE OR, POSLEDNJEM IZ NIZA AND-OVA SE OBRCE USLOV + SE MENJA ADRESA
				//SKOKA NA THEN
		// OSTALIMA SE MENJA SAMO ADRESA SKOKA NA NEXT COND
		
		//popuni fixeve za next cond !!!!!!!!!!!!
				while(!listaAdresaZaFix_skociNaSledeciUslov.isEmpty()) {
					MyCond elem = listaAdresaZaFix_skociNaSledeciUslov.removeFirst();
					int adr = elem.getAdr();
					Code.fixup(adr);
					Code.put2(adr-1, (Code.jcc+Code.inverse[elem.getRelop()])<<8);
				}//??
	}
	
	//--------------------------------------------------------------
	// *** IF ELSE ZEZANJE ***
	
	// IZLAZ
	public void visit(StmtIf s) {
		
		//Code.fixup(skipToElse_AdrToFix);

		while(!listaAdresaZaFix_andIzraz.isEmpty()) {
			int adr = listaAdresaZaFix_andIzraz.removeFirst().getAdr();
			Code.fixup(adr);			
		}
		
		//kraj
		//pop sa steka, izlazimo iz if-a, else ne postoji
	/*	if(!stackListi_andIzraz.isEmpty()) {
			stackListi_andIzraz.removeLast();
		}
		if(!stackListi_skipElse.isEmpty()) {
			stackListi_skipElse.removeLast();		
		}
		
		if(!stackListi_skociNaSledeciUslov.isEmpty()) {
			stackListi_skociNaSledeciUslov.removeLast();
		}
		
		if(!stackListi_skociNaThen.isEmpty()) {
			stackListi_skociNaThen.removeLast();
		}*/
		
		
		//restauracija konteksta
		if(!stackListi_andIzraz.isEmpty()) {
			listaAdresaZaFix_andIzraz = stackListi_andIzraz.removeLast();
		}
		if(!stackListi_skipElse.isEmpty()) {
			listaAdresaZaFix_skipElse = stackListi_skipElse.removeLast();		
		}
		
		if(!stackListi_skociNaSledeciUslov.isEmpty()) {
			listaAdresaZaFix_skociNaSledeciUslov = stackListi_skociNaSledeciUslov.removeLast();			
		}
		
		if(!stackListi_skociNaThen.isEmpty()) {
			listaAdresaZaFix_skociNaThen = stackListi_skociNaThen.removeLast();
		}
		
		ifCnt--;
		
	}
	
	// IZLAZ
	public void visit(StmtIfElse s) {
		
	//	Code.fixup(skipElse_AdrToFix);
		while(!listaAdresaZaFix_skipElse.isEmpty()) {
			int adr = listaAdresaZaFix_skipElse.removeFirst();
			Code.fixup(adr);
		}
		
		//kraj
		//pop sa steka
	/*	if(!stackListi_andIzraz.isEmpty()) {
			stackListi_andIzraz.removeLast();
		}
		if(!stackListi_skipElse.isEmpty()) {
			stackListi_skipElse.removeLast();		
		}
		
		if(!stackListi_skociNaSledeciUslov.isEmpty()) {
			stackListi_skociNaSledeciUslov.removeLast();
		}
		
		if(!stackListi_skociNaThen.isEmpty()) {
			stackListi_skociNaThen.removeLast();
		}*/
		//restauracija konteksta
		if(!stackListi_andIzraz.isEmpty()) {
			listaAdresaZaFix_andIzraz = stackListi_andIzraz.removeLast();
		}
		if(!stackListi_skipElse.isEmpty()) {
			listaAdresaZaFix_skipElse = stackListi_skipElse.removeLast();		
		}
		
		if(!stackListi_skociNaSledeciUslov.isEmpty()) {
			listaAdresaZaFix_skociNaSledeciUslov = stackListi_skociNaSledeciUslov.removeLast();			
		}
		
		if(!stackListi_skociNaThen.isEmpty()) {
			listaAdresaZaFix_skociNaThen = stackListi_skociNaThen.removeLast();
		}
		ifCnt--;
		
	}
	
	// ULAZ U IF
	public void visit(IfStart s) {
		
		
		if(ifCnt>0) {
			stackListi_andIzraz.addLast(listaAdresaZaFix_andIzraz);
			stackListi_skipElse.addLast(listaAdresaZaFix_skipElse);
			stackListi_skociNaSledeciUslov.addLast(listaAdresaZaFix_skociNaSledeciUslov);
			stackListi_skociNaThen.addLast(listaAdresaZaFix_skociNaThen);
			
		}
	
		ifCnt++;
		stackListi_andIzraz.addLast(new LinkedList<MyCond>());
		stackListi_skipElse.addLast(new LinkedList<Integer>());
		stackListi_skociNaSledeciUslov.addLast(new LinkedList<MyCond>());
		stackListi_skociNaThen.addLast(new LinkedList<MyCond>());
		
		listaAdresaZaFix_andIzraz = stackListi_andIzraz.removeLast();
		listaAdresaZaFix_skipElse = stackListi_skipElse.removeLast();
		listaAdresaZaFix_skociNaSledeciUslov = stackListi_skociNaSledeciUslov.removeLast();
		listaAdresaZaFix_skociNaThen = stackListi_skociNaThen.removeLast();
		
	}
	
	public void visit(IfCond s) {
		
		
		//OVDE POCINJE THEN GRANA, OVDE SE FIXUJU SKAKANJA NA THEN
		while(!listaAdresaZaFix_skociNaThen.isEmpty()) {
			MyCond elem = listaAdresaZaFix_skociNaThen.removeFirst();
			int adr = elem.getAdr();
			Code.put2(adr-1, (Code.jcc+Code.inverse[elem.getRelop()])<<8); //posto ne postoji put sa ovim argumentima
			Code.fixup(adr);			
		}
	}
	
	public void visit(IfBody s) {
		
		
		if(s.getParent().getClass() == StmtIfElse.class) {
			//skociti iza else grane bezuslovno
			Code.put(Code.jmp);
			skipElse_AdrToFix = Code.pc;
			listaAdresaZaFix_skipElse.add(skipElse_AdrToFix);
			Code.put2(0); 
			
		}
		
		
	}
	
	public void visit(ElseStart s) {
		
		//fixovati i popuniti adrese  za skakanje na else granu
	//	Code.fixup(skipToElse_AdrToFix);

		while(!listaAdresaZaFix_andIzraz.isEmpty()) {
			int adr = listaAdresaZaFix_andIzraz.removeFirst().getAdr();
			Code.fixup(adr);			
		}
	}

	//izlazak iz if i elsa
	public void visit(ElseBody s) {
		
		
	}
	//nakon else-fixovati i popuniti adresu u ifbody 
	
}
