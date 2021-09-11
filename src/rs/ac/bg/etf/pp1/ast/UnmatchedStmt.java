// generated with ast extension for cup
// version 0.8
// 11/8/2021 18:37:8


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedStmt extends Statement {

    private StmtUnmatched StmtUnmatched;

    public UnmatchedStmt (StmtUnmatched StmtUnmatched) {
        this.StmtUnmatched=StmtUnmatched;
        if(StmtUnmatched!=null) StmtUnmatched.setParent(this);
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
        if(StmtUnmatched!=null) StmtUnmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StmtUnmatched!=null) StmtUnmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StmtUnmatched!=null) StmtUnmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedStmt(\n");

        if(StmtUnmatched!=null)
            buffer.append(StmtUnmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedStmt]");
        return buffer.toString();
    }
}
