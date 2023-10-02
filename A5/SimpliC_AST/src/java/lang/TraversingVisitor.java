package lang;

import javax.swing.plaf.multi.MultiButtonUI;

import lang.ast.*;

/**
 * Traverses each node, passing the data to the children.
 * Returns the data unchanged.
 * Overriding methods may change the data passed and the data returned.
 */
public abstract class TraversingVisitor implements lang.ast.Visitor {
    
    protected Object visitChildren(ASTNode node, Object data) {
		for (int i = 0; i < node.getNumChild(); ++i) {
			node.getChild(i).accept(this, data);
		}
		return data;
	}

    public Object visit(List node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Opt node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Program node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Function node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Argument node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Binding node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Declaration node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(ReturnState node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(FunctionCallStmt node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(If node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Else node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(While node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(IdDecl node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(LessThan node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(LessThanEqual node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(GreaterThan node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(GreaterThanEqual node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Equals node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(NotEquals node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Sum node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Sub node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Mul node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Div node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(Mod node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(FunctionCall node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Minus node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Parenthesis node, Object data) {
		return visitChildren(node, data);
	}
    public Object visit(IdUse node, Object data) {
		return visitChildren(node, data);
	}
	public Object visit(Nbr node, Object data) {
		return visitChildren(node, data);
	}
}
