// generated with ast extension for cup
// version 0.8
// 21/7/2021 18:50:58


package rs.ac.bg.etf.pp1.ast;

public class ConstantsListEnd extends ConstListEnd {

    private String constEndName;
    private Consts Consts;

    public ConstantsListEnd (String constEndName, Consts Consts) {
        this.constEndName=constEndName;
        this.Consts=Consts;
        if(Consts!=null) Consts.setParent(this);
    }

    public String getConstEndName() {
        return constEndName;
    }

    public void setConstEndName(String constEndName) {
        this.constEndName=constEndName;
    }

    public Consts getConsts() {
        return Consts;
    }

    public void setConsts(Consts Consts) {
        this.Consts=Consts;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Consts!=null) Consts.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Consts!=null) Consts.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Consts!=null) Consts.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantsListEnd(\n");

        buffer.append(" "+tab+constEndName);
        buffer.append("\n");

        if(Consts!=null)
            buffer.append(Consts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantsListEnd]");
        return buffer.toString();
    }
}
