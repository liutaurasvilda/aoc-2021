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

    public static List<Integer> asInt(String resourceName) {
        return readResource(resourceName)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> asLineInt(String resourceName) {
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
