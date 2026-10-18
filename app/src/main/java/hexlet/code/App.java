package hexlet.code;

import picocli.CommandLine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable <Integer> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @CommandLine.Parameters(index = "0", paramLabel  = "filepath1", description = "path to first file")
    private Path filepath1;

    @CommandLine.Parameters(index = "1", paramLabel  = "filepath2", description = "path to second file")
    private Path filepath2;

    @CommandLine.Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format", defaultValue = "stylish",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private String format;

    @Override
    public Integer call() throws Exception {
        Path first  = filepath1.toAbsolutePath().normalize();
        Path second = filepath2.toAbsolutePath().normalize();

        for (Path p : List.of(first, second)) {
            if (!Files.isRegularFile(p)) {
                System.err.println("Файл не найден: " + p);
                return CommandLine.ExitCode.USAGE;
            }
        }


        Map<String, Object> map1 = getData(first);
        Map<String, Object> map2 = getData(second);

        System.out.println(map1);
        System.out.println(map2);

        return CommandLine.ExitCode.OK;
    }

    private Map<String, Object> getData(Path path) throws IOException {
        return MAPPER.readValue(path.toFile(), Map.class);
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("Hello World!");
        int exitCode=new CommandLine(new App()).execute(args);
        System.exit(exitCode);


    }


}