package GUI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.add(new MainPanel(width, height));
        this.setLayout(null);
        this.setSize(width, height);
        this.setVisible(true);
    }
}
