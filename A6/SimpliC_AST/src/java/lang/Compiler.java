package lang;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;

import beaver.Parser.Exception;

import lang.ast.Program;
import lang.ast.LangParser;
import lang.ast.LangScanner;
import lang.ast.ErrorMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dumps the parsed Abstract Syntax Tree of a Calc program.
 */
public class Compiler {
	/**
	 * Entry point
	 * @param args
	 */
    
    public static Object DrAST_root_node; //Enable debugging with DrAST
    
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.err.println(
						"You must specify a source file on the command line!");
				printUsage();
				System.exit(1);
				return;
			}

			String filename = args[0];
			LangScanner scanner = new LangScanner(new FileReader(filename));
			LangParser parser = new LangParser();
			Program program = (Program) parser.parse(scanner);
            DrAST_root_node = program; //Enable debugging with DrAST

			// // Generate Assembly file.
			// File assemblyFile = new File(filename.substring(0, filename.length()-3) + ".s");
			// PrintStream out = new PrintStream(new FileOutputStream(assemblyFile));
			// program.genCode(out);
			// out.close();

			// // Generate object file.
			// File objectFile = new File(filename.substring(0, filename.length()-3) + ".o");
			// ArrayList<String> cmdAs = new ArrayList<String>();
			// cmdAs.add("as");
			// cmdAs.add("--gstabs");
			// cmdAs.add(assemblyFile.getAbsolutePath());
			// cmdAs.add("-o");
			// cmdAs.add(objectFile.getAbsolutePath());
			// //execute(cmdAs);

			// // Link object file and generate executable file.
			// File execFile = new File(filename.substring(0, filename.length()-3));
			// ArrayList<String> cmdLd = new ArrayList<String>();
			// cmdLd.add("ld");
			// cmdLd.add(objectFile.getAbsolutePath());
			// cmdLd.add("-o");
			// cmdLd.add(execFile.getAbsolutePath());

			//System.out.println(program.dumpTree());
			//System.out.println(String.valueOf(MSN.maximum(program)));
			//program.prettyPrint(System.out);
			if (!program.errors().isEmpty()) {
				System.err.println();
				System.err.println("Errors: ");
				for (ErrorMessage e: program.errors()) {
					System.err.println("- " + e);
				}
				System.exit(1);
			}
			else {
				program.genCode(System.out);
			}
			//System.out.println("No errors found.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void printUsage() {
		System.err.println("Usage: DumpTree FILE");
		System.err.println("  where FILE is the file to be parsed");
	}
}

