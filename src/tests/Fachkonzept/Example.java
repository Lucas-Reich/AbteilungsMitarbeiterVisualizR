package tests.Fachkonzept;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

public class Example {
    @CommandLine.Option(names = { "-v", "--verbose" }, description = "Be verbose.")
    private boolean verbose = false;

    @CommandLine.Parameters(arity = "1..*", paramLabel = "FILE", description = "File(s) to process.")
    private File[] inputFiles;

    public void Example() {
        String[] args = { "-v", "inputFile1", "inputFile2" };
        Example app = CommandLine.populateCommand(new Example(), args);
        assert  app.verbose;
        assert  app.inputFiles != null && app.inputFiles.length == 2;
    }
}
