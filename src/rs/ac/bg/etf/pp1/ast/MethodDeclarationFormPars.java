// generated with ast extension for cup
// version 0.8
// 16/8/2021 10:52:26


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationFormPars extends MethodDecl {

    private MethodType MethodType;
    private FormPars FormPars;
    private ListOfVarDecl ListOfVarDecl;
    private ListOfStmt ListOfStmt;

    public MethodDeclarationFormPars (MethodType MethodType, FormPars FormPars, ListOfVarDecl ListOfVarDecl, ListOfStmt ListOfStmt) {
        this.MethodType=MethodType;
        if(MethodType!=null) MethodType.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.ListOfVarDecl=ListOfVarDecl;
        if(ListOfVarDecl!=null) ListOfVarDecl.setParent(this);
        this.ListOfStmt=ListOfStmt;
        if(ListOfStmt!=null) ListOfStmt.setParent(this);
    }

    public MethodType getMethodType() {
        return MethodType;
    }

    public void setMethodType(MethodType MethodType) {
        this.MethodType=MethodType;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public ListOfVarDecl getListOfVarDecl() {
        return ListOfVarDecl;
    }

    public void setListOfVarDecl(ListOfVarDecl ListOfVarDecl) {
        this.ListOfVarDecl=ListOfVarDecl;
    }

    public ListOfStmt getListOfStmt() {
        return ListOfStmt;
    }

    public void setListOfStmt(ListOfStmt ListOfStmt) {
        this.ListOfStmt=ListOfStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodType!=null) MethodType.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.accept(visitor);
        if(ListOfStmt!=null) ListOfStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodType!=null) MethodType.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.traverseTopDown(visitor);
        if(ListOfStmt!=null) ListOfStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodType!=null) MethodType.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.traverseBottomUp(visitor);
        if(ListOfStmt!=null) ListOfStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationFormPars(\n");

        if(MethodType!=null)
            buffer.append(MethodType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListOfVarDecl!=null)
            buffer.append(ListOfVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListOfStmt!=null)
            buffer.append(ListOfStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationFormPars]");
        return buffer.toString();
    }
}
