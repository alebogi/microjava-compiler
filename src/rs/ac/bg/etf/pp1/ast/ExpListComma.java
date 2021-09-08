// generated with ast extension for cup
// version 0.8
// 7/8/2021 13:48:30


package rs.ac.bg.etf.pp1.ast;

public class ExpListComma extends ExprList {

    private ExprList ExprList;
    private ExprListEnd ExprListEnd;

    public ExpListComma (ExprList ExprList, ExprListEnd ExprListEnd) {
        this.ExprList=ExprList;
        if(ExprList!=null) ExprList.setParent(this);
        this.ExprListEnd=ExprListEnd;
        if(ExprListEnd!=null) ExprListEnd.setParent(this);
    }

    public ExprList getExprList() {
        return ExprList;
    }

    public void setExprList(ExprList ExprList) {
        this.ExprList=ExprList;
    }

    public ExprListEnd getExprListEnd() {
        return ExprListEnd;
    }

    public void setExprListEnd(ExprListEnd ExprListEnd) {
        this.ExprListEnd=ExprListEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprList!=null) ExprList.accept(visitor);
        if(ExprListEnd!=null) ExprListEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprList!=null) ExprList.traverseTopDown(visitor);
        if(ExprListEnd!=null) ExprListEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprList!=null) ExprList.traverseBottomUp(visitor);
        if(ExprListEnd!=null) ExprListEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExpListComma(\n");

        if(ExprList!=null)
            buffer.append(ExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprListEnd!=null)
            buffer.append(ExprListEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExpListComma]");
        return buffer.toString();
    }
}
