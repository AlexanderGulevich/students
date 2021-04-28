package basisFx.service.price;

import basisFx.appCore.events.FileChooser;
import basisFx.appCore.utils.OfficeExtensions;
import basisFx.appCore.utils.Registry;
import basisFx.domain.price.Price;
import basisFx.domain.price.PriceReader;
import basisFx.domain.price.PriceUtils;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class ServicePanelLoadPrice extends ServicePanels {
    @FXML
    private JFXButton load;
    @FXML
    private Label commonLabelName;
    @FXML
    private TextField textfield;
    @FXML
    private TextArea textarea;

    FileChooser fileChooser;

    static String path;

    @FXML
    private AnchorPane panelAnchor;



    @Override
    public void init() {
        fileChooser = new FileChooser(new OfficeExtensions());
        fileChooser.setEventToElement( load);
        fileChooser.setMediator(this);
        if (Registry.dataExchanger.get("PriceMessage") != null) {
            Price price = (Price) Registry.dataExchanger.get("price");
            setMessage(price);
        }
        textfield.setText(path);


    }



    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    public ServicePanelLoadPrice() {
        Registry.dataExchanger.put("priceLoader", this);
    }


    @Override
    public void inform(Object node) {
        if (node==load) {
            path=fileChooser.getFiles().get(0).getAbsolutePath();
            textarea.clear();
            textfield.setText(path);
            List<File> files = fileChooser.getFiles();
            PriceReader priceReader = new PriceReader(files.get(0));
            Price price = priceReader.getPrice();
            Registry.dataExchanger.put("price",price);
            textarea.setWrapText(true);
            setMessage(price);
        }


    }

    private void setMessage(Price price) {
        List<String> mess=new ArrayList<>();
        HashMap<PriceUtils.Message,ArrayList<String>> priceMessage
                = (HashMap<PriceUtils.Message, ArrayList<String>>) Registry.dataExchanger.get("PriceMessage");

        priceMessage.keySet().stream().forEach(message -> {
            ArrayList<String> stringArrayList = priceMessage.get(message);
            int length=stringArrayList.toArray().length;
            if(length>0){
                Optional<String> reduce = stringArrayList.stream().distinct().reduce((s, s2) -> s + "\n" + s2);
                mess.add("\n\n"+message.get().toUpperCase()+"\n"+reduce.get());

            }
        });
        DecimalFormat df = new DecimalFormat("###,###.##");
        textarea.setText(
                "СУММА ПО ТЕКУЩИМ СОСТАТКАМ СОСТАВЛЯЕТ : "+df.format(price.getTotalSumma())+" руб."+
                mess.stream().reduce((s, s2) -> s+s2).get()

        );
    }

}