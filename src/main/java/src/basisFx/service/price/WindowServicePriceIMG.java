package basisFx.service.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.*;
import basisFx.appCore.poi.StringHandler;
import basisFx.appCore.utils.ImgExstensions;
import basisFx.appCore.utils.ImgUtils;
import basisFx.appCore.utils.Registry;
import basisFx.domain.price.Img;
import basisFx.domain.price.PriceItem;
import basisFx.service.WindowService;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class WindowServicePriceIMG extends WindowService {

    @FXML private AnchorPane titlePanel;
    @FXML private Button okBut;
    @FXML private Label title;
    @FXML private Label barcodeLabel;
    @FXML private JFXButton addPic;
    @FXML private ImageView imgview;

    FileChooser fileChooser;
    TableWrapper outer_table_wrapper;
    StringHandler stringHandler=new  StringHandler();
    String barcode;
    PriceItem clickedDomain;


    public WindowServicePriceIMG() {
        Registry.crossWindowMediators.put("PriceImgHandlerWindow",this);
    }
    @Override
    public void init() {
        initCloseButtonEvent();
        initStageDragging();
        initTitle();
        outerTableDataHandler();
        viewStoredImg();
        fileChooserHandler();
    }
    @Override
    public void inform(Object node) {
        if (node==addPic) {
            File file = fileChooser.getFiles().get(0);

            String absolutePath = file.getAbsolutePath();

            File filebig = new File(absolutePath);
            File filesmall = new File(absolutePath);
            File filetoview = new File(absolutePath);

            ByteArrayInputStream byteArrayInputStream480 = ImgUtils.resize(filebig, 480);
            ByteArrayInputStream byteArrayInputStream160 = ImgUtils.resize(filesmall, 160);
            ByteArrayInputStream byteArrayInputStreamfiletoview = ImgUtils.resize(filetoview, 480);

            Img img=null;

            if (clickedDomain != null && clickedDomain.getImg() != null ) {
                 img =clickedDomain.getImg() ;
                    img.setBarcode(barcodeLabel.getText());
                    img.setImgBig(byteArrayInputStream480);
                    img.setImgSmall(byteArrayInputStream160);
                    img.update();
            }else {
                img = new Img();
                img.setBarcode(barcodeLabel.getText());
                img.setImgBig(byteArrayInputStream480);
                img.setImgSmall(byteArrayInputStream160);
                img.insert();
            }

            Image image1 = null;
            try {
                image1 = new Image( byteArrayInputStreamfiletoview);
            } catch (Exception e) {
                Registry.windowFabric.infoWindow("что-то пошло не так при загрузке картинки  \n"+e.getMessage());
            }
            if ( image1.isError()) {
                Registry.windowFabric.infoWindow("Некая ошибка при обработке изображения\n"+  image1.getException().getMessage());
            }else {
                imgview.setImage(image1);
                imgview.setFitHeight(333);
                clickedDomain.setImg(img);
                clickedDomain.insert();
                //todo
                ObservableList<ActiveRecord> all = PriceItem.getINSTANCE().getAll();
                outer_table_wrapper.setItems(all);
            }
        }
    }

    private void checkTextField(Img img) {
        try {

            String value= barcodeLabel.getText().trim();
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
            Registry.windowFabric.infoWindow("Не вышло преобразовать в число штрихкод по  введенному значению - \n"+ barcodeLabel.getText()  );
        }
    }
    private void viewStoredImg() {
        Img img = Img.getINSTANCE().find(clickedDomain);
        if (img != null) {
            InputStream imgBig = img.getImgBig();
            Image image=new Image(imgBig);
            imgview.setImage(image);
            imgview.setFitHeight(333);
            boolean image1Error = image.isError();
            if (image1Error) {
                Registry.windowFabric.infoWindow("Некая ошибка при обработке изображения из БД\n"+  image.toString());
            }
        }
    }
    private void fileChooserHandler() {
        fileChooser = new FileChooser(new ImgExstensions());
        fileChooser.setEventToElement(addPic);
        fileChooser.setMediator(this);
        fileChooser.setCallBackTyped(() -> {
                    String text = barcodeLabel.getText();
                    if (text==null) {
                        Registry.windowFabric.infoWindow("Для того, чтобы привязать картинку, у товара должен быть штриход!");
                        return false;
                    }
                    return true;
                }

        );
    }
    private void outerTableDataHandler() {
        outer_table_wrapper = (TableWrapper) Registry.dataExchanger.get("outer_table_wrapper");
        clickedDomain = (PriceItem) outer_table_wrapper.clickedDomain;
        barcodeLabel.setText(clickedDomain.getBarcode());
    }
    private void initCloseButtonEvent() {
        ClosePopupAndSubWindow closePopupAndSubWindow = new ClosePopupAndSubWindow();
        closePopupAndSubWindow.setMediator(this);
        closePopupAndSubWindow.setEventToElement(okBut);

       }
    private void initStageDragging() {
        new StageDragging().setEventToElement(titlePanel);
    }
    private void initTitle() {
        title.setText(currentWindow.getWindowImpl().getTitleName());
    }

}
