package basisFx.appCore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {
    public SystemProperties() {
        try {
            Properties properties = new Properties();
            String elementPath = System.getProperty("user.dir") + "/"+"/src/config.properties";
            elementPath = elementPath.replace("\\","/");
            File file = new File(elementPath);
            if (!file.exists()) {
                file.createNewFile();
            }

            //Загружаем свойства из файла
            properties.load(new FileInputStream(file));
            //Получаем в переменную значение конкретного свойства
            String host = properties.getProperty("host");
            //Устанавливаем значение свойста
            properties.setProperty("host", "localhost:8080");
            //Сохраняем свойства в файл.
            properties.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
