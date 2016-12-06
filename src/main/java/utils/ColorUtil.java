package utils;

import java.awt.*;
import java.util.Random;

/**
 * Created by androidjp on 2016/11/22.
 */
public class ColorUtil {

    public static Color getRadomColor(){
        Random random = new Random();
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

    public static Color getReverseColor(Color color){
        return new Color(255 - color.getRed(), 255-color.getGreen(), 255-color.getBlue());
    }
}
