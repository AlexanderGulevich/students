package basisFx.appCore.utils;

import basisFx.appCore.elements.LabelWrapper;
import basisFx.appCore.settings.FontsStore;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateViaPopup extends DateGetter {

    private LocalDate date;
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public void init() {
        date=((LocalDate) Registry.dataExchanger.get("SelectedDateFromPopup"));
        createLabel();
    }

    public   void createLabel() {
        LabelWrapper.newBuilder()
                .setCssClassesStrings("LABEL1")
                .setCssClassesStrings("font_softRed")
                .setText(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy г.")))
                .setParentAnchor(parentAnchor)
                .setCoordinate(new Coordinate(18d, 15d, null, null))
                .setFont(FontsStore.ROBOTO_LIGHT)
                .setAlignment(Pos.TOP_LEFT)
                .setFontSize(18d)
                .build();
    }

    @Override
    public void inform(Object node) {
        mediator.inform(this);
    }
}
