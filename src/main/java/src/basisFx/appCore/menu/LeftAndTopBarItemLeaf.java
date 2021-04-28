package basisFx.appCore.menu;

import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.PanelCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
public class LeftAndTopBarItemLeaf implements MenuLeaf {
    @Getter
    private PanelCreator panelCreator;
    @Getter
    @Setter
    private MenuComponent parent;
    @Getter
    @Setter
    private boolean isActive;
    @Getter
    private boolean hasParent;
    @Getter
    private String name;

    @Setter
    @Getter private CallBack callBack;

    @Override
    public boolean hasParent() {
        if (parent != null) {
            return true;
        }
        return false;
    }
}

