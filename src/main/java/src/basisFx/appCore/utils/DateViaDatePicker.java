package basisFx.appCore.utils;

import basisFx.appCore.elements.DatePickerWrapper;
import basisFx.service.ServiceTables;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DateViaDatePicker extends DateGetter {

    private DatePicker element;
    @Override
    public LocalDate getDate() {
        return element.getValue();
    }

    @Override
    public void init() {
        DatePickerWrapper datePickerWrapper = DatePickerWrapper.newBuilder()
                .setCoordinate(coordinate)
                .setParentAnchor(parentAnchor)
                .setMediator(this)
                .build();

        ((ServiceTables) mediator).setDatePickerWrapper(datePickerWrapper);

        element= datePickerWrapper.getElement();
    }


    @Override
    public void inform(Object node) {
            mediator.inform(this);
    }

}
