// generated with ast extension for cup
// version 0.8
// 18/7/2021 0:4:5


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(VarDeclListEnd VarDeclListEnd);
    public void visit(Mulop Mulop);
    public void visit(MethodDecl MethodDecl);
    public void visit(ElseExists ElseExists);
    public void visit(Arr Arr);
    public void visit(ConstListEnd ConstListEnd);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(CondTermOrList CondTermOrList);
    public void visit(MulopFactorList MulopFactorList);
    public void visit(MethodType MethodType);
    public void visit(ActParsExists ActParsExists);
    public void visit(MinusExists MinusExists);
    public void visit(RelopExprExists RelopExprExists);
    public void visit(Addop Addop);
    public void visit(ExprListEnd ExprListEnd);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(ConstList ConstList);
    public void visit(Designator Designator);
    public void visit(FormParsList FormParsList);
    public void visit(Condition Condition);
    public void visit(DesgnList DesgnList);
    public void visit(ListOfStmt ListOfStmt);
    public void visit(StmtList StmtList);
    public void visit(ExprList ExprList);
    public void visit(DesgStmtEnd DesgStmtEnd);
    public void visit(ListOfVarDecl ListOfVarDecl);
    public void visit(FormParsListEnd FormParsListEnd);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(DeclsList DeclsList);
    public void visit(DesgnListEnd DesgnListEnd);
    public void visit(ActPars ActPars);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(CondFactAndList CondFactAndList);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ClassDecl ClassDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Decls Decls);
    public void visit(ExtendsClass ExtendsClass);
    public void visit(Consts Consts);
    public void visit(FormPars FormPars);
    public void visit(ExprArrExists ExprArrExists);
    public void visit(ExprExists ExprExists);
    public void visit(AddopTermList AddopTermList);
    public void visit(ModOp ModOp);
    public void visit(DivOp DivOp);
    public void visit(OpMul OpMul);
    public void visit(SubOp SubOp);
    public void visit(OpAdd OpAdd);
    public void visit(RelopLE RelopLE);
    public void visit(RelopL RelopL);
    public void visit(RelopGE RelopGE);
    public void visit(RelopG RelopG);
    public void visit(RelopNE RelopNE);
    public void visit(RelopE RelopE);
    public void visit(AssignOperator AssignOperator);
    public void visit(DesgnListEndExpr DesgnListEndExpr);
    public void visit(DesignListEnd DesignListEnd);
    public void visit(NoDesignList NoDesignList);
    public void visit(DesignList DesignList);
    public void visit(Design Design);
    public void visit(NoArrExprExists NoArrExprExists);
    public void visit(ArrExprExists ArrExprExists);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNew FactorNew);
    public void visit(FactorBoolConst FactorBoolConst);
    public void visit(FactorCharConst FactorCharConst);
    public void visit(FactorNumConst FactorNumConst);
    public void visit(FactorDesign FactorDesign);
    public void visit(NoMullopFactList NoMullopFactList);
    public void visit(MullopFactList MullopFactList);
    public void visit(Term Term);
    public void visit(NoListAddopTerm NoListAddopTerm);
    public void visit(ListAddopTerm ListAddopTerm);
    public void visit(NoMinusExs NoMinusExs);
    public void visit(MinusExs MinusExs);
    public void visit(Expression Expression);
    public void visit(NoRelopExprExist NoRelopExprExist);
    public void visit(RelopExprExist RelopExprExist);
    public void visit(ConditionFact ConditionFact);
    public void visit(NoCondFactListAnd NoCondFactListAnd);
    public void visit(CondFactListAnd CondFactListAnd);
    public void visit(ConditionTerm ConditionTerm);
    public void visit(NoCondTermLisOr NoCondTermLisOr);
    public void visit(CondTermListOr CondTermListOr);
    public void visit(Cond Cond);
    public void visit(ExpListEnd ExpListEnd);
    public void visit(NoExpListComma NoExpListComma);
    public void visit(ExpListComma ExpListComma);
    public void visit(ActParams ActParams);
    public void visit(NoActParamsExists NoActParamsExists);
    public void visit(ActParamsExists ActParamsExists);
    public void visit(DesgnStmtDecr DesgnStmtDecr);
    public void visit(DesgnStmtIncr DesgnStmtIncr);
    public void visit(DesgnStmtActPars DesgnStmtActPars);
    public void visit(DesgnStmtAsgnOp DesgnStmtAsgnOp);
    public void visit(DesignStmt DesignStmt);
    public void visit(NoStatementList NoStatementList);
    public void visit(StatementList StatementList);
    public void visit(NoExprExists NoExprExists);
    public void visit(ExpreExists ExpreExists);
    public void visit(NoNumConstExists NoNumConstExists);
    public void visit(NumConstExists NumConstExists);
    public void visit(NoElse NoElse);
    public void visit(ElseBranch ElseBranch);
    public void visit(StmtStmt StmtStmt);
    public void visit(StmtRet StmtRet);
    public void visit(StmtCont StmtCont);
    public void visit(StmtBreak StmtBreak);
    public void visit(StmtDoWhile StmtDoWhile);
    public void visit(StmtPrint StmtPrint);
    public void visit(StmtRead StmtRead);
    public void visit(StmtIfElse StmtIfElse);
    public void visit(StmtDesgStmt StmtDesgStmt);
    public void visit(Type Type);
    public void visit(FormParamsListEnd FormParamsListEnd);
    public void visit(NoFormParsList NoFormParsList);
    public void visit(FormParamsList FormParamsList);
    public void visit(FormParams FormParams);
    public void visit(NoListOfSatemant NoListOfSatemant);
    public void visit(ListOfSatemant ListOfSatemant);
    public void visit(NoFormParsExists NoFormParsExists);
    public void visit(FormParsExists FormParsExists);
    public void visit(MethodVoid MethodVoid);
    public void visit(MethodWithType MethodWithType);
    public void visit(MethodDeclaration MethodDeclaration);
    public void visit(NoMethodsDeclarationList NoMethodsDeclarationList);
    public void visit(MethodsDeclarationList MethodsDeclarationList);
    public void visit(NoMethods NoMethods);
    public void visit(Methods Methods);
    public void visit(NoListOfVarDeclaration NoListOfVarDeclaration);
    public void visit(ListVarDeclaration ListVarDeclaration);
    public void visit(NoClassExtends NoClassExtends);
    public void visit(ClassExtends ClassExtends);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(VarDeclarationListEnd VarDeclarationListEnd);
    public void visit(NoVarDeclarationList NoVarDeclarationList);
    public void visit(VarDeclarationList VarDeclarationList);
    public void visit(NoArray NoArray);
    public void visit(Array Array);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(ConstantsListEnd ConstantsListEnd);
    public void visit(NoConstantsList NoConstantsList);
    public void visit(ConstantsList ConstantsList);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(DeclsClass DeclsClass);
    public void visit(DeclsVar DeclsVar);
    public void visit(DeclsConst DeclsConst);
    public void visit(NoDeclarationsList NoDeclarationsList);
    public void visit(DeclarationsList DeclarationsList);
    public void visit(Program Program);

}
