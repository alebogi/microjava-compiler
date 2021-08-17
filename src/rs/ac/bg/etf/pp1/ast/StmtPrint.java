// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:4:5


package rs.ac.bg.etf.pp1.ast;

public class StmtPrint extends Statement {

    private Expr Expr;
    private NumConstExists NumConstExists;

    public StmtPrint (Expr Expr, NumConstExists NumConstExists) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.NumConstExists=NumConstExists;
        if(NumConstExists!=null) NumConstExists.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public NumConstExists getNumConstExists() {
        return NumConstExists;
    }

    public void setNumConstExists(NumConstExists NumConstExists) {
        this.NumConstExists=NumConstExists;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(NumConstExists!=null) NumConstExists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(NumConstExists!=null) NumConstExists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(NumConstExists!=null) NumConstExists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumConstExists!=null)
            buffer.append(NumConstExists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtPrint]");
        return buffer.toString();
    }
}
