package basisFx.appCore.menu;

import basisFx.appCore.settings.CSSid;
import basisFx.appCore.utils.Coordinate;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

public abstract class MenuRepresent {

    @Setter protected CSSid css;
    @Setter protected MenuComponent component;
    @Setter protected AnchorPane parentAnchor;
    @Setter protected Coordinate coordinate;
    @Setter protected Double width;
    @Setter protected Double height;

    protected abstract <T> void   makeStructuredMenuView(MenuComponent c, MenuComposite parentMenu );



}
