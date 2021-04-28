package basisFx.appCore.grid;

import basisFx.appCore.events.AppEvent;

public class ButSizeBig extends ButSizeForGrid {

    public ButSizeBig(AppEvent del, AppEvent add) {
        super(del, add);
    }

    public ButSizeBig() {
    }

    @Override
    public void init() {
        setColumnWidth(130d);
        buttonAdd= addRowButtonHuge(tableWrapper);
        buttonDel= deleteRowButtonHuge(tableWrapper);
    }

}
