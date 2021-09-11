// generated with ast extension for cup
// version 0.8
// 11/8/2021 18:37:8


package rs.ac.bg.etf.pp1.ast;

public class DeclarationsList extends DeclsList {

    private DeclsList DeclsList;
    private Decls Decls;

    public DeclarationsList (DeclsList DeclsList, Decls Decls) {
        this.DeclsList=DeclsList;
        if(DeclsList!=null) DeclsList.setParent(this);
        this.Decls=Decls;
        if(Decls!=null) Decls.setParent(this);
    }

    public DeclsList getDeclsList() {
        return DeclsList;
    }

    public void setDeclsList(DeclsList DeclsList) {
        this.DeclsList=DeclsList;
    }

    public Decls getDecls() {
        return Decls;
    }

    public void setDecls(Decls Decls) {
        this.Decls=Decls;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclsList!=null) DeclsList.accept(visitor);
        if(Decls!=null) Decls.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclsList!=null) DeclsList.traverseTopDown(visitor);
        if(Decls!=null) Decls.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclsList!=null) DeclsList.traverseBottomUp(visitor);
        if(Decls!=null) Decls.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclarationsList(\n");

        if(DeclsList!=null)
            buffer.append(DeclsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Decls!=null)
            buffer.append(Decls.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclarationsList]");
        return buffer.toString();
    }
}
