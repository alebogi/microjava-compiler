// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:4:5


package rs.ac.bg.etf.pp1.ast;

public class NoDeclarationsList extends DeclsList {

    public NoDeclarationsList () {
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
        buffer.append("NoDeclarationsList(\n");

        buffer.append(tab);
        buffer.append(") [NoDeclarationsList]");
        return buffer.toString();
    }
}
