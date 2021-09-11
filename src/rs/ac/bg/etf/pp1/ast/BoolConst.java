// generated with ast extension for cup
// version 0.8
// 11/8/2021 22:0:44


package rs.ac.bg.etf.pp1.ast;

public class BoolConst extends Consts {

    private Boolean boolValue;

    public BoolConst (Boolean boolValue) {
        this.boolValue=boolValue;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(Boolean boolValue) {
        this.boolValue=boolValue;
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
        buffer.append("BoolConst(\n");

        buffer.append(" "+tab+boolValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConst]");
        return buffer.toString();
    }
}
