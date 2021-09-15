// generated with ast extension for cup
// version 0.8
// 15/8/2021 19:10:31


package rs.ac.bg.etf.pp1.ast;

public class RelopL extends Relop {

    public RelopL () {
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
        buffer.append("RelopL(\n");

        buffer.append(tab);
        buffer.append(") [RelopL]");
        return buffer.toString();
    }
}
