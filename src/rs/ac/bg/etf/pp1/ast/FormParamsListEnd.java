// generated with ast extension for cup
// version 0.8
// 17/8/2021 18:41:45


package rs.ac.bg.etf.pp1.ast;

public class FormParamsListEnd extends FormParsListEnd {

    private Type Type;
    private String fpListName;
    private Arr Arr;

    public FormParamsListEnd (Type Type, String fpListName, Arr Arr) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.fpListName=fpListName;
        this.Arr=Arr;
        if(Arr!=null) Arr.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFpListName() {
        return fpListName;
    }

    public void setFpListName(String fpListName) {
        this.fpListName=fpListName;
    }

    public Arr getArr() {
        return Arr;
    }

    public void setArr(Arr Arr) {
        this.Arr=Arr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Arr!=null) Arr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Arr!=null) Arr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Arr!=null) Arr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParamsListEnd(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+fpListName);
        buffer.append("\n");

        if(Arr!=null)
            buffer.append(Arr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamsListEnd]");
        return buffer.toString();
    }
}
