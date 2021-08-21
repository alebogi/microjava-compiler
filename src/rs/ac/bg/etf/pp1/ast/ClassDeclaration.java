// generated with ast extension for cup
// version 0.8
// 21/7/2021 17:44:8


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ClassDecl {

    private String className;
    private ExtendsClass ExtendsClass;
    private ListOfVarDecl ListOfVarDecl;

    public ClassDeclaration (String className, ExtendsClass ExtendsClass, ListOfVarDecl ListOfVarDecl) {
        this.className=className;
        this.ExtendsClass=ExtendsClass;
        if(ExtendsClass!=null) ExtendsClass.setParent(this);
        this.ListOfVarDecl=ListOfVarDecl;
        if(ListOfVarDecl!=null) ListOfVarDecl.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public ExtendsClass getExtendsClass() {
        return ExtendsClass;
    }

    public void setExtendsClass(ExtendsClass ExtendsClass) {
        this.ExtendsClass=ExtendsClass;
    }

    public ListOfVarDecl getListOfVarDecl() {
        return ListOfVarDecl;
    }

    public void setListOfVarDecl(ListOfVarDecl ListOfVarDecl) {
        this.ListOfVarDecl=ListOfVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendsClass!=null) ExtendsClass.accept(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsClass!=null) ExtendsClass.traverseTopDown(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsClass!=null) ExtendsClass.traverseBottomUp(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(ExtendsClass!=null)
            buffer.append(ExtendsClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListOfVarDecl!=null)
            buffer.append(ListOfVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
