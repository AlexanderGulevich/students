package basisFx.appCore.menu;


import lombok.Getter;

public class LeftAndTopBarDirector implements MenuDirector{

   private @Getter LeftAndTopBarItemComposite menuTree =LeftAndTopBarItemComposite.builder().build();

    @Override
    public MenuDirector setComposite(MenuComposite composite) {
        menuTree.add(composite);
        return this;
    }

    @Override
    public MenuDirector setLeaf(MenuLeaf leaf) {
        int length = menuTree.getChilds().toArray().length;
        MenuComposite composite = (MenuComposite) menuTree.getChilds().get(length-1);
        composite.add(leaf);
        return this;
    }
}
