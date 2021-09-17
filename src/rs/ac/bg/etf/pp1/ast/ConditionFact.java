// generated with ast extension for cup
// version 0.8
// 17/8/2021 18:41:45


package rs.ac.bg.etf.pp1.ast;

public class ConditionFact extends CondFact {

    private Expr Expr;
    private RelopExprExists RelopExprExists;

    public ConditionFact (Expr Expr, RelopExprExists RelopExprExists) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.RelopExprExists=RelopExprExists;
        if(RelopExprExists!=null) RelopExprExists.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public RelopExprExists getRelopExprExists() {
        return RelopExprExists;
    }

    public void setRelopExprExists(RelopExprExists RelopExprExists) {
        this.RelopExprExists=RelopExprExists;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(RelopExprExists!=null) RelopExprExists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(RelopExprExists!=null) RelopExprExists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(RelopExprExists!=null) RelopExprExists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionFact(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RelopExprExists!=null)
            buffer.append(RelopExprExists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionFact]");
        return buffer.toString();
    }
}
