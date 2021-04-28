package basisFx.appCore.utils;


import java.net.URL;

public class PathToFileUtils {
    private  static  PathToFileUtils pathToFileUtils= new PathToFileUtils();

    public  static String getFilePath(String str){
        String path=null;
        switch (System.getProperty("os.name")) {
            case "Linux":  path = "file:" + System.getProperty("user.dir") + str;;
                break;
            case "Windows":  path = "file:/" + System.getProperty("user.dir") + str;;
                break;
        }
        path=path.replace("\\","/");
        return path;

    }
    public  static String getAbsolutePath(String str){
        String path=null;
        switch (System.getProperty("os.name")) {
            case "Linux":  path = System.getProperty("user.dir") + str;;
                break;
            case "Windows":  path =  System.getProperty("user.dir") + str;;
                break;
        }
        path=path.replace("\\","/");
        return path;

    }
    public  static String getResourseExternalForm(String str){
        return pathToFileUtils.getClass().getResource(str).toExternalForm();

    }
    public  static URL getUrl(String str){
        return pathToFileUtils.getClass().getResource(str);

    }
}
