package rs.ac.bg.etf.pp1.test;

import java.util.List;

import rs.ac.bg.etf.pp1.CompilerError;

public interface Compiler {
	
	List<CompilerError> compile(String sourceFilePath, String outputFilePath);
}
