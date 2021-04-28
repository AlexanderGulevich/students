package basisFx.appCore.windows;

public class WindowImplDialog extends WindowImpl {
    private  String messagge;

    public WindowImplDialog(WindowBuilder builder) {
        super(builder);
        this.messagge=builder.message;

    }

    @Override
    protected void setDefaultWidthAndHeight() {
        width=530d;
        height=300d;

    }

    @Override
    public void customInit(WindowAbstraction windowAbstraction) {

        windowService.setMessage(builder.message);
        setWindowAbstraction(windowAbstraction);
        windowAbstraction.stage.setAlwaysOnTop(true);
        windowAbstraction.stage.setTitle(getTitleName());

    }

    @Override
    public void addToRegistry(WindowAbstraction windowAbstraction) {

    }
}
