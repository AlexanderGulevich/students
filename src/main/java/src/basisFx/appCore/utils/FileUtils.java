package basisFx.appCore.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class FileUtils {


    public static void fileChecking(File file)  {

        boolean directory = file.isDirectory();
        boolean absolute = file.isAbsolute();
        boolean isfile = file.isFile();
        boolean hidden = file.isHidden();
        boolean exists = file.exists();

        URI uri = file.toURI();
        try {
            URL url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
