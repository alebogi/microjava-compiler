// generated with ast extension for cup
// version 0.8
// 11/8/2021 22:0:44


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArr extends Designator {

    private String dsgnName;
    private Expr Expr;

    public DesignatorArr (String dsgnName, Expr Expr) {
        this.dsgnName=dsgnName;
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public String getDsgnName() {
        return dsgnName;
    }

    public void setDsgnName(String dsgnName) {
        this.dsgnName=dsgnName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArr(\n");

        buffer.append(" "+tab+dsgnName);
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArr]");
        return buffer.toString();
    }
}
