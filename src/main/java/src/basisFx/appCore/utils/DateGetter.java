package basisFx.appCore.utils;

import basisFx.appCore.interfaces.Mediator;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

import java.time.LocalDate;

public abstract class DateGetter implements Mediator {

    @Setter
    protected Mediator mediator;
    @Setter
    protected AnchorPane parentAnchor;
    @Setter
    protected Coordinate coordinate;

    public abstract LocalDate getDate();
    public abstract void init();

}
