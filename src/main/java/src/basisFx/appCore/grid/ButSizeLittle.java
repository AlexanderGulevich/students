package basisFx.appCore.grid;

import basisFx.appCore.events.AppEvent;

public class ButSizeLittle extends ButSizeForGrid {

    public ButSizeLittle(AppEvent del, AppEvent add) {
        super(del, add);
    }

    public ButSizeLittle() {
    }

    @Override
    public void init() {
        setColumnWidth(40d);
        buttonAdd=littleRowAddButton(tableWrapper);
        buttonDel=littleRowDeleteButton(tableWrapper);
    }




}
