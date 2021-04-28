package basisFx.service.price;

import basisFx.appCore.events.DirectoryChosserEvent;
import basisFx.appCore.events.FileChooser;
import basisFx.appCore.utils.ImgExstensions;
import basisFx.appCore.utils.Registry;
import basisFx.domain.price.Img;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class ServicePanelImageHandler extends ServicePanels {
    @FXML
    private JFXButton addPic;
    @FXML
    private JFXButton check;
    @FXML
    private Label commonLabelName;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField textField1;

    FileChooser fileChooser;

    DirectoryChosserEvent directoryChosserEvent;

    @Override
    public void init() {

        fileChooser = new FileChooser(new ImgExstensions());
        fileChooser.setEventToElement(addPic);
        fileChooser.setMediator(this);
        fileChooser.setCallBackTyped(() -> {
            String text = textField1.getText();
            if (!(text!=null && text.trim().length()>0)) {
                Registry.windowFabric.infoWindow("Для того, чтобы привязать картинку, сначала введите штрихкод!");
                return false;
            }
            return true;
            }

        );
    }



    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    public ServicePanelImageHandler() {
        Registry.dataExchanger.put("priceImageHadler", this);
    }


    @Override
    public void inform(Object node) {
        if (node==addPic) {

//            imgview.setImage();

            File file = fileChooser.getFiles().get(0);
            Img img = new Img();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                img.setImgBig(fileInputStream);
            } catch (FileNotFoundException e) {
                Registry.windowFabric.infoWindow(e.getMessage()  );

            }

            try {

                String value= textField1.getText().trim();
                char[] charArray = value.toCharArray();

                List<Character> chars = new ArrayList<>();
                for (int i = 0; i < charArray.length; i++) {
                    chars.add(charArray[i]);
                }

                String barcode=new String();
                int barcodeLenth=0;

                for (Character aChar : chars) {
                    try {
                        Integer integer = Integer.valueOf(aChar.toString());
                        String current=aChar.toString();
                        barcodeLenth += 1;
                        barcode+=integer.toString();
                        if (barcodeLenth==13) img.setBarcode(barcode);
                    } catch (NumberFormatException e) {
                            Registry.windowFabric.infoWindow("Шрихкод нижеследующего товара содержит нечисленные символы");
                     break;
                    }
                }
                if (barcodeLenth<13)  Registry.windowFabric.infoWindow("Шрихкод нижеследующего товара содержит менее 13 символов");

            } catch (NumberFormatException e) {
                Registry.windowFabric.infoWindow("Не вышло преобразовать в число штрихкод по  введенному значению - \n"+ textField1.getText()  );
            }

            img.insert();


        }


    }

}