// generated with ast extension for cup
// version 0.8
// 7/8/2021 13:48:30


package rs.ac.bg.etf.pp1.ast;

public class StmtUnamtchedIfElse extends StmtUnmatched {

    private Condition Condition;
    private StmtMatched StmtMatched;
    private StmtUnmatched StmtUnmatched;

    public StmtUnamtchedIfElse (Condition Condition, StmtMatched StmtMatched, StmtUnmatched StmtUnmatched) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.StmtMatched=StmtMatched;
        if(StmtMatched!=null) StmtMatched.setParent(this);
        this.StmtUnmatched=StmtUnmatched;
        if(StmtUnmatched!=null) StmtUnmatched.setParent(this);
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

    public StmtUnmatched getStmtUnmatched() {
        return StmtUnmatched;
    }

    public void setStmtUnmatched(StmtUnmatched StmtUnmatched) {
        this.StmtUnmatched=StmtUnmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(StmtMatched!=null) StmtMatched.accept(visitor);
        if(StmtUnmatched!=null) StmtUnmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(StmtMatched!=null) StmtMatched.traverseTopDown(visitor);
        if(StmtUnmatched!=null) StmtUnmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(StmtMatched!=null) StmtMatched.traverseBottomUp(visitor);
        if(StmtUnmatched!=null) StmtUnmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtUnamtchedIfElse(\n");

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

        if(StmtUnmatched!=null)
            buffer.append(StmtUnmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtUnamtchedIfElse]");
        return buffer.toString();
    }
}
