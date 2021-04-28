package basisFx.appCore.chart;

import basisFx.service.ServicePanels;
import javafx.scene.layout.AnchorPane;

import java.util.Calendar;
import java.util.List;

public interface ChartBfx {
    void configure();
    void setParent(AnchorPane anchorPane);
    void setService(ServicePanels service);
    void setData( List data) ;
    void applyPeriod(Calendar before, Calendar after) ;
    void applyAllTime() ;
    Class getAClass() ;

    void applyYearFormat();
    void applyMonthFormat();
    void applyMonthYearFormat();
    void applyDayFormat();



}
