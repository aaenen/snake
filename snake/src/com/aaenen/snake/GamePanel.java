package com.aaenen.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author aaenen
 * @PackageName:com.aaenen.snake
 * @ClassName: Gamepanel
 * @Description
 * @date 2020/4/20 14:41
 */

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //蛇的长度
    int lenth;
    //蛇的坐标X
    int[] snakeX = new int[600];
    //蛇的坐标Y
    int[] snakeY = new int[600];
    //蛇头的方向
    String fx;

    //判断游戏是否开始
    boolean isStart = false;

    //定时器
    Timer timer = new Timer(100, this);

    //1.定义一个食物
    int foodx;
    int foody;
    Random random = new Random();

    //判断是否失败
    boolean isFail = false;

    //积分系统
    int score;


    //构造器
    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //初始化
    public void init() {
        lenth = 3;
        //头部坐标
        snakeX[0] = 100;
        snakeY[0] = 100;
        //第一个身体坐标
        snakeX[1] = 75;
        snakeY[1] = 100;
        //第二个身体
        snakeX[2] = 50;
        snakeY[2] = 100;

        fx = "R";
        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);
        score = 0;
    }

    //画板： 画界面，画蛇
    //Graphics: 画笔
    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        //设置背景的颜色
        this.setBackground(Color.white);

        //绘制头部的广告栏
        Data.header.paintIcon(this, g, 25, 11);

        //绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        //画一条静态的小蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        }


        for (int i = 1; i < lenth; i++) {
            //蛇的身体长度通过length来控制
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画食物
        Data.food.paintIcon(this, g, foodx, foody);

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + lenth, 750, 35);
        g.drawString("分数：" + score, 750, 55);

        //游戏提示：是否开始
        if (isStart == false) {
            //画一个文字，String
            //设置画笔的颜色
            g.setColor(Color.white);
            //设置字体
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }

        //失败提醒
        if (isFail) {
            //设置画笔的颜色
            g.setColor(Color.RED);
            //设置字体
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏失败！按下空格重新开始游戏", 200, 300);
        }
    }


    //接受键盘的输入：监听
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = false;
                //重新初始化游戏
                init();
            } else {
                //如果按下的是空格键，让状态取反
                isStart = !isStart;
            }

            repaint();
        }

        //键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        } else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        }
    }

    //定时器，监听时间,帧：执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态
        if (isStart && isFail == false) {
            //身体右移
            for (int i = lenth - 1; i > 0; i--) {
                //除了脑袋，身体都向前移动
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //通过方向让头部移动
            if (fx.equals("R")) {
                //头部移动
                snakeX[0] = snakeX[0] + 25;
                //边界判断
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                //头部移动
                snakeX[0] = snakeX[0] - 25;
                //边界判断
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if (fx.equals("U")) {
                //头部移动
                snakeY[0] = snakeY[0] - 25;
                //边界判断
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (fx.equals("D")) {
                //头部移动
                snakeY[0] = snakeY[0] + 25;
                //边界判断
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            //如果蛇的头和咱们的食物坐标重合了
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                //长度+1
                lenth++;
                score += 10;
                //重新生成食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }

            //结束判断
            for (int i = 1; i < lenth; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }
            //刷新界面
            repaint();
        }
        //让时间动起来
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起：敲击
    }
}
