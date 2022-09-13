package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileApiTest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
//         Checking if files exists
        Path homePath = Paths.get(HOME);
        Assertions.assertTrue(Files.exists(homePath));
//        Delete files and check File not Exist
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath))
            FilesUtils.deleteFiles(playPath.toFile());
        Assertions.assertTrue(Files.notExists(playPath));
//        Create Directory
        Files.createDirectory(playPath);
        Assertions.assertTrue(Files.exists(playPath));
//        Create Files
        IntStream.range(1, 10).forEach(counter -> {
            Path tempFile = Paths.get(playPath + "/temp" + counter);
            Assertions.assertTrue(Files.notExists(tempFile));
//            using try catch block for exception handling
            try {
                Files.createFile(tempFile);

            } catch (IOException e) {
                Assertions.assertTrue(Files.exists(tempFile));
            }
        });
//        List Files, Directories as well as Files with extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp"))
                .forEach(System.out::println);
    }
}
