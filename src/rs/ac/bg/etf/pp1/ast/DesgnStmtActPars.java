// generated with ast extension for cup
// version 0.8
// 11/8/2021 22:0:44


package rs.ac.bg.etf.pp1.ast;

public class DesgnStmtActPars extends DesignatorStatement {

    private Designator Designator;
    private ActParsExists ActParsExists;

    public DesgnStmtActPars (Designator Designator, ActParsExists ActParsExists) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ActParsExists=ActParsExists;
        if(ActParsExists!=null) ActParsExists.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ActParsExists getActParsExists() {
        return ActParsExists;
    }

    public void setActParsExists(ActParsExists ActParsExists) {
        this.ActParsExists=ActParsExists;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ActParsExists!=null) ActParsExists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ActParsExists!=null) ActParsExists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ActParsExists!=null) ActParsExists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesgnStmtActPars(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsExists!=null)
            buffer.append(ActParsExists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesgnStmtActPars]");
        return buffer.toString();
    }
}
