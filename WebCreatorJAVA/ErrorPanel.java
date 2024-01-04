import java.awt.Image;

import javax.swing.*;

public class ErrorPanel extends JFrame {
    ErrorPanel(String msg) {
        ImageIcon icon = new ImageIcon("exclamation.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        icon = new ImageIcon(newImg);
        JLabel label = new JLabel(msg); 
        label.setIcon(icon);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.setTitle("Website Creator 9000");
        this.setSize(300, 120);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    
        this.add(label);
    }
}
