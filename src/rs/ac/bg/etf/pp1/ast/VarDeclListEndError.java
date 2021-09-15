// generated with ast extension for cup
// version 0.8
// 15/8/2021 19:10:30


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListEndError extends VarDeclListEnd {

    public VarDeclListEndError () {
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
        buffer.append("VarDeclListEndError(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListEndError]");
        return buffer.toString();
    }
}
