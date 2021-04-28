package basisFx.appCore.utils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Coordinate {
    
    private AnchorPane parentAnchorPane;
    private Node node;
    private Double top;
    private Double right;
    private Double bottom;
    private Double left;

    public Coordinate(Double top, Double right, Double bottom, Double left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Coordinate setChildNode(Node node) {
        this.node = node;
        return this;
    }
    public Coordinate setParentAnchorPane(AnchorPane parentAnchorPane) {
        this.parentAnchorPane = parentAnchorPane;
        return this;
    }
    
    public void bonding () {
        
                if (parentAnchorPane!=null){
                    parentAnchorPane.getChildren().add(node);

                }
//
                if(top!=null){
                    AnchorPane.setTopAnchor(node, top);
                }
                if(right!=null){
                    AnchorPane.setRightAnchor(node, right);
                }
                if(bottom!=null){
                   AnchorPane.setBottomAnchor(node, bottom);
                }
                if(left!=null){
                    AnchorPane.setLeftAnchor(node, left);
                }

}

    
    
}
