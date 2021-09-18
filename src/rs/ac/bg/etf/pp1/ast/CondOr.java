// generated with ast extension for cup
// version 0.8
// 18/8/2021 21:42:56


package rs.ac.bg.etf.pp1.ast;

public class CondOr extends Condition {

    private Condition Condition;
    private LogOr LogOr;
    private CondTerm CondTerm;

    public CondOr (Condition Condition, LogOr LogOr, CondTerm CondTerm) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.LogOr=LogOr;
        if(LogOr!=null) LogOr.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public LogOr getLogOr() {
        return LogOr;
    }

    public void setLogOr(LogOr LogOr) {
        this.LogOr=LogOr;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(LogOr!=null) LogOr.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(LogOr!=null) LogOr.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(LogOr!=null) LogOr.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondOr(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LogOr!=null)
            buffer.append(LogOr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondOr]");
        return buffer.toString();
    }
}
