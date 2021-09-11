// generated with ast extension for cup
// version 0.8
// 11/8/2021 18:37:8


package rs.ac.bg.etf.pp1.ast;

public class DesignStmt extends DesignatorStatement {

    private Designator Designator;
    private DesgStmtEnd DesgStmtEnd;

    public DesignStmt (Designator Designator, DesgStmtEnd DesgStmtEnd) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesgStmtEnd=DesgStmtEnd;
        if(DesgStmtEnd!=null) DesgStmtEnd.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesgStmtEnd getDesgStmtEnd() {
        return DesgStmtEnd;
    }

    public void setDesgStmtEnd(DesgStmtEnd DesgStmtEnd) {
        this.DesgStmtEnd=DesgStmtEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesgStmtEnd!=null) DesgStmtEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesgStmtEnd!=null) DesgStmtEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesgStmtEnd!=null) DesgStmtEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesgStmtEnd!=null)
            buffer.append(DesgStmtEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignStmt]");
        return buffer.toString();
    }
}
