package basisFx.appCore.utils;

import java.io.File;
import java.io.IOException;

import javafx.stage.DirectoryChooser;
import lombok.Getter;

public class DirectoryChooserWrapper {
    
    DirectoryChooser directoryChooser   =new DirectoryChooser();
    @Getter
    private String path=null;

    public DirectoryChooserWrapper() {

            File dir= directoryChooser.showDialog(null);
            directoryChooser.setTitle("Выберите папку");
            try {
                if (dir != null) {
                    path = dir.getCanonicalPath();
                }

            } catch (IOException ex) {
                Registry.windowFabric.infoWindow("Что-то пошло не так при выборе директории!") ;
            }
    }
    
    public void setInitialDirectory(String dir){
        directoryChooser.setInitialDirectory(new File(dir));
    }


    
    
    
    
    
    
}
