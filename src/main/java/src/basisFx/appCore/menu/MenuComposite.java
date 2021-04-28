package basisFx.appCore.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface MenuComposite extends MenuComponent {

    ArrayList<MenuComponent>  getChilds();
    default MenuComponent add(MenuComponent... component){
        List<MenuComponent> menuComponents = Arrays.asList(component);
        for (MenuComponent c:menuComponents) {
            getChilds().add(c);
            c.setParent(this);
        }
            return this;
    }
    default void remove(MenuComponent component){
        getChilds().remove(component);
    }
    @Override
    default boolean isComposit() {
        return true;
    }

}
