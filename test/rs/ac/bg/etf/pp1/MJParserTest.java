/*package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;


import rs.ac.bg.etf.pp1.test.Compiler;

public class MJParserTest implements Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
	        Tab.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v); 
	      
			
			log.info("===================================");
			Tab.dump();
			
			if(!p.errorDetected && v.passed()){
				File objFile = new File("test/program.obj");
				if(objFile.exists()) {
					objFile.delete();
				}
				
				CodeGenerator codeGen = new CodeGenerator();
				prog.traverseBottomUp(codeGen);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGen.getMainPC();
				Code.write(new FileOutputStream(objFile));
				
				log.info("\n Parsiranje uspesno zavrseno!");
			}else{
				log.error("\n Parsiranje NIJE uspesno zavrseno!");
			}
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}

	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}*/
package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import org.apache.log4j.FileAppender;
import java.io.PrintWriter;
import org.apache.log4j.PatternLayout;


import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.test.Compiler;

public class MJParserTest implements Compiler {
	private static File sourceCode;
	//private static Logger log = Logger.getLogger(MJParserTest.class);
	
	public static Logger logError = Logger.getLogger("error");
    public static Logger logInfo = Logger.getLogger("info");
    private static List<CompilerError> listaGresaka = new LinkedList<CompilerError>();
    
    private static int gr = 1;
    
   // private static boolean ispisUFajl = false;
  //  private static boolean greskeUFajl = false;
    
   // private static FileAppender fileAppender;
	//private static FileAppender fileAppenderError;
    
    public static void dodajGresku(CompilerError compilerError) {
    	listaGresaka.add(compilerError);
    }

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
		
	/*	fileAppender = new FileAppender();
		fileAppender.setLayout(new PatternLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN));
		fileAppenderError = new FileAppender();
		fileAppenderError.setLayout(new PatternLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN));*/
		
	}
	
	public static void main(String[] args) throws Exception {
		
		if (args.length < 2) {
			logError.error("Not enough arguments supplied! Usage: MJParser <source-file> <obj-file> ");
			return;
		}
		
		sourceCode = new File(args[0]);
		if (!sourceCode.exists()) {
			logError.error("Source file [" + sourceCode.getAbsolutePath() + "] not found!");
			return;
		}
			
		logInfo.info("Compiling source file: " + sourceCode.getAbsolutePath());
		//-------
		//fajlovi
		
	/*	if(args.length==3 || args.length==4) {
			String out = args[2].substring(1);
			String err = "";
			if(args.length==4) {
				err = args[3].substring(1);
				PrintWriter pw = new PrintWriter("test/"+err);
				pw.close();
			}
			
			PrintWriter pw = new PrintWriter("test/"+out);//da obrises staro
			pw.close();
			
			if(err.equals("")) {
				fileAppender.setFile("test/"+out);
				fileAppenderError.setFile("test/"+out);
				fileAppender.activateOptions();
				fileAppenderError.activateOptions();
				logInfo.addAppender(fileAppender);
				logInfo.addAppender(fileAppenderError);
			} else {
				fileAppender.setFile("test/"+out);
				fileAppenderError.setFile("test/"+err);
				fileAppender.activateOptions();
				fileAppenderError.activateOptions();
				logInfo.addAppender(fileAppender);
				logError.addAppender(fileAppenderError);
			}
			
		}
		
		
		*/
		
		
		//--------------
		
		MJParserTest mjp = new MJParserTest();		
		mjp.compile(args[0], args[1]);
		
		if(listaGresaka.isEmpty() && gr!=0) {
			logInfo.info("Parsiranje uspesno!");
		}else {
			logError.error("Parsiranje NIJE uspesno!");
			for(CompilerError g: listaGresaka) {
				logError.error(g.toString());
			}
		}
	}

	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {

		
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCode))) {
			
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
			
			if(p.errorDetected) {
				logInfo.info("Ulazni fajl ima leksickih i/ili sintaksnih gresaka");
				gr = 0;
				return listaGresaka;
			}
			
			
	        Symbol s = p.parse();  //pocetak parsiranja
	        Program prog = (Program)s.value;
	        logInfo.info("\n************ SINTAKSNO STABLO ************\n");
			logInfo.info(prog.toString(""));
			logInfo.info("\n------------------------------------------\n");
			
			Tab.init(); // Universe scope
			logInfo.info("\n************ SEMANTICKA OBRADA ************\n");
			SemanticAnalyzer semanticCheck = new SemanticAnalyzer();
			prog.traverseBottomUp(semanticCheck);
			
			//tabela simbolica
			tsdump();
			
			if(p.errorDetected) {
				logInfo.info("Ulazni fajl ima leksickih i/ili sintaksnih gresaka");
				gr = 0;
				return listaGresaka;
			}
	        
	        if (!p.errorDetected && semanticCheck.passed()) {
	        	File objFile = new File(outputFilePath);
	        	logInfo.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	// Code generation...
	        	logInfo.info("\n************ GENERISANJE KODA ************\n");
	        	CodeGenerator codeGenerator = new CodeGenerator();
	        	prog.traverseBottomUp(codeGenerator);
	        	Code.dataSize = semanticCheck.nVars;
	        	Code.mainPc = codeGenerator.getMainPC();
	        	Code.write(new FileOutputStream(objFile));
	        	//logInfo.info("Parsiranje uspesno zavrseno!");
	        }
	        else {
	        	logError.error("Parsiranje NIJE uspesno zavrseno!");
	        	gr = 0;
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaGresaka;
	}
	
	public static void tsdump() {
		MySymbolTableVisitor stv = new MySymbolTableVisitor();
        Tab.dump(stv);
	}
}
