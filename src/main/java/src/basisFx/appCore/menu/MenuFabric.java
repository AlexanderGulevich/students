package basisFx.appCore.menu;

public  class MenuFabric {

    public  static LeftAndTopBarRepresetation menuLeftAndTop(MenuSketch sketch){
        return new LeftAndTopBarRepresetation(sketch);
    }

    public  static LeftRepresetation menuLeft(MenuSketch sketch){
        return new LeftRepresetation(sketch);
    }


}
