package basisFx.appCore.utils;

import javafx.stage.FileChooser;

public class OfficeExtensions implements Extensions {
    @Override
    public FileChooser.ExtensionFilter get() {
        return    new FileChooser.ExtensionFilter(
                "files", "*.xlsx","*.xls", "*.docx","*.doc"
        );
    }
}
