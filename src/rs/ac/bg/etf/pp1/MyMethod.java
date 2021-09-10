package rs.ac.bg.etf.pp1;

import java.util.LinkedList;

public class MyMethod {
	
	private String name;
	private LinkedList<MyVariable> formPars = new LinkedList<MyVariable>();
	
	public MyMethod(String n) {
		this.name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
