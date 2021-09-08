// generated with ast extension for cup
// version 0.8
// 8/8/2021 22:45:42


package rs.ac.bg.etf.pp1.ast;

public class FactorNew extends Factor {

    private Type Type;
    private ExprArrExists ExprArrExists;

    public FactorNew (Type Type, ExprArrExists ExprArrExists) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ExprArrExists=ExprArrExists;
        if(ExprArrExists!=null) ExprArrExists.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ExprArrExists getExprArrExists() {
        return ExprArrExists;
    }

    public void setExprArrExists(ExprArrExists ExprArrExists) {
        this.ExprArrExists=ExprArrExists;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ExprArrExists!=null) ExprArrExists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ExprArrExists!=null) ExprArrExists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ExprArrExists!=null) ExprArrExists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorNew(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprArrExists!=null)
            buffer.append(ExprArrExists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorNew]");
        return buffer.toString();
    }
}
