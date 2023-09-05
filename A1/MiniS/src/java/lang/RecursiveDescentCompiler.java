package lang;
import lang.ast.LangParser;
import lang.ast.LangScanner;
import static lang.ast.LangParser.Terminals.*;

//Abstract base class for recursive decent parsers.
//You should implement the parseProgram() method to parse a MiniS program.


public abstract class RecursiveDescentCompiler {
	private LangScanner scanner;
	private beaver.Symbol currentToken;

	//Initialize the parser and start parsing via the parseProgram() method.
	public void parse(LangScanner scanner) {
		this.scanner = scanner;
		parseProgram();
		accept(EOF); // Ensure all input is processed.
	}
	
	protected abstract void parseProgram(){
		accept();
		int cToken;
		int testToken;
		cToken = peek();
		while(peek() != 0){
			switch(cToken){
				case 9://for
				       accept(9);
				       accept(3);
				       accept(11);
				       expression();
				       accept(4);
				       expression();
				       accept(5);
				       parseProgram();
				       accept(1);
				       break;
				case 10://if
					accept(10);
					expression();
					accept(6);
					parseProgram();
					accept(2);
					break;
				case 3://assignment
			       		accept(3);
			       		accept(11);
					expression();
			       		break;
				default:
					error("Syntax Error");

					

		}
		return;
	}

	void expression(){
		if(peek() == 3 || peek() == 7){
			accept();
		}else if(peek() == 8){
			accept();
			if(peek() == 3 || peek() == 7){
				accept();
			} else {
				error("Syntax Error");
			}
		}else{
			error("Syntax Error");
		}
	}
		


	
	//Returns the current token without proceeding to the next.
	protected int peek() {
		if (currentToken == null) accept();
		return currentToken.getId();
	}
	
	//Read the next token from the scanner.
	protected void accept() {
		try {
			currentToken = scanner.nextToken();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//Ensure the current token is of a certain type; then read the next.
	protected void accept(int expectedToken) {
		if (peek() != expectedToken) {
			error("expected token " + LangParser.Terminals.NAMES[expectedToken] + " got token " + LangParser.Terminals.NAMES[currentToken.getId()]);
		}
		accept();
	}

	protected static void error(String message) {
		throw new RuntimeException(message);
	}
}
