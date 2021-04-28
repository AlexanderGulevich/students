package basisFx.appCore.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
    private static Properties properties = new Properties();
    private static String path = ( System.getProperty("user.dir") +"/properties.txt").replace("\\","/");
    private static FileOutputStream out = null;
    private static FileInputStream in=null;


    public static void store() {
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.store(out, "Properties");
            System.out.println("\nСвойства".toUpperCase());
            properties.list(System.out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load() throws IOException {
            in = new FileInputStream(path);
            properties.load(in);
            in.close();
    }



    public static void run() {
        createFile();
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setProperty(String k,String v) {
        properties.put(k, v);
    }

    public static String getProperty(String s) {
      return properties.getProperty(s, null);
    }

    private static void createFile() {
        File file = new File(path);
        FileUtils.fileChecking(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Registry.windowFabric.infoWindow("File have not created \n"+ e.getMessage());
            }
        }
    }
}
