package basisFx.appCore.elements;

import basisFx.appCore.utils.Registry;
import basisFx.appCore.interfaces.Mediator;
import javafx.scene.control.TextField;
import lombok.Getter;


public class TextFieldHandler {
    private TextField textField;
    @Getter
    private String text;
    private Mediator mediator;


    public TextFieldHandler(TextField textField, Mediator mediator) {
        this.textField = textField;
        this.mediator = mediator;


        textField.textProperty().addListener((obs, oldText, newText) -> {
            text = textField.getText();
            mediator.inform(textField);
        });

    }

    public boolean checkDoubleValue( ) {
        String s=text;
        try {
            s=s.trim();
            if(s.contains(",")){
                s=s.replace(',','.');
            }
            Double v=Double.parseDouble(s);
        }catch (NumberFormatException   e){
            Registry.windowFabric.infoWindow("Вы ввели в поле ввода неправильное значение!" +
                    "\n" +"Введенное значение - " +"\" "+s+" \"");
            return false;
        }
        return  true;
    }
}
