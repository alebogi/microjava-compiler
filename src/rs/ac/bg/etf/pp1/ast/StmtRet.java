// generated with ast extension for cup
// version 0.8
// 18/8/2021 21:42:56


package rs.ac.bg.etf.pp1.ast;

public class StmtRet extends Statement {

    private ExprExists ExprExists;

    public StmtRet (ExprExists ExprExists) {
        this.ExprExists=ExprExists;
        if(ExprExists!=null) ExprExists.setParent(this);
    }

    public ExprExists getExprExists() {
        return ExprExists;
    }

    public void setExprExists(ExprExists ExprExists) {
        this.ExprExists=ExprExists;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprExists!=null) ExprExists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprExists!=null) ExprExists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprExists!=null) ExprExists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtRet(\n");

        if(ExprExists!=null)
            buffer.append(ExprExists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtRet]");
        return buffer.toString();
    }
}
