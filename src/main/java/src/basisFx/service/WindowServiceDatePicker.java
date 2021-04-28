package basisFx.service;

import basisFx.appCore.elements.DatePickerHandler;
import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.utils.Registry;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class WindowServiceDatePicker extends WindowService {
    @FXML private JFXButton okBut;
    @FXML private Label title;
    @FXML private Label dateLsabel;
    @FXML private AnchorPane titleAnchor;
    @FXML private DatePicker datePicker;
    ClosePopupAndSubWindow closePopupAndSubWindow;
    DatePickerHandler datePickerHandler;

    @FXML public void initialize() {

    }
    public static void closeIfExist(){
        WindowServiceDatePicker windowService = (
                WindowServiceDatePicker) Registry.crossWindowMediators.get("SelectDate");
            if (windowService != null)  {
                windowService.closeWithoutChecking();
            }
    }
    public WindowServiceDatePicker() {
        Registry.crossWindowMediators.put("SelectDate",this);
    }
    @Override
    public void init() {
        new StageDragging().setEventToElement(titleAnchor);
        datePickerHandler=new DatePickerHandler(datePicker,this);
        setCloseEvent();
        dateLsabel.setText("Дата для проводок");
    }

    private void setCloseEvent() {
        closePopupAndSubWindow = new ClosePopupAndSubWindow();
        closePopupAndSubWindow.setEventToElement(okBut);
        closePopupAndSubWindow.setCallBackTyped(() ->
        {
            if (datePicker.getValue() != null) {
                return true;
            }
            return false;
        });
    }

    @Override
    public void inform(Object node) {
        if (node==datePickerHandler){
            LocalDate localDate = datePicker.getValue();
//            dateLsabel.setText(
//                    localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
//            );
            Registry.dataExchanger.put("SelectedDateFromPopup",localDate);
        }
        }

    @Override
    public void close() {
        closePopupAndSubWindow.run();
        super.close();
    }

    public void closeWithoutChecking() {
        closePopupAndSubWindow.setCallBackTyped(() -> true);
        closePopupAndSubWindow.run();
        super.close();
    }


}
