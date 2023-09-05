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
		cToken = peek();
		while(cToken != 0){
			switch(cToken){
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
					

			}
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
