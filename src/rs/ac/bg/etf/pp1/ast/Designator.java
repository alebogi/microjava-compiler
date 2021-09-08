// generated with ast extension for cup
// version 0.8
// 7/8/2021 13:48:30


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String dsgnName;
    private DesgnList DesgnList;

    public Designator (String dsgnName, DesgnList DesgnList) {
        this.dsgnName=dsgnName;
        this.DesgnList=DesgnList;
        if(DesgnList!=null) DesgnList.setParent(this);
    }

    public String getDsgnName() {
        return dsgnName;
    }

    public void setDsgnName(String dsgnName) {
        this.dsgnName=dsgnName;
    }

    public DesgnList getDesgnList() {
        return DesgnList;
    }

    public void setDesgnList(DesgnList DesgnList) {
        this.DesgnList=DesgnList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("Designator(\n");

        buffer.append(" "+tab+dsgnName);
        buffer.append("\n");

        if(DesgnList!=null)
            buffer.append(DesgnList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
