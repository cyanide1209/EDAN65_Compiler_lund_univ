package lang;

import java.io.File;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lang.ast.Program;


@RunWith(Parameterized.class)
public class MSNTest {
  /** Directory where the test input files are stored. */
  private static final File TEST_DIRECTORY = new File("testfiles/MSN");

  private final String filename;
  public MSNTest(String testFile) {
    filename = testFile;
  }

  @Test public void runTest() throws Exception {
    Program program = (Program) Util.parse(new File(TEST_DIRECTORY, filename));
    String actual = String.valueOf(MSN.maximum(program));
    PrintStream outStream = new PrintStream();
    outStream.println("Msn Max: " + MSN.maximum(program));
    Util.compareOutput(actual,
        new File(TEST_DIRECTORY, Util.changeExtension(filename, ".out")),
        new File(TEST_DIRECTORY, Util.changeExtension(filename, ".expected")));
  }

  @Parameters(name = "{0}")
  public static Iterable<Object[]> getTests() {
    return Util.getTestParameters(TEST_DIRECTORY, ".in");
  }
}
