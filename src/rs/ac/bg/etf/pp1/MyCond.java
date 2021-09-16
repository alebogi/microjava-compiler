package rs.ac.bg.etf.pp1;

public class MyCond {

	private int pcRetAdr; // pc na koji se vracamo
	private int andNum; //koji je po redu and
	private int relop; //koji relop je u pitanju
	private boolean set; //setovana adresa skoka
	
	public MyCond(int pcRetAdr, int andNum, int relop) {
		super();
		this.pcRetAdr = pcRetAdr;
		this.andNum = andNum;
		this.relop = relop;
		this.set = false;
	}

	public int getPcRetAdr() {
		return pcRetAdr;
	}

	public void setPcRetAdr(int pcRetAdr) {
		this.pcRetAdr = pcRetAdr;
	}

	public int getAndNum() {
		return andNum;
	}

	public void setAndNum(int andNum) {
		this.andNum = andNum;
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
