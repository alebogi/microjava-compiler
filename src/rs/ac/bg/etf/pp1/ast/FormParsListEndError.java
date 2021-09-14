// generated with ast extension for cup
// version 0.8
// 14/8/2021 20:20:17


package rs.ac.bg.etf.pp1.ast;

public class FormParsListEndError extends FormParsListEnd {

    public FormParsListEndError () {
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
        buffer.append("FormParsListEndError(\n");

        buffer.append(tab);
        buffer.append(") [FormParsListEndError]");
        return buffer.toString();
    }
}
