package basisFx.appCore.chart;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CoordinateLabel {
    static  SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MMM.yyyy",new Locale("ru"));


    public static   Label executeLabel(XYChart<Number,Number> chart) {
        final Label label = new Label();

        final Axis<Number> xAxis = chart.getXAxis();
        final Axis<Number> yAxis = chart.getYAxis();


        final Node chartBackground = chart.lookup(".chart-plot-background");
        for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
            if (n != chartBackground && n != xAxis && n != yAxis) {
                n.setMouseTransparent(true);
            }
        }

        chartBackground.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(true);
            }
        });

        chartBackground.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setText(
                        String.format(
                                "x - %.1f,    y - %.1f",
                                xAxis.getValueForDisplay(mouseEvent.getX()),
                                yAxis.getValueForDisplay(mouseEvent.getY())
                        )
                );
            }
        });

        chartBackground.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(false);
            }
        });

        xAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(true);
            }
        });

        xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setText(
                        String.format(
                                "x = %.1f",
                                xAxis.getValueForDisplay(mouseEvent.getX())
                        )
                );
            }
        });

        xAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(false);
            }
        });

        yAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(true);
            }
        });

        yAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setText(
                        String.format(
                                "y = %.1f",
                                yAxis.getValueForDisplay(mouseEvent.getY())
                        )
                );
            }
        });

        yAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(false);
            }
        });

        return label;
    }


    public static   Label executeDateBasedLabel(XYChart<Number,Number> chart) {
        final Label label = new Label();

        final Axis<Number> xAxis = chart.getXAxis();
        final Axis<Number> yAxis = chart.getYAxis();


        final Node chartBackground = chart.lookup(".chart-plot-background");
        for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
            if (n != chartBackground && n != xAxis && n != yAxis) {
                n.setMouseTransparent(true);
            }
        }

        chartBackground.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(true);
            }
        });

        chartBackground.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setText(" дата - "+
                        dayFormat.format( xAxis.getValueForDisplay(mouseEvent.getX()))+ "   "+
                        String.format(" значение - %.1f", yAxis.getValueForDisplay(mouseEvent.getY()))
                );
            }
        });

        chartBackground.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(false);
            }
        });

        xAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(true);
            }
        });

        xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setText(
                        dayFormat.format( xAxis.getValueForDisplay(mouseEvent.getX()))
                );
            }
        });

        xAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(false);
            }
        });

        yAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(true);
            }
        });

        yAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setText(
                        String.format(
                                "значение = %.1f",
                                yAxis.getValueForDisplay(mouseEvent.getY())
                        )
                );
            }
        });

        yAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                label.setVisible(false);
            }
        });

        return label;
    }


}
