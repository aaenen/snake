package com.aaenen.snake;

import javax.swing.*;

/**
 * @author aaenen
 * @PackageName:com.aaenen.snake
 * @ClassName: StartGames
 * @Description
 * @date 2020/4/20 14:23
 */

public class StartGames {

    public static void main(String[] args) {
        //1.绘制一个静态窗口 JFrame
        JFrame frame = new JFrame("啊啊嗯嗯java小程序-贪吃蛇小游戏");
        //设置界面的大小
        frame.setBounds(10, 10, 900, 720);
        //窗口大小不可改变
        frame.setResizable(false);
        //设置关闭事件，游戏可以关闭了
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //2.画板 JPanle 可以加入到JFrame
        frame.add(new GamePanel());

        //让窗口能够展示出来
        frame.setVisible(true);
    }
}
