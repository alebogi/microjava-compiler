package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Struct;

public class MyVariable {

	private String name;
	private Object obj;
	private Struct type;
	private boolean arr = false;
	private boolean constant = false;
	
	public MyVariable(String name) {
		super();
		this.name = name;
	}
	
	public MyVariable(String name, Object obj) {
		super();
		this.name = name;
		this.obj = obj;
	}
	
	public MyVariable(String name, Object obj, Struct type) {
		super();
		this.name = name;
		this.obj = obj;
		this.type = type;
	}
	
	public MyVariable(String name, Object obj, Struct type, boolean arr) {
		super();
		this.name = name;
		this.obj = obj;
		this.type = type;
		this.arr = arr;
	}
	
	public MyVariable(String name, boolean constant) {
		super();
		this.name = name;
		this.constant = constant;
	}
	
	
	public MyVariable(String name, Object obj, Struct type, boolean arr, boolean constant) {
		super();
		this.name = name;
		this.obj = obj;
		this.type = type;
		this.arr = arr;
		this.constant = constant;
	}




	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Struct getType() {
		return type;
	}
	public void setType(Struct type) {
		this.type = type;
	}
	public boolean isArr() {
		return arr;
	}
	public void setArr(boolean arr) {
		this.arr = arr;
	}
	public boolean isConstant() {
		return constant;
	}
	public void setConstant(boolean constant) {
		this.constant = constant;
	}
	
	
}
