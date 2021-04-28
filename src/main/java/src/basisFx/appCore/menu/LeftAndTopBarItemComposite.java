package basisFx.appCore.menu;

import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.PanelCreator;
import basisFx.appCore.settings.FontsStore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
public class LeftAndTopBarItemComposite implements MenuComposite{
    @Getter @Builder.Default ArrayList<MenuComponent> childs =new ArrayList<>();
    @Getter private PanelCreator panelCreator;
    @Getter @Setter  private MenuComponent parent;
    @Getter @Setter private boolean isActive;
    @Setter @Getter private CallBack callBack;
    @Getter private String fxmlFileName;
    @Getter private String fontSymbol;
    @Getter private String description;
    @Getter private FontsStore fontsStore;
    @Getter private double fontSize;

    @Override
    public boolean hasParent() {
        if (parent != null) {
            return true;
        }
        return false;
    }


}
