package tests.Fachkonzept;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

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
    @CommandLine.Command(description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
            name = "checksum", mixinStandardHelpOptions = true, version = "checksum 3.0")
    class CheckSum implements Callable<Void> {

        @Parameters(index = "0", description = "The file whose checksum to calculate.")
        private File file;

        @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
        private String algorithm = "MD5";

        public void main(String[] args) throws Exception {
            // CheckSum implements Callable, so parsing, error handling and handling user
            // requests for usage help or version help can be done with one line of code.
            CommandLine.call(new CheckSum(), args);
        }

        @Override
        public Void call() throws Exception {
            // your business logic goes here...
            byte[] fileContents = Files.readAllBytes(file.toPath());
            byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
            //System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(digest));
            return null;
        }
    }
}
