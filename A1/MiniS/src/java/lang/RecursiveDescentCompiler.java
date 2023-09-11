package lang;
import lang.ast.LangParser;
import lang.ast.LangScanner;
import static lang.ast.LangParser.Terminals.*;
import java.io.FileReader;
import java.io.FileNotFoundException;

//Abstract base class for recursive decent parsers.
//You should implement the parseProgram() method to parse a MiniS program.


public class RecursiveDescentCompiler extends RDPTemplate{
	private LangScanner scanner;
	private beaver.Symbol currentToken;

	public static void main(String[] args){
		RecursiveDescentCompiler r = new RecursiveDescentCompiler();
		try{
			FileReader fr = new FileReader(args[0]);
			LangScanner scannerz = new LangScanner(fr);
			r.parse(scannerz);
		} catch(FileNotFoundException f){
			System.err.println(f.getMessage());
		}
	}

	//Initialize the parser and start parsing via the parseProgram() method.
	public void parse(LangScanner scanner) {
		this.scanner = scanner;
		parseProgram();
		accept(EOF); // Ensure all input is processed.
	}
	
	protected void parseProgram(){
		accept();
		int cToken;
		int testToken;
		cToken = peek();
		while(peek() != EOF){
			switch(cToken){
				case FOR://for
				       accept(FOR);
				       accept(ID);
				       accept(ASSIGN);
				       expression();
				       accept(UNTIL);
				       expression();
				       accept(DO);
				       parseProgram();
				       accept(OD);
				       break;
				case IF://if
					accept(IF);
					expression();
					accept(THEN);
					parseProgram();
					accept(FI);
					break;
				case ID://assignment
			       		accept(ID);
			       		accept(ASSIGN);
					expression();
			       		break;
				default:
					error("Syntax Error");
					break;

					

		}
		return;
	}
}

	void expression(){
		if(peek() == ID || peek() == NUMERAL){
			accept();
		}else if(peek() == NOT){
			accept();
			if(peek() == ID || peek() == NUMERAL){
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
