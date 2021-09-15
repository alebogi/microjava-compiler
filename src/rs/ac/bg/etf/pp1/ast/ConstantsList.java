// generated with ast extension for cup
// version 0.8
// 15/8/2021 19:10:30


package rs.ac.bg.etf.pp1.ast;

public class ConstantsList extends ConstList {

    private ConstList ConstList;
    private ConstListEnd ConstListEnd;

    public ConstantsList (ConstList ConstList, ConstListEnd ConstListEnd) {
        this.ConstList=ConstList;
        if(ConstList!=null) ConstList.setParent(this);
        this.ConstListEnd=ConstListEnd;
        if(ConstListEnd!=null) ConstListEnd.setParent(this);
    }

    public ConstList getConstList() {
        return ConstList;
    }

    public void setConstList(ConstList ConstList) {
        this.ConstList=ConstList;
    }

    public ConstListEnd getConstListEnd() {
        return ConstListEnd;
    }

    public void setConstListEnd(ConstListEnd ConstListEnd) {
        this.ConstListEnd=ConstListEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstList!=null) ConstList.accept(visitor);
        if(ConstListEnd!=null) ConstListEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstList!=null) ConstList.traverseTopDown(visitor);
        if(ConstListEnd!=null) ConstListEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstList!=null) ConstList.traverseBottomUp(visitor);
        if(ConstListEnd!=null) ConstListEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantsList(\n");

        if(ConstList!=null)
            buffer.append(ConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstListEnd!=null)
            buffer.append(ConstListEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantsList]");
        return buffer.toString();
    }
}
