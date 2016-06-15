//CHECKSTYLE:OFF
package kz.mix.e804.fileIO.fileVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class MyFileVisitor extends SimpleFileVisitor<Path> {
    private static int count = 0;

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("file name:" + path.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("------- Directory name:" + path + " -------");
        return FileVisitResult.CONTINUE;
    }
}

public class FileTreeWalk {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: FileWalkTree <source-path>");
            System.exit(-1);
        }
        Path pathSource = Paths.get(args[0]);
        try {
            Files.walkFileTree(pathSource, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
