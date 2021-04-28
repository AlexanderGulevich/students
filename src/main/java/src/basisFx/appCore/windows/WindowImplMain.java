package basisFx.appCore.windows;

import basisFx.appCore.utils.Registry;

public class WindowImplMain extends WindowImpl {

    private static WindowImplMain instance;

    public WindowImplMain(WindowBuilder builder) {
        super(builder);
    }
    public static WindowImplMain getInstance(WindowBuilder builder){
        if (instance == null) {
           return instance=new WindowImplMain(builder);
        }else {
           return WindowImplMain.instance;
        }
    }
    @Override
    protected void setDefaultWidthAndHeight() {
        width=1100d;
        height=700d;
    }
    @Override
    public void customInit(WindowAbstraction windowAbstraction) {
        setWindowAbstraction(windowAbstraction);
        windowAbstraction.stage.setTitle(getTitleName());

    }

    @Override
    public void addToRegistry(WindowAbstraction windowAbstraction)
    {
        Registry.mainWindow=windowAbstraction;
    }
}
