// generated with ast extension for cup
// version 0.8
// 11/8/2021 22:0:44


package rs.ac.bg.etf.pp1.ast;

public class MatchedStmt extends Statement {

    private StmtMatched StmtMatched;

    public MatchedStmt (StmtMatched StmtMatched) {
        this.StmtMatched=StmtMatched;
        if(StmtMatched!=null) StmtMatched.setParent(this);
    }

    public StmtMatched getStmtMatched() {
        return StmtMatched;
    }

    public void setStmtMatched(StmtMatched StmtMatched) {
        this.StmtMatched=StmtMatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StmtMatched!=null) StmtMatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StmtMatched!=null) StmtMatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StmtMatched!=null) StmtMatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedStmt(\n");

        if(StmtMatched!=null)
            buffer.append(StmtMatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedStmt]");
        return buffer.toString();
    }
}
