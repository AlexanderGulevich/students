package basisFx.appCore.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lombok.Getter;

public class SeriesToPie implements Series {
    @Getter private ObservableList<PieChart.Data> allSets = FXCollections.observableArrayList();

    public void add(String name, double val){

        allSets.add(new PieChart.Data(name, val)

        );
    }

}
