package basisFx.appCore.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;

public class SeriesNumberNumber implements Series {
    @Getter private ObservableList<XYChart.Series<Number, Number>> allSets = FXCollections.observableArrayList();

    public void createSet(String name){
        XYChart.Series<Number, Number> set = new XYChart.Series<>();
        set.setName(name);
        allSets.add(set);
    }
    public void add(Number x, Number y){
        int length = allSets.toArray().length;
        XYChart.Series<Number, Number> set = allSets.get(length - 1);
        set.getData().add(new XYChart.Data<>(x, y));
    }

}
