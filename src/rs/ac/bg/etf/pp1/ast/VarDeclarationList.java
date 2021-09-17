// generated with ast extension for cup
// version 0.8
// 17/8/2021 22:23:49


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationList extends VarDeclList {

    private VarDeclList VarDeclList;
    private VarDeclListEnd VarDeclListEnd;

    public VarDeclarationList (VarDeclList VarDeclList, VarDeclListEnd VarDeclListEnd) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.VarDeclListEnd=VarDeclListEnd;
        if(VarDeclListEnd!=null) VarDeclListEnd.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public VarDeclListEnd getVarDeclListEnd() {
        return VarDeclListEnd;
    }

    public void setVarDeclListEnd(VarDeclListEnd VarDeclListEnd) {
        this.VarDeclListEnd=VarDeclListEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(VarDeclListEnd!=null) VarDeclListEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(VarDeclListEnd!=null) VarDeclListEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(VarDeclListEnd!=null) VarDeclListEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationList(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListEnd!=null)
            buffer.append(VarDeclListEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationList]");
        return buffer.toString();
    }
}
