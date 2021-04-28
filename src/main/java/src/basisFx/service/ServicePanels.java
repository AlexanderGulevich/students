package basisFx.service;

import basisFx.appCore.chart.ChartBfx;
import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.interfaces.Mediator;
import basisFx.appCore.interfaces.Observer;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

public abstract class ServicePanels implements Mediator {

    protected WindowAbstraction currentWindow;
    protected AnchorPane dynamicContentAnchor;
    @Setter protected CallBack callBack;
    @Setter protected CallBackParametrized callBackParametrized;
    @Setter protected ChartBfx chartBfx;
    public void setCurrentWindow(WindowAbstraction currentWindow) {
        this.currentWindow = currentWindow;
    }


    public abstract void init();
    public abstract void commonLabelName(String name);
    @Override
    public void inform(Object node) {

    }
}