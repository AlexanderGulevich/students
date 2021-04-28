package basisFx.appCore.elements;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.Mediator;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import lombok.Getter;

public class CheckBoxAdapter {

    @Getter
    private JFXCheckBox jfxCheckBox;
    private Mediator mediator;
    @Getter
    private ActiveRecord record;

    public CheckBoxAdapter(JFXCheckBox jfxCheckBox, Mediator mediator, ActiveRecord record) {
        this.jfxCheckBox = jfxCheckBox;
        this.mediator = mediator;
        this.record = record;

        jfxCheckBox.setOnAction((e) -> {
            mediator.inform(this);
        });


    }


}
