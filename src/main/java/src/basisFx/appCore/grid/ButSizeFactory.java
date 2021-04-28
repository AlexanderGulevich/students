package basisFx.appCore.grid;

public class ButSizeFactory {
    private static ButSizeFactory butSizeFactoryInstance;

    private ButSizeFactory() {
    }

    public static synchronized ButSizeFactory getInstance() {
        if (butSizeFactoryInstance == null) {
            butSizeFactoryInstance = new ButSizeFactory();
        }
        return butSizeFactoryInstance;
    }

    public ButSizeForGrid getButSizeForGrid(ButSizeEnum butSizeEnum) {
        switch (butSizeEnum) {
            case BUT_SIZE_BIG: {
                return new ButSizeBig();
            }
            case BUT_SIZE_NON: {
                return new ButSizeNon();
            }
            case BUT_SIZE_LITTLE: {
                return new ButSizeLittle();
            }
            default: {
                return null;
            }
        }
    }
}