package basisFx.appCore.utils;

import javafx.stage.FileChooser;

public class ImgExstensions implements Extensions {
    @Override
    public FileChooser.ExtensionFilter get() {
        return    new FileChooser.ExtensionFilter(
                "files", "*.jpeg","*.jpg", "*.png","*.gif"
        );
    }
}
