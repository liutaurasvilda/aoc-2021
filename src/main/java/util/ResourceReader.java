package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ResourceReader {

    public static List<String> asString(String resourceName) {
        return readResource(resourceName)
                .collect(Collectors.toList());
    }

    public static List<List<String>> asStringList(String resourceName) {
        return readResource(resourceName)
                .map(e -> Arrays.stream(e.split(""))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<String> asStringLine(String resourceName) {
        return Arrays.stream(ResourceReader.asString(resourceName)
                        .get(0).split(","))
                .collect(Collectors.toList());
    }

    public static List<Integer> asInt(String resourceName) {
        return readResource(resourceName)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> asIntList(String resourceName) {
        return readResource(resourceName)
                .map(e -> Arrays.stream(e.split(""))
                        .map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<Integer> asIntLine(String resourceName) {
        return Arrays.stream(ResourceReader.asString(resourceName)
                .get(0).split(",")).map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Stream<String> readResource(String resourceName) {
        try {
            return Files.lines(Paths.get(ClassLoader.getSystemResource(resourceName).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Resource cannot be read");
        }
    }
}
