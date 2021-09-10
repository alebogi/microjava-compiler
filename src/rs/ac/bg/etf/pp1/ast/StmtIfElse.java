// generated with ast extension for cup
// version 0.8
// 11/8/2021 1:17:19


package rs.ac.bg.etf.pp1.ast;

public class StmtIfElse extends StmtMatched {

    private Condition Condition;
    private StmtMatched StmtMatched;
    private StmtMatched StmtMatched1;

    public StmtIfElse (Condition Condition, StmtMatched StmtMatched, StmtMatched StmtMatched1) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.StmtMatched=StmtMatched;
        if(StmtMatched!=null) StmtMatched.setParent(this);
        this.StmtMatched1=StmtMatched1;
        if(StmtMatched1!=null) StmtMatched1.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public StmtMatched getStmtMatched() {
        return StmtMatched;
    }

    public void setStmtMatched(StmtMatched StmtMatched) {
        this.StmtMatched=StmtMatched;
    }

    public StmtMatched getStmtMatched1() {
        return StmtMatched1;
    }

    public void setStmtMatched1(StmtMatched StmtMatched1) {
        this.StmtMatched1=StmtMatched1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(StmtMatched!=null) StmtMatched.accept(visitor);
        if(StmtMatched1!=null) StmtMatched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(StmtMatched!=null) StmtMatched.traverseTopDown(visitor);
        if(StmtMatched1!=null) StmtMatched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(StmtMatched!=null) StmtMatched.traverseBottomUp(visitor);
        if(StmtMatched1!=null) StmtMatched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtIfElse(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StmtMatched!=null)
            buffer.append(StmtMatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StmtMatched1!=null)
            buffer.append(StmtMatched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtIfElse]");
        return buffer.toString();
    }
}
