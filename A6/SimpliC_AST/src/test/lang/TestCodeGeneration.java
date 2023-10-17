package lang;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lang.ast.Program;

/**
 * This is a parameterized test: one test case is generated for each input
 * file found in TEST_DIRECTORY. Input files should have the ".in" extension.
 * @Author Niklas Fors
 */
@RunWith(Parameterized.class)
public class TestCodeGeneration {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles/codegen");

	private final String filename;
	public TestCodeGeneration(String testFile) {
		filename = testFile;
	}

	@Test public void runTest() throws IOException, Exception {
		Program program = (Program) Util.parse(new File(TEST_DIRECTORY, filename));

		assertEquals("[]", program.errors().toString());

		// Generate Assembly file.
		File assemblyFile = new File(TEST_DIRECTORY, Util.changeExtension(filename, ".s"));
		PrintStream out = new PrintStream(new FileOutputStream(assemblyFile));
		program.genCode(out);
		out.close();

		// Generate object file.
		File objectFile = new File(TEST_DIRECTORY, Util.changeExtension(filename, ".o"));
		ArrayList<String> cmdAs = new ArrayList<String>();
		cmdAs.add("as");
		cmdAs.add("--gstabs");
		cmdAs.add(assemblyFile.getAbsolutePath());
		cmdAs.add("-o");
		cmdAs.add(objectFile.getAbsolutePath());
		execute(cmdAs);

		// Link object file and generate executable file.
		File execFile = new File(TEST_DIRECTORY, Util.changeExtension(filename, ".elf"));
		ArrayList<String> cmdLd = new ArrayList<String>();
		cmdLd.add("ld");
		cmdLd.add(objectFile.getAbsolutePath());
		cmdLd.add("-o");
		cmdLd.add(execFile.getAbsolutePath());
		execute(cmdLd);

		// Run the executable file and compare output:
		String actual = execute(Arrays.asList(execFile.getAbsolutePath()));
		Util.compareOutput(actual,
				new File(TEST_DIRECTORY, Util.changeExtension(filename, ".out")),
				new File(TEST_DIRECTORY, Util.changeExtension(filename, ".expected")));
	}

	private String execute(List<String> cmd) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(cmd);
		Process process = pb.start();
		process.getOutputStream().close();
		process.waitFor();

		String standardError = inputStreamToString(process.getErrorStream());
		assertEquals(
			  "Standard error was not empty when running command '" + cmd.get(0) + "'",
			  "", standardError);
		assertEquals(
			  "Exit code was not zero (error occured) when running command '" + cmd.get(0) + "'",
			  0, process.exitValue());

		return inputStreamToString(process.getInputStream());
	}

	private String inputStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return sb.toString();
	}

	@Parameters(name = "{0}")
	public static Iterable<Object[]> getTests() {
		return Util.getTestParameters(TEST_DIRECTORY, ".in");
	}
}