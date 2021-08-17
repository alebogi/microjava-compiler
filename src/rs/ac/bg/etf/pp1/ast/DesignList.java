// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:4:5


package rs.ac.bg.etf.pp1.ast;

public class DesignList extends DesgnList {

    private DesgnList DesgnList;
    private DesgnListEnd DesgnListEnd;

    public DesignList (DesgnList DesgnList, DesgnListEnd DesgnListEnd) {
        this.DesgnList=DesgnList;
        if(DesgnList!=null) DesgnList.setParent(this);
        this.DesgnListEnd=DesgnListEnd;
        if(DesgnListEnd!=null) DesgnListEnd.setParent(this);
    }

    public DesgnList getDesgnList() {
        return DesgnList;
    }

    public void setDesgnList(DesgnList DesgnList) {
        this.DesgnList=DesgnList;
    }

    public DesgnListEnd getDesgnListEnd() {
        return DesgnListEnd;
    }

    public void setDesgnListEnd(DesgnListEnd DesgnListEnd) {
        this.DesgnListEnd=DesgnListEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesgnList!=null) DesgnList.accept(visitor);
        if(DesgnListEnd!=null) DesgnListEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesgnList!=null) DesgnList.traverseTopDown(visitor);
        if(DesgnListEnd!=null) DesgnListEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesgnList!=null) DesgnList.traverseBottomUp(visitor);
        if(DesgnListEnd!=null) DesgnListEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignList(\n");

        if(DesgnList!=null)
            buffer.append(DesgnList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesgnListEnd!=null)
            buffer.append(DesgnListEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignList]");
        return buffer.toString();
    }
}
