package basisFx.appCore.windows;

import basisFx.appCore.settings.Settings;

import java.math.BigDecimal;


public class WindowImplSubWindow extends WindowImpl{


    public WindowImplSubWindow(WindowBuilder builder) {
        super(builder);
    }
    @Override
    protected void setDefaultWidthAndHeight() {
        width=BigDecimal.valueOf(Settings.WIDTH).multiply(BigDecimal.valueOf(0.7d)).doubleValue();
        height=BigDecimal.valueOf(Settings.HEIGHT).multiply(BigDecimal.valueOf(0.7d)).doubleValue();
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
