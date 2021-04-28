package basisFx.appCore.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;

public class SeriesStringNumber implements Series {
    @Getter private ObservableList<XYChart.Series<String, Number>> allSets = FXCollections.observableArrayList();

    public void createSet(String name){
        XYChart.Series<String, Number> set = new XYChart.Series<>();
        set.setName(name);
        allSets.add(set);
    }
    public void add(String name, Number val){
        int length = allSets.toArray().length;
        XYChart.Series<String, Number> set = allSets.get(length - 1);
        set.getData().add(new XYChart.Data<>(name, val));
    }

}