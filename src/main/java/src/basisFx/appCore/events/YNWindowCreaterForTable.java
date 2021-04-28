package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.windows.WindowFabric;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class YNWindowCreaterForTable extends AppEvent implements TableEvents{
    protected AppEvent event;
    private String mess;
    private Button but;
    @Setter @Getter
    private TableWrapper tableWrapper;

    public YNWindowCreaterForTable(AppEvent event, String mess ) {
        this.event=event;
        this.mess = mess;
    }
    @Override
    public void setEventToElement(AppNode node) {
        but=(Button) node.getElement();
        but.setOnMouseClicked((event) -> {
            run();
        });
    }
    @Override
    public void setEventToElement(Node node) {
        but=(Button) node;
        but.setOnMouseClicked((event) -> {
            run();
        });
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
        ((TableEvents) event).setTableWrapper(tableWrapper);
        WindowFabric.WindowUndecorated().dialogWindow(  mess, (t) -> {
            if(t)  event.run(); }
            );
    }


}