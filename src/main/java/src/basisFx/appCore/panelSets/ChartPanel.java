package basisFx.appCore.panelSets;

import basisFx.appCore.chart.ChartBfx;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.FXMLLoader;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.service.ServicePanels;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;

@Builder
public class ChartPanel implements PanelSets {
     private WindowAbstraction<Object> currentWindow;
     private String fxmlFileName;
     private String commonLabelName;
     private AnchorPane parent;
     private ChartBfx chartBfx;


    @Override
    public void configure() {

        AnchorPane anchorPaneFromFXML = FXMLLoader.loadAnchorPane(fxmlFileName);
        ServicePanels service = (ServicePanels) Registry.dataExchanger.get(fxmlFileName);
        service.commonLabelName(commonLabelName);
        service.init();

        Coordinate coordinate=new Coordinate(0d,0d,0d,0d);
        coordinate.setChildNode(anchorPaneFromFXML);
        coordinate.setParentAnchorPane(parent);
        coordinate.bonding();

        AnchorPane chartAnchor= (AnchorPane) Registry.dataExchanger.get("chartAnchor");
        chartBfx.setParent(chartAnchor);
        chartBfx.setService(service);
        chartBfx.configure();

    }

}
