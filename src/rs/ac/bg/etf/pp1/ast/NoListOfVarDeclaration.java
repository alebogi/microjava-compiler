// generated with ast extension for cup
// version 0.8
// 10/8/2021 13:48:57


package rs.ac.bg.etf.pp1.ast;

public class NoListOfVarDeclaration extends ListOfVarDecl {

    public NoListOfVarDeclaration () {
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
        buffer.append("NoListOfVarDeclaration(\n");

        buffer.append(tab);
        buffer.append(") [NoListOfVarDeclaration]");
        return buffer.toString();
    }
}
