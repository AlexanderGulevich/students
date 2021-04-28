package basisFx.appCore.grid;

public class CtrlPosFactory {
    private static CtrlPosFactory ctrlPosFactoryInstance;

    private CtrlPosFactory() {
    }

    public static synchronized CtrlPosFactory getInstance() {
        if (ctrlPosFactoryInstance == null) {
            ctrlPosFactoryInstance = new CtrlPosFactory();
        }
        return ctrlPosFactoryInstance;
    }

    public CtrlPosition getCtrlPosition(CtrlPosEnum ctrlPosEnum) {
        switch (ctrlPosEnum) {
            case CTRL_POS_TOP: {
                return new CtrlPosTop();
            }
            case CTRL_POS_BUT_AND_COMBOBOX: {
                return new CtrlPosButAndCombobox();
            }
            case CTRL_POS_BOTTON: {
                return new CtrlPosBotton();
            }
            case CTRL_POS_N_O_N: {
                return new CtrlPosNON();
            }
            case CTRL_POS_DEL_BUT: {
                return new CtrlPosDelBut();
            }
            case CTRL_POS_DEL_BUT_BOTTON: {
                return new CtrlPosDelButBotton();
            }
            case CTRL_POS_DEL_BUT_MIDDLE: {
                return new CtrlPosDelButMiddle();
            }
            case CTRL_POS_MIDDLE: {
                return new CtrlPosMiddle();
            }
            default: {
                return null;
            }
        }
    }
}