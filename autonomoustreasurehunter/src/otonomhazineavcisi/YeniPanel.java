
package otonomhazineavcisi;

import javax.swing.*;
import java.awt.*;

public class YeniPanel extends JFrame {
    private JTextArea textArea;

    public YeniPanel() {
        setTitle("Yeni Panel");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        textArea = new JTextArea();
        panel.add(textArea);
        this.add(panel);

        setVisible(true);
    }

    public void metniGuncelle(String metin) {
        textArea.setText(metin);
    }
}
