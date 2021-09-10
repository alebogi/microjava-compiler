// generated with ast extension for cup
// version 0.8
// 10/8/2021 13:48:57


package rs.ac.bg.etf.pp1.ast;

public class ConditionTerm extends CondTerm {

    private CondFact CondFact;
    private CondFactAndList CondFactAndList;

    public ConditionTerm (CondFact CondFact, CondFactAndList CondFactAndList) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.CondFactAndList=CondFactAndList;
        if(CondFactAndList!=null) CondFactAndList.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public CondFactAndList getCondFactAndList() {
        return CondFactAndList;
    }

    public void setCondFactAndList(CondFactAndList CondFactAndList) {
        this.CondFactAndList=CondFactAndList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(CondFactAndList!=null) CondFactAndList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(CondFactAndList!=null) CondFactAndList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(CondFactAndList!=null) CondFactAndList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionTerm(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactAndList!=null)
            buffer.append(CondFactAndList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionTerm]");
        return buffer.toString();
    }
}
