// generated with ast extension for cup
// version 0.8
// 17/8/2021 18:41:45


package rs.ac.bg.etf.pp1.ast;

public class FormParamsList extends FormParsList {

    private FormParsList FormParsList;
    private FormParsListEnd FormParsListEnd;

    public FormParamsList (FormParsList FormParsList, FormParsListEnd FormParsListEnd) {
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.FormParsListEnd=FormParsListEnd;
        if(FormParsListEnd!=null) FormParsListEnd.setParent(this);
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
    }

    public FormParsListEnd getFormParsListEnd() {
        return FormParsListEnd;
    }

    public void setFormParsListEnd(FormParsListEnd FormParsListEnd) {
        this.FormParsListEnd=FormParsListEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(FormParsListEnd!=null) FormParsListEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(FormParsListEnd!=null) FormParsListEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(FormParsListEnd!=null) FormParsListEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParamsList(\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsListEnd!=null)
            buffer.append(FormParsListEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamsList]");
        return buffer.toString();
    }
}
