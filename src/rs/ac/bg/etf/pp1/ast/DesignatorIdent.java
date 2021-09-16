// generated with ast extension for cup
// version 0.8
// 16/8/2021 10:52:26


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIdent extends Designator {

    private String dsgnName;

    public DesignatorIdent (String dsgnName) {
        this.dsgnName=dsgnName;
    }

    public String getDsgnName() {
        return dsgnName;
    }

    public void setDsgnName(String dsgnName) {
        this.dsgnName=dsgnName;
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
        buffer.append("DesignatorIdent(\n");

        buffer.append(" "+tab+dsgnName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIdent]");
        return buffer.toString();
    }
}
