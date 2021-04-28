package basisFx.service;

import basisFx.appCore.elements.DatePickerHandler;
import basisFx.appCore.elements.RangeDirector;
import basisFx.appCore.utils.Range;
import basisFx.appCore.utils.Registry;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ServicePanelRightControls extends ServicePanels {
    private RangeDirector rangeDirector;
    private DatePickerHandler datePickerHandlerSTART;
    private DatePickerHandler datePickerHandlerEND;
    @FXML
    private Label filtrerTitle;
    @FXML
    private JFXButton okBut;
    @FXML
    private VBox rightVbox;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private ComboBox combobox;
    @FXML
    private AnchorPane labelAnchor;
    @FXML
    private Label commonLabelName;
    @FXML
    private AnchorPane chartAnchor;

    @Override
    public void init() {

        Registry.dataExchanger.put("chartAnchor", chartAnchor);
        datePickerHandlerSTART = new DatePickerHandler(dateStart, this);
        datePickerHandlerEND = new DatePickerHandler(dateEnd, this);
        rangeDirector = new RangeDirector(combobox, this, Range.ALLTIME, Range.getAllPeriodicaly());
        setOnMousePressedOK();

    }

    private void setOnMousePressedOK() {
        okBut.setOnMousePressed(event -> {
            LocalDate start = datePickerHandlerSTART.getSelectedDate();
            LocalDate end = datePickerHandlerEND.getSelectedDate();
            if (event.isPrimaryButtonDown() && event.getClickCount() == 1 || event.getClickCount() == 2) {
                if (start != null && end != null) {

                    Calendar start2 = new GregorianCalendar(start.getYear(), start.getMonthValue() - 1, start.getDayOfMonth());
                    start2.add(Calendar.DAY_OF_MONTH, -1);
                    Calendar end2 = new GregorianCalendar(end.getYear(), end.getMonthValue() - 1, end.getDayOfMonth());
                    end2.add(Calendar.DAY_OF_MONTH, 1);
                    chartBfx.applyPeriod(end2, start2);

                    long diff= end2.getTimeInMillis()-start2.getTimeInMillis();
                    long days = diff / (1000*60*60*24);

                    if(days <90)  chartBfx.applyDayFormat();
                    if(days <180 && days>=90)  chartBfx.applyMonthYearFormat();
                    if(days >=180)  chartBfx.applyMonthYearFormat();

                }
            }
        });
    }


    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    public ServicePanelRightControls() {
        Registry.dataExchanger.put("chartPanel", this);
    }


    @Override
    public void inform(Object node) {
        if (node == rangeDirector) {
            Range range = rangeDirector.getSelectedRange();
            switch (range) {
                case ALLTIME:{
                    chartBfx.applyAllTime();
                    chartBfx.applyMonthFormat();
                };
                    break;
                case DAY30:{
                    Calendar start = Calendar.getInstance();
                    start.add(Calendar.DAY_OF_MONTH, -30);
                    Calendar end = Calendar.getInstance();  start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyDayFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case DAY60:{
                    Calendar start = Calendar.getInstance();
                    start.add(Calendar.DAY_OF_MONTH, -60);
                    start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);
                    Calendar end = Calendar.getInstance();
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyDayFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case DAY90:{
                    Calendar start = Calendar.getInstance();
                    start.add(Calendar.DAY_OF_MONTH, -90);
                    start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);
                    Calendar end = Calendar.getInstance();
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyDayFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case DAY180:{
                    Calendar start = Calendar.getInstance();
                    start.add(Calendar.DAY_OF_MONTH, -180);
                    Calendar end = Calendar.getInstance();  start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);
                    chartBfx.applyPeriod(end, start);



                    chartBfx.applyMonthFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case MONTH:{

                    Calendar start = Calendar.getInstance();
                    start.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);

                    Calendar end = Calendar.getInstance();
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyDayFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case YEAR:   {
                    Calendar start = Calendar.getInstance();
                    start.set(Calendar.DAY_OF_YEAR, 1);
                    start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);

                    Calendar end = Calendar.getInstance();
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyMonthYearFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case YEAR2:{
                    Calendar start = Calendar.getInstance();
                    start.add(Calendar.YEAR,-2);
                    start.set(Calendar.DAY_OF_YEAR, 1);
                    start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);

                    Calendar end = Calendar.getInstance();
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyMonthYearFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
                case YEAR3:{
                    Calendar start = Calendar.getInstance();
                    start.add(Calendar.YEAR,-3);
                    start.set(Calendar.DAY_OF_YEAR, 1);
                    start.clear(Calendar.HOUR);
                    start.clear(Calendar.MINUTE);
                    start.clear(Calendar.SECOND);
                    start.clear(Calendar.MILLISECOND);

                    Calendar end = Calendar.getInstance();
                    chartBfx.applyPeriod(end, start);

                    chartBfx.applyMonthYearFormat();

                    System.out.println(start.getTime());
                    System.out.println(end.getTime());
                };
                    break;
            }
        }


    }

}