package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ExitCode;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

  @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
  private Path filepath1;

  @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
  private Path filepath2;

  @Option(
      names = {"-f", "--format"},
      paramLabel = "format",
      description = "output format",
      defaultValue = "stylish",
      showDefaultValue = Help.Visibility.ALWAYS)
  private String format;

  @Override
  public Integer call() throws Exception {
    Path first = filepath1.toAbsolutePath().normalize();
    Path second = filepath2.toAbsolutePath().normalize();

    for (Path p : List.of(first, second)) {
      if (!Files.isRegularFile(p)) {
        System.err.println("Файл не найден: " + p);
        return ExitCode.USAGE;
      }
    }

    String diff = Differ.generate(first.toString(), second.toString());
    System.out.println(diff);

    return ExitCode.OK;
  }

  public static void main(String[] args) throws Exception {
    // System.out.println("Hello World!");
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
