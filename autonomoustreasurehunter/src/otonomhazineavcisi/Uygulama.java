package otonomhazineavcisi;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Uygulama {
    private List<Hazine> toplananHazineler = new ArrayList<>();
    private List<Lokasyon> hazineKonumlari = new ArrayList<>();
    private List<Integer> adimSayilari = new ArrayList<>();
    private int hareketEdilenKareSayisi = 0;
    private JFrame bilgiPaneli = null;

    public void hazineTopla(Hazine hazine, Lokasyon konum) {
        toplananHazineler.add(hazine);
        hazineKonumlari.add(konum);
        adimSayilari.add(hareketEdilenKareSayisi); // Hazine toplandığında kaç adım atıldığını sakla
    }

    public List<Hazine> getToplananHazineler() {
        return toplananHazineler;
    }

    public List<Lokasyon> getHazineKonumlari() {
        return hazineKonumlari;
    }

    public void hareketEt() {
        hareketEdilenKareSayisi++;
    }

    public int getHareketEdilenKareSayisi() {
        return hareketEdilenKareSayisi;
    }

    public void ekranaYazdir(Graphics g, int height, int width) {
        g.drawString("Hareket edilen kare sayısı: " + hareketEdilenKareSayisi, 10, height - 10);
        for (int i = 0; i < toplananHazineler.size(); i++) {
            Hazine hazine = toplananHazineler.get(i);
            Lokasyon konum = hazineKonumlari.get(i);
            String hazineBilgisi = hazine.getAd() + " şu konumda bulundu: (" + konum.getY() + ", " + konum.getX() + ")";
            g.drawString(hazineBilgisi, 20, 20 * (i + 1)); // Sol üst köşede bilgileri çiz
        }

        // Hazineleri değerlerine göre sırala
        List<Hazine> siraliHazineler = new ArrayList<>(toplananHazineler);
        Collections.sort(siraliHazineler, new Comparator<Hazine>() {
            @Override
            public int compare(Hazine h1, Hazine h2) {
                return Integer.compare(h2.getDeger(), h1.getDeger());
            }
        });

        for (int i = 0; i < siraliHazineler.size(); i++) {
            Hazine hazine = siraliHazineler.get(i);
            Lokasyon konum = hazine.getKonum();
            String hazineBilgisi = hazine.getAd() + " şu konumda bulundu: (" + konum.getY() + ", " + konum.getX() + ")";
            g.drawString(hazineBilgisi, width - 250, 20 * (i + 1)); // Sağ üst köşede bilgileri çiz
        }
      
    if (bilgiPaneli == null) {
            bilgiPaneli = new JFrame("Hazine Bilgileri");
            bilgiPaneli.setSize(600, 200); // Panel boyutunu genişlet
            bilgiPaneli.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            JPanel panel = new JPanel();
            JTextArea textArea1 = new JTextArea(); // Sırasız hazine bilgileri için
            JTextArea textArea2 = new JTextArea(); // Sıralı hazine bilgileri için
            panel.add(textArea1);
            panel.add(textArea2);
            bilgiPaneli.add(panel);
            bilgiPaneli.setVisible(true);
        }

        JTextArea textArea1 = (JTextArea) ((JPanel) bilgiPaneli.getContentPane().getComponent(0)).getComponent(0);
        JTextArea textArea2 = (JTextArea) ((JPanel) bilgiPaneli.getContentPane().getComponent(0)).getComponent(1);
        textArea1.setText(""); // Önceki metni temizle
        textArea2.setText(""); // Önceki metni temizle

        for (int i = 0; i < toplananHazineler.size(); i++) {
            Hazine hazine = toplananHazineler.get(i);
            Lokasyon konum = hazineKonumlari.get(i);
            int adimSayisi = adimSayilari.get(i); // Hazineyi kaç adımda bulduğumuzu al
            String hazineBilgisi = hazine.getAd() + " şu konumda bulundu: (" + konum.getY() + ", " + konum.getX() + "), " + adimSayisi + " adımda bulundu";
            textArea1.append(hazineBilgisi + "\n"); // Yeni panelde sırasız hazine bilgilerini çiz
        }

        // Hazineleri değerlerine göre sırala ve yeni panelde yazdır
        Collections.sort(siraliHazineler, new Comparator<Hazine>() {
            @Override
            public int compare(Hazine h1, Hazine h2) {
                return Integer.compare(h2.getDeger(), h1.getDeger());
            }
        });

        for (int i = 0; i < siraliHazineler.size(); i++) {
            Hazine hazine = siraliHazineler.get(i);
            Lokasyon konum = hazine.getKonum();
            int adimSayisi = adimSayilari.get(i); // Hazineyi kaç adımda bulduğumuzu al
            String hazineBilgisi = hazine.getAd() + " şu konumda bulundu: (" + konum.getY() + ", " + konum.getX() + "), " + adimSayisi + " adımda bulundu";
            textArea2.append(hazineBilgisi + "\n"); // Yeni panelde sıralı hazine bilgilerini çiz
    
}}}
