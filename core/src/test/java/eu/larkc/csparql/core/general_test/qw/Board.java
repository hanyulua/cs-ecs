package eu.larkc.csparql.core.general_test.qw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author 杨帆玉
 * @date 2020/3/12 6:44 下午
 */
public class Board {
    private JFrame window;
    private JPanel panel;
    private Square[] squares = new Square[25];
    private int[] frogs = new int[13];// 存储荷叶的下标, 表示这些荷叶可以放青蛙


    public Board(int width, int height, String title) {
        this.window = new JFrame();
        this.window.setTitle(title);
        this.window.setSize(width, height);
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));

        setWater();
        setLilypad();
        setFrog();
        setRedFrog();


        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    // 从start跳到end
    public static void move(int start, int end) {

    }

    private void setRedFrog() {
        squares[frogs[11]].setStatus(3); //设置红青蛙
    }

    private void setFrog() {
        int[] initFrogArr = {3, 4, 6, 10, 12}; // 固定青蛙的位置.
        for (int i = 0; i < initFrogArr.length; i++) {
            squares[frogs[initFrogArr[i]]].setStatus(2);
        }
    }

    // 初始化荷叶的位置
    private void setLilypad() {
        for (int i = 0; i < squares.length; i++) {
            if (i % 2 == 0) {
                squares[i].setStatus(1);//设为1就是讲当前的水改为荷叶
                frogs[i / 2] = i; // 存储荷叶的下标,因为只有有荷叶的地方才可以有青蛙
            }
        }
    }

    // 初始化水的位置,就是所有块都是水
    private void setWater() {
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square(0);
            panel.add(squares[i].getButton());
        }
    }

    public static void main(String[] args) {
        Board board = new Board(800, 800, "青蛙");
    }
}
