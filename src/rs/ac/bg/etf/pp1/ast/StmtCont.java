// generated with ast extension for cup
// version 0.8
// 13/8/2021 13:3:10


package rs.ac.bg.etf.pp1.ast;

public class StmtCont extends StmtMatched {

    public StmtCont () {
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
        buffer.append("StmtCont(\n");

        buffer.append(tab);
        buffer.append(") [StmtCont]");
        return buffer.toString();
    }
}
