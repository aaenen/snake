package com.aaenen.snake;

import javax.swing.*;
import java.net.URL;

/**
 * @author aaenen
 * @PackageName:com.aaenen.snake
 * @ClassName: Data
 * @Description
 * @date 2020/4/20 14:57
 */

public class Data {

    //头部的图片 URL:定位图片地址 ImageIcon：图片
    public static URL headreURL = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headreURL);

    //头部：
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static URL leftURl = Data.class.getResource("/statics/left.png");
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURl);
    public static ImageIcon right = new ImageIcon(rightURL);

    //身体
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    //食物
    public static URL foodURl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURl);
}
