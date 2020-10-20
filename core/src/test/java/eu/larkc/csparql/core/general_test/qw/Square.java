package eu.larkc.csparql.core.general_test.qw;

import javax.swing.*;

/**
 * @author 杨帆玉
 * @date 2020/3/12 6:43 下午
 */
public class Square {
    private int status; // 当前的状态 0-水,1-荷叶,2-青蛙,3-....4....,用来控制当前显示的图标
    private JButton button;
    private ImageIcon imageIcon;

    public Square(int status) {
        this.status = status;
        this.button = new JButton();
        this.imageIcon = new ImageIcon(getImageUrl(status));
        this.button.setIcon(this.imageIcon);
    }

    public int getStatus() {
        return status;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }


    // 设置状态,表示青蛙跳,或者其他的情况下, 修改状态后,需要修改图标
    public void setStatus(int status) {
        this.status = status;
        this.imageIcon = new ImageIcon(getImageUrl(this.status));
        this.button.setIcon(this.imageIcon);
    }

    private String getImageUrl(int status) {
        switch (status) {
            case 0:
                return "/Users/yangfanyu/IdeaProjects/ecs-2020/core/src/test/java/eu/larkc/csparql/core/general_test/qw/Water.jpg";
            case 1:
                return "/Users/yangfanyu/IdeaProjects/ecs-2020/core/src/test/java/eu/larkc/csparql/core/general_test/qw/Lilypad.jpg";
            case 2:
                return "/Users/yangfanyu/IdeaProjects/ecs-2020/core/src/test/java/eu/larkc/csparql/core/general_test/qw/Frog.jpg";
            case 3:
                return "/Users/yangfanyu/IdeaProjects/ecs-2020/core/src/test/java/eu/larkc/csparql/core/general_test/qw/RedFrog.jpg";
            default:
                return "Water.png";
        }
    }
}
