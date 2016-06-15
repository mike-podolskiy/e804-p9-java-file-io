//CHECKSTYLE:OFF
package kz.mix.e804.fileIO.fileVisitor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class KeepAnEye {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Podolskiy.Mikhail\\Desktop\\1\\12");
        WatchService watchService = null;
        try {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // infinite loop
        for (;;) {
            WatchKey key = null;
            try {
                key = watchService.take();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            // iterate for each event
            for (WatchEvent<?> event : key.pollEvents()) {
                switch (event.kind().name()) {
                    case "OVERFLOW":
                        System.out.println("We lost some events");
                        break;
                    case "ENTRY_MODIFY":
                        System.out.println("File " + event.context() + " is changed!");
                        break;
                }
            }
            // resetting the key is important to receive subsequent notifications
            key.reset();
        }
    }
}
