// generated with ast extension for cup
// version 0.8
// 21/7/2021 18:26:51


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationListEnd extends VarDeclListEnd {

    private String varDeclListEndName;
    private Arr Arr;

    public VarDeclarationListEnd (String varDeclListEndName, Arr Arr) {
        this.varDeclListEndName=varDeclListEndName;
        this.Arr=Arr;
        if(Arr!=null) Arr.setParent(this);
    }

    public String getVarDeclListEndName() {
        return varDeclListEndName;
    }

    public void setVarDeclListEndName(String varDeclListEndName) {
        this.varDeclListEndName=varDeclListEndName;
    }

    public Arr getArr() {
        return Arr;
    }

    public void setArr(Arr Arr) {
        this.Arr=Arr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Arr!=null) Arr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Arr!=null) Arr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Arr!=null) Arr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationListEnd(\n");

        buffer.append(" "+tab+varDeclListEndName);
        buffer.append("\n");

        if(Arr!=null)
            buffer.append(Arr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationListEnd]");
        return buffer.toString();
    }
}
