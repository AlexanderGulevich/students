package basisFx.appCore.menu;


public  interface MenuLeaf extends MenuComponent{

    @Override
    default boolean isComposit() {
        return false;
    }

}
