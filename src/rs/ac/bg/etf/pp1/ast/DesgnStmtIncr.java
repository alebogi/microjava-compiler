// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:4:5


package rs.ac.bg.etf.pp1.ast;

public class DesgnStmtIncr extends DesgStmtEnd {

    public DesgnStmtIncr () {
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
        buffer.append("DesgnStmtIncr(\n");

        buffer.append(tab);
        buffer.append(") [DesgnStmtIncr]");
        return buffer.toString();
    }
}
