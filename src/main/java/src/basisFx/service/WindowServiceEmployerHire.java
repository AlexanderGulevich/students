package basisFx.service;

import basisFx.appCore.elements.DatePickerHandler;
import basisFx.appCore.elements.TextFieldHandler;
import basisFx.appCore.events.CallBackEvent;
import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.UnitOfWork;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.domain.EmployeesRatePerHour;
import basisFx.domain.Employer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class WindowServiceEmployerHire extends WindowService {
    private UnitOfWork unitOfWork=new UnitOfWork();
    private  DatePickerHandler datePickerHandler;
    private  TextFieldHandler rateFieldHandler;
    private  TextFieldHandler fioHandler;
    @FXML private Button okBut;
    @FXML private Button closeBut;
    @FXML private DatePicker dateForHiringDatePicker;
    @FXML private TextField rateField;
    @FXML private TextField fioField;
    @FXML private AnchorPane titlePanel;

    @FXML public void initialize() {
    }

    public WindowServiceEmployerHire() {
        Registry.crossWindowMediators.put("EmployerHire",this);
    }

    @Override
    public void init() {
        initCloseButtonEvent();
        initDatePickersHadler();
        initTextFieldsHandlers();
        initStageDragging();
        initOkButEvent();
    }

    @Override
    public void inform(Object node) {
        if (node == rateField) {
            rateFieldHandler.checkDoubleValue();
        }
    }

    private void accept() {

        Employer employer=new Employer();
        employer.setName(fioHandler.getText());
        employer.setIsFired(new BoolComboBox(false));

        unitOfWork.registerNew(employer);
        unitOfWork.commit();

        Integer maxIdFromTable = ActiveRecord.getMaxIdFromTable(employer);

        EmployeesRatePerHour perHour=new EmployeesRatePerHour();
        perHour.setEmployerId(maxIdFromTable);
        perHour.setRate(Double.valueOf(rateFieldHandler.getText()));
        perHour.setStartDate(datePickerHandler.getSelectedDate());

        unitOfWork.registerNew(perHour);
        unitOfWork.commit();

    }
    private void initOkButEvent() {
        CallBackEvent callBackEvent = new CallBackEvent(
                ()->{
                    accept();
                    informParentWindowAboutClosing();
                    closeBut.fire();
                    closeBut.arm();
                });
        callBackEvent.setEventToElement(okBut);
    }


    private void initCloseButtonEvent() {
        ClosePopupAndSubWindow closePopupAndSubWindow = new ClosePopupAndSubWindow();
        closePopupAndSubWindow.setMediator(this);
        closePopupAndSubWindow.setEventToElement(closeBut);
        closePopupAndSubWindow.setSleepMillis(1000);


    }
    private void initDatePickersHadler() {
        datePickerHandler=new DatePickerHandler(dateForHiringDatePicker,this);
    }

    private void initTextFieldsHandlers() {
        rateFieldHandler=new TextFieldHandler(rateField,this);
        fioHandler=new TextFieldHandler(fioField,this);
    }
    private void initStageDragging() {
        new StageDragging().setEventToElement(titlePanel);
    }

}
