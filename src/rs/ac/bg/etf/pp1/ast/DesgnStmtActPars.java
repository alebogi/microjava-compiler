// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:23:35


package rs.ac.bg.etf.pp1.ast;

public class DesgnStmtActPars extends DesgStmtEnd {

    private ActParsExists ActParsExists;

    public DesgnStmtActPars (ActParsExists ActParsExists) {
        this.ActParsExists=ActParsExists;
        if(ActParsExists!=null) ActParsExists.setParent(this);
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
        if(ActParsExists!=null) ActParsExists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsExists!=null) ActParsExists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsExists!=null) ActParsExists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesgnStmtActPars(\n");

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