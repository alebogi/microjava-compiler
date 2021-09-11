// generated with ast extension for cup
// version 0.8
// 11/8/2021 18:37:8


package rs.ac.bg.etf.pp1.ast;

public class DesignListEnd extends DesgnListEnd {

    private String DesgnNameId;

    public DesignListEnd (String DesgnNameId) {
        this.DesgnNameId=DesgnNameId;
    }

    public String getDesgnNameId() {
        return DesgnNameId;
    }

    public void setDesgnNameId(String DesgnNameId) {
        this.DesgnNameId=DesgnNameId;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignListEnd(\n");

        buffer.append(" "+tab+DesgnNameId);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignListEnd]");
        return buffer.toString();
    }
}
