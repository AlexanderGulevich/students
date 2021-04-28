package basisFx.appCore.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;

import java.util.GregorianCalendar;

public class SeriesNumberDate implements Series {

    @Getter  private ObservableList<XYChart.Series<Long, Number>> allSets = FXCollections.observableArrayList();

    public void createSet(String name){
        ObservableList<XYChart.Data<Long, Number>> data = FXCollections.observableArrayList();
        allSets.add(new XYChart.Series<>(name, data));
    }
    public void add(GregorianCalendar calendar,Number val ){
        int length = allSets.toArray().length;
        XYChart.Series<Long, Number> set = allSets.get(length - 1);
        set.getData().add(  new XYChart.Data<>(calendar.getTime().getTime(), val));
    }

}
