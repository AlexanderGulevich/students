package basisFx.appCore.utils;

import org.apache.commons.compress.utils.IOUtils;

import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Iterator;

public class ImgUtils {
//            public static void mainr( )
//            {
//                String in = (System.getProperty("user.dir") + "/"+"src/res/res/img/gagarin.jpg").replace("\\","/");
//                String out = System.getProperty("user.dir") + "/"+"src/res/res/img/resized.jpg";
//
//
//                resize(in,out,480);
////                resize(in,out,160);
////
//            }

            public static void   resize(String pathToFile, String pathToResizedFile, int width)
            {
                try
                {
                    File f = new File(pathToFile);
                    if (!f.exists())  Registry.windowFabric.infoWindow("File doesn’t exist") ;

                    BufferedImage bim=ImageIO.read(new FileInputStream(pathToFile));
                    Image resizedImg=bim.getScaledInstance(width,-1,Image.SCALE_FAST);
                    int scaled_height=resizedImg.getHeight(null);

                    BufferedImage rBimg=new BufferedImage(width,scaled_height,bim.getType());

                    Graphics2D g=rBimg.createGraphics();
                    g.drawImage(resizedImg,0,0,null);
                    g.dispose();

                    ImageIO.write(rBimg,pathToResizedFile.substring(pathToResizedFile.indexOf(".")+1),new FileOutputStream(pathToResizedFile));
                }
                catch (Exception e)
                {
                    Registry.windowFabric.infoWindow(e.getMessage()) ;
                }
                Registry.windowFabric.infoWindow("\"Picture Resized Successfully\"") ;
            }



            public static ByteArrayInputStream resize(String pathToFile, int width)
            {
                try
                {
                    File f = new File(pathToFile);
                    boolean exist = f.exists();
                    if (!exist)  Registry.windowFabric.infoWindow("File doesn’t exist") ;

                    BufferedImage bim=ImageIO.read(new FileInputStream(f));
                    Image resizedImg=bim.getScaledInstance(width,-1,Image.SCALE_FAST);
                    int scaled_height=resizedImg.getHeight(null);

                    BufferedImage rBimg=new BufferedImage(width,scaled_height,bim.getType());

                    Graphics2D g=rBimg.createGraphics();
                    g.drawImage(resizedImg,0,0,null);
                    g.dispose();
                    byte[] buffer = ((DataBufferByte)(rBimg).getRaster().getDataBuffer()).getData();
                    return new ByteArrayInputStream(buffer);

                }
                catch (Exception e)
                {
                    Registry.windowFabric.infoWindow(e.getMessage()) ;
                    return null;
                }
            }

            public static ByteArrayInputStream resize(File file, int width)
            {
                try
                {
                    boolean exist = file.exists();
                    if (!exist)  Registry.windowFabric.infoWindow("File doesn’t exist") ;

                    BufferedImage bim=ImageIO.read(new FileInputStream(file));
                    Image resizedImg=bim.getScaledInstance(width,-1,Image.SCALE_FAST);
                    int scaled_height=resizedImg.getHeight(null);

                    BufferedImage rBimg=new BufferedImage(width,scaled_height,bim.getType());

                    Graphics2D g=rBimg.createGraphics();
                    g.drawImage(resizedImg,0,0,null);
                    g.dispose();

                    ByteArrayInputStream fis = toInputStream(rBimg);

                    return fis;
                }
                catch (Exception e)
                {
                    Registry.windowFabric.infoWindow(e.getMessage()) ;
                    return null;
                }
            }

    public static ByteArrayInputStream toInputStream(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png", os);
        return new ByteArrayInputStream(os.toByteArray());
    }
    public static ByteArrayOutputStream toOutputStream(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png", os);
        return  os;
    }


    public static ByteArrayOutputStream toByteArrayOutputStream(File f) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] picData;
        int b;

        FileInputStream fis = new FileInputStream(f.getAbsolutePath());
        if (f.exists()) {

            while ((b = fis.read()) != -1) {
                baos.write(b);
            }
            picData = IOUtils.toByteArray(fis);

            fis.close();

            return baos;

        }
        return null;
    }



}
