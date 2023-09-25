package lang;

import lang.ast.*;

public class MSN extends TraversingVisitor{
    static int count;
    static int maxMSN;

    static int maximum(Program root){
        count = 0;
        maxMSN = 0;

        root.accept(new MSN(), null);

        return maxMSN;
    }

    public Object visit(Function node, Object data) {
        count++;
        super.visit(node, data);
        if (count > maxMSN) {
            maxMSN = count;
        }
        count--;
        return null;
	}
    public Object visit(While node, Object data) {
        count++;
        super.visit(node, data);
        if (count > maxMSN) {
            maxMSN = count;
        }
        count--;
        return null;
	}
    public Object visit(If node, Object data) {
        count++;
        super.visit(node, data);
        if (count > maxMSN) {
            maxMSN = count;
        }
        count--;
        return null;
	}
    

}