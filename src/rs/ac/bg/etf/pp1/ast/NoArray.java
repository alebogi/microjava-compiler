// generated with ast extension for cup
// version 0.8
// 11/8/2021 1:17:19


package rs.ac.bg.etf.pp1.ast;

public class NoArray extends Arr {

    public NoArray () {
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
        buffer.append("NoArray(\n");

        buffer.append(tab);
        buffer.append(") [NoArray]");
        return buffer.toString();
    }
}
