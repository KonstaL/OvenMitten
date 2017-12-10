package fi.konstal.engine.util;

import java.io.InputStream;
import java.net.URL;

public class Fileloader {
//    public static <T> T getFile(String filename) {
//        String is = Thread.currentThread().getContextClassLoader().toString();
//        T toReturn = null;
//
//        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
//            toReturn = ImageIO.read(is);
//        } catch (IOException e) {
//            System.out.println("Could not load sprite sheet image: " + filename);
//            e.printStackTrace();
//        }
//        return test;
//    }

    public static String getFileLocation(String filename) {
        URL location = Fileloader.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation();

        return  location.toString() + filename;
    }
}
