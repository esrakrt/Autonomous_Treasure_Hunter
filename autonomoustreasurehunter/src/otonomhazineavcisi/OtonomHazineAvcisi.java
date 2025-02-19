package otonomhazineavcisi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random; // Random sınıfını import et

public class OtonomHazineAvcisi extends JFrame {
    private static final Random rand = new Random(); // Random nesnesi oluştur

    private int haritaBoyut;
    private HaritaPanel haritaPanel;
    private JPanel baslangicPaneli;
    private JButton oynaButonu;
    private JLabel resimLabel;
    private JTextField kullaniciAdiField;
    private JTextField kullaniciIDField;

    public OtonomHazineAvcisi() {
        
        setTitle("Otonom Hazine Avcısı");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800)); 
        JLabel label = new JLabel("Harita Boyutu:");
        JTextField boyutField = new JTextField(10);
        JButton baslatButton = new JButton("Oyuna Başla");

        baslatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    haritaBoyut = Integer.parseInt(boyutField.getText());
                    if (haritaBoyut > 0) {
                        // HaritaPanel oluştur
                        haritaPanel = new HaritaPanel(haritaBoyut);
                        
                    
                        

                        // Pencereye HaritaPanel'i ekle
                        getContentPane().removeAll();
                        getContentPane().add(haritaPanel, BorderLayout.CENTER);
                        revalidate();
                        repaint();

                        
                        setFocusable(true);
                    } else {
                        JOptionPane.showMessageDialog(OtonomHazineAvcisi.this, "Geçerli bir pozitif sayı giriniz.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(OtonomHazineAvcisi.this, "Lütfen geçerli bir sayı giriniz.");
                }
            }
        });

        panel.add(label);
        panel.add(boyutField);
        panel.add(baslatButton);

        baslangicPaneli = new JPanel();
        baslangicPaneli.setOpaque(false);
        baslangicPaneli.setLayout(new BoxLayout(baslangicPaneli, BoxLayout.Y_AXIS));
        oynaButonu = new JButton("Oyna");
        kullaniciAdiField = new JTextField(10);
        kullaniciIDField = new JTextField(10);

        baslangicPaneli.add(new JLabel("Kullanıcı Adı:"));
        baslangicPaneli.add(kullaniciAdiField);
        baslangicPaneli.add(new JLabel("Kullanıcı ID:"));
        baslangicPaneli.add(kullaniciIDField);
        baslangicPaneli.add(oynaButonu);

        oynaButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = kullaniciAdiField.getText();
                String kullaniciID = kullaniciIDField.getText();
                if (!kullaniciAdi.isEmpty() && !kullaniciID.isEmpty()) {
                    getContentPane().removeAll();
                    getContentPane().add(panel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(OtonomHazineAvcisi.this, "Lütfen kullanıcı adını ve ID'yi girin.");
                }
            }
        });

        ImageIcon icon = new ImageIcon("giris.png"); // Resim dosyasının yolunu girin
        Image image = icon.getImage(); // ImageIcon'u Image'a dönüştür
        Image scaledImage = image.getScaledInstance(800, 800, Image.SCALE_DEFAULT); // Resmi 900x900 boyutuna ölçekle
        resimLabel = new JLabel(new ImageIcon(scaledImage)); // Ölçeklenmiş resmi kullan
        resimLabel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        resimLabel.add(baslangicPaneli, gbc);

        add(resimLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*private class KlavyeListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            // KeyTyped metodunu kullanmıyoruz
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Klavyedeki ok tuşlarına göre karakteri hareket ettir
            if (haritaPanel != null) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    haritaPanel.hareketEt(-1, 0); // Yukarı
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    haritaPanel.hareketEt(1, 0); // Aşağı
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    haritaPanel.hareketEt(0, -1); // Sola
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    haritaPanel.hareketEt(0, 1); // Sağa
                }
            }
        

        @Override
        public void keyReleased(KeyEvent e) {
            // KeyReleased metodunu kullanmıyoruz
        }
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OtonomHazineAvcisi();
            }
        });
    }
}
class YeniPanel extends JFrame {
    public YeniPanel(String metin) {
        setTitle("Yeni Panel");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea(metin);
        panel.add(textArea);
        this.add(panel);

        setVisible(true);
    }
}