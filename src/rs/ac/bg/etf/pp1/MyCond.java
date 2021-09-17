package rs.ac.bg.etf.pp1;

public class MyCond {

	private int adr; // pc na koji se vracamo
	private boolean poslednji; //koji je po redu and
	private int relop; //koji relop je u pitanju
	private boolean set; //setovana adresa skoka
	

	public MyCond(int pcRetAdr, int relop) {
		super();
		this.adr = pcRetAdr;
		this.relop = relop;
		this.set = false;
		this.poslednji = false;
	}
	
	public MyCond() {
		super();
		this.set = false;
		this.poslednji = false;
	}

	public int getAdr() {
		return adr;
	}

	public void setAdr(int pcRetAdr) {
		this.adr = pcRetAdr;
	}



	public boolean isPoslednji() {
		return poslednji;
	}

	public void setPoslednji(boolean poslednji) {
		this.poslednji = poslednji;
	}

	public int getRelop() {
		return relop;
	}

	public void setRelop(int relop) {
		this.relop = relop;
	}

	public boolean isSet() {
		return set;
	}

	public void setSet(boolean set) {
		this.set = set;
	}
	
	
}
