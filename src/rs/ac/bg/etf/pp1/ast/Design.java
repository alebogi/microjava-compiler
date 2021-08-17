// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:23:35


package rs.ac.bg.etf.pp1.ast;

public class Design extends Designator {

    private String DesgnName;
    private DesgnList DesgnList;

    public Design (String DesgnName, DesgnList DesgnList) {
        this.DesgnName=DesgnName;
        this.DesgnList=DesgnList;
        if(DesgnList!=null) DesgnList.setParent(this);
    }

    public String getDesgnName() {
        return DesgnName;
    }

    public void setDesgnName(String DesgnName) {
        this.DesgnName=DesgnName;
    }

    public DesgnList getDesgnList() {
        return DesgnList;
    }

    public void setDesgnList(DesgnList DesgnList) {
        this.DesgnList=DesgnList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesgnList!=null) DesgnList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesgnList!=null) DesgnList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesgnList!=null) DesgnList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Design(\n");

        buffer.append(" "+tab+DesgnName);
        buffer.append("\n");

        if(DesgnList!=null)
            buffer.append(DesgnList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Design]");
        return buffer.toString();
    }
}
