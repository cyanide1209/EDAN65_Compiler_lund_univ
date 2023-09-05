package lang;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.fail;

public class ParseTests {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles");

	@Test public void exampleprogram() {
		Util.testValidSyntax(TEST_DIRECTORY, "exampleprogram.in");
	}

	@Test public void parseError() {
		Util.testSyntaxError(TEST_DIRECTORY, "parseError.in");
	}

	@Test public void scanError() {
		Util.testSyntaxError(TEST_DIRECTORY, "scanError.in");
	}

	@Test public void shortestprogram() {
		Util.testValidSyntax(TEST_DIRECTORY, "shortestprogram.in");
	}
}
