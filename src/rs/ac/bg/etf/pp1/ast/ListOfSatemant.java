// generated with ast extension for cup
// version 0.8
// 21/7/2021 18:50:58


package rs.ac.bg.etf.pp1.ast;

public class ListOfSatemant extends ListOfStmt {

    private ListOfStmt ListOfStmt;
    private Statement Statement;

    public ListOfSatemant (ListOfStmt ListOfStmt, Statement Statement) {
        this.ListOfStmt=ListOfStmt;
        if(ListOfStmt!=null) ListOfStmt.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ListOfStmt getListOfStmt() {
        return ListOfStmt;
    }

    public void setListOfStmt(ListOfStmt ListOfStmt) {
        this.ListOfStmt=ListOfStmt;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListOfStmt!=null) ListOfStmt.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfStmt!=null) ListOfStmt.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfStmt!=null) ListOfStmt.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ListOfSatemant(\n");

        if(ListOfStmt!=null)
            buffer.append(ListOfStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ListOfSatemant]");
        return buffer.toString();
    }
}
