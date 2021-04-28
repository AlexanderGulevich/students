package basisFx.appCore.menu;

public interface MenuDirector {
    MenuDirector setComposite(MenuComposite composite);
    MenuDirector setLeaf(MenuLeaf leaf);
}
