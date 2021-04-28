package basisFx.appCore.elements;

import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.events.AppEvent;
import basisFx.appCore.settings.CSSid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.interfaces.Mediator;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;


public abstract class AppNode <T extends Node> {

    @Getter protected ArrayList<AppEvent> events;
    protected CSSid CSSid;
    protected CSSclasses[] cssClasses;
    protected String[] cssClassesStrings;
    protected List<String> cssClassesStringsList;
    protected Double width;
    protected Double height;
    protected Coordinate coordinate;
    protected AnchorPane parentAnchor;
    protected Group parentGroup;
    protected FlowPane parentFlowPane;
    protected ScrollPane parentScrollPane;
    @Getter protected String metaName;
    @Getter protected String text;
    @Getter protected Stage stage;
    protected WindowAbstraction windowAbstraction;
    protected HBox parentHBox;
    protected VBox parentVBox;
    protected String cssClass;

    public abstract Node getElement();
    public void setElementToWindowRegistry() {
        if (metaName != null) {
            if (windowAbstraction != null) {
                windowAbstraction.setNodeToMap(getElement(), metaName);
            }
        }
    }
    public void bond(AppNode node) {
            if(parentAnchor!=null){     bondAnchorAndNode(node);}
            if(parentFlowPane!=null){   bondNodeWithFlowPane(node);}
            if(parentScrollPane!=null){ bondParentScrollPane(node);}
            if(parentGroup!=null){      parentGroup.getChildren().addAll(node.getElement());}
            if(parentHBox!=null){       parentHBox.getChildren().add(node.getElement());}
            if(parentVBox!=null){       parentVBox.getChildren().add(node.getElement());}
    }
    private void bondParentScrollPane(AppNode n) {
        parentScrollPane.setPannable(true);
        parentScrollPane.setContent(n.getElement());
    }
    private void bondNodeWithFlowPane(AppNode node) {
        parentFlowPane.getChildren().add(node.getElement());
    }
    private void bondAnchorAndNode(AppNode node) {
        this.coordinate.setChildNode(node.getElement());
        this.coordinate.setParentAnchorPane(parentAnchor);
        this.coordinate.bonding();
    }
    protected void elocateEvents() {
        if ( events != null  &&! events.isEmpty()) {
        for (Iterator<AppEvent> iterator = events.iterator(); iterator.hasNext();) {
            AppEvent next = iterator.next();
            next.setEventToElement(this);
        }
    }
}
    protected void elocateEvents(Mediator mediator) {
        if ( events != null  &&! events.isEmpty()) {
        for (Iterator<AppEvent> iterator = events.iterator(); iterator.hasNext();) {
            AppEvent next = iterator.next();
            next.setMediator(mediator);
            next.setEventToElement(this);
        }
    }
}
    protected void applyCssId() {
        if (CSSid != null) {
            getElement().setId(CSSid.get());
        }
    }
    protected void applyCssClasses() {
        if (cssClass != null) {
            getElement().getStyleClass().add(cssClass);
        }

        if (cssClasses != null) {
            for (int i = 0; i < cssClasses.length; i++) {
                getElement().getStyleClass().add(cssClasses[i].get());
            }
        }
        if (cssClassesStrings != null) {
            for (int i = 0; i < cssClassesStrings.length; i++) {
                getElement().getStyleClass().add(cssClassesStrings[i]);
            }
        }
        if (cssClassesStringsList != null) {
            cssClassesStringsList.forEach(s -> getElement().getStyleClass().add(s));
        }
    }
    }

       


   
   


    

   
    
    
