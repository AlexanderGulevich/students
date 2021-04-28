package basisFx.appCore.elements;

import basisFx.appCore.interfaces.Mediator;
import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ToogleHandler {

    private JFXToggleButton toggleButton;
    private Mediator mediator;

    public ToogleHandler(JFXToggleButton toggleButton, Mediator mediator) {
        this.toggleButton = toggleButton;
        this.mediator = mediator;

        toggleButton.setOnAction((e) -> {
            mediator.inform(this);
        });


    }


}
