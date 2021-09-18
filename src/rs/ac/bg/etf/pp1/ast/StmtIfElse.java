// generated with ast extension for cup
// version 0.8
// 18/8/2021 21:42:56


package rs.ac.bg.etf.pp1.ast;

public class StmtIfElse extends Statement {

    private IfStart IfStart;
    private IfBody IfBody;
    private ElseBody ElseBody;

    public StmtIfElse (IfStart IfStart, IfBody IfBody, ElseBody ElseBody) {
        this.IfStart=IfStart;
        if(IfStart!=null) IfStart.setParent(this);
        this.IfBody=IfBody;
        if(IfBody!=null) IfBody.setParent(this);
        this.ElseBody=ElseBody;
        if(ElseBody!=null) ElseBody.setParent(this);
    }

    public IfStart getIfStart() {
        return IfStart;
    }

    public void setIfStart(IfStart IfStart) {
        this.IfStart=IfStart;
    }

    public IfBody getIfBody() {
        return IfBody;
    }

    public void setIfBody(IfBody IfBody) {
        this.IfBody=IfBody;
    }

    public ElseBody getElseBody() {
        return ElseBody;
    }

    public void setElseBody(ElseBody ElseBody) {
        this.ElseBody=ElseBody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStart!=null) IfStart.accept(visitor);
        if(IfBody!=null) IfBody.accept(visitor);
        if(ElseBody!=null) ElseBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStart!=null) IfStart.traverseTopDown(visitor);
        if(IfBody!=null) IfBody.traverseTopDown(visitor);
        if(ElseBody!=null) ElseBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStart!=null) IfStart.traverseBottomUp(visitor);
        if(IfBody!=null) IfBody.traverseBottomUp(visitor);
        if(ElseBody!=null) ElseBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtIfElse(\n");

        if(IfStart!=null)
            buffer.append(IfStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfBody!=null)
            buffer.append(IfBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseBody!=null)
            buffer.append(ElseBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtIfElse]");
        return buffer.toString();
    }
}
