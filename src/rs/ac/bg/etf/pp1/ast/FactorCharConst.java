// generated with ast extension for cup
// version 0.8
// 14/8/2021 23:18:50


package rs.ac.bg.etf.pp1.ast;

public class FactorCharConst extends Factor {

    private Character numVal;

    public FactorCharConst (Character numVal) {
        this.numVal=numVal;
    }

    public Character getNumVal() {
        return numVal;
    }

    public void setNumVal(Character numVal) {
        this.numVal=numVal;
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
        buffer.append("FactorCharConst(\n");

        buffer.append(" "+tab+numVal);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorCharConst]");
        return buffer.toString();
    }
}
