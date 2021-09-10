// generated with ast extension for cup
// version 0.8
// 10/8/2021 13:48:57


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarationMethods extends ClassDecl {

    private String className;
    private ExtendsClass ExtendsClass;
    private ListOfVarDecl ListOfVarDecl;
    private MethodDeclList MethodDeclList;

    public ClassDeclarationMethods (String className, ExtendsClass ExtendsClass, ListOfVarDecl ListOfVarDecl, MethodDeclList MethodDeclList) {
        this.className=className;
        this.ExtendsClass=ExtendsClass;
        if(ExtendsClass!=null) ExtendsClass.setParent(this);
        this.ListOfVarDecl=ListOfVarDecl;
        if(ListOfVarDecl!=null) ListOfVarDecl.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
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

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendsClass!=null) ExtendsClass.accept(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsClass!=null) ExtendsClass.traverseTopDown(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsClass!=null) ExtendsClass.traverseBottomUp(visitor);
        if(ListOfVarDecl!=null) ListOfVarDecl.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarationMethods(\n");

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

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarationMethods]");
        return buffer.toString();
    }
}
