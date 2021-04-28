package basisFx.appCore.windows;

import basisFx.service.WindowServiceInfo;

public class WindowImplDatePicker extends WindowImpl{


    public WindowImplDatePicker(WindowBuilder builder) {
        super(builder);

    }

    @Override
    protected void setDefaultWidthAndHeight() {
    }

    @Override
    public void customInit(WindowAbstraction windowAbstraction) {
        setWindowAbstraction(windowAbstraction);
        windowAbstraction.stage.setAlwaysOnTop(true);
        windowAbstraction.stage.setTitle(getTitleName());

    }

    @Override
    public void addToRegistry(WindowAbstraction windowAbstraction) {

    }
}
