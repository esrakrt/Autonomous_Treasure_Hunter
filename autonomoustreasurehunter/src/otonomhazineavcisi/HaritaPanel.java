package otonomhazineavcisi;

    import javax.swing.*;
    import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
    import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.Timer;
    import java.util.List;
import javax.swing.JOptionPane;
    import java.util.ArrayList;
    import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class HaritaPanel extends JPanel {
        private boolean griBoyama = false; 
        private boolean[][] görünürHücreler;
        private double scale = 1.0;
        private long baslangicZamani;
        private boolean griButonunaBasildi = false;
        
        private int haritaBoyut;
        private List<Hazine> toplananHazineler = new ArrayList<>();
        private int[][] harita;
        private List<Lokasyon> sonKonumlar = new ArrayList<>();
private static final int MAX_SON_KONUM_SAYISI = 10000000;
        private Uygulama uygulama = new Uygulama();
        private Karakter karakter;
        private Image arkaPlanResmi;
        private Image karakterResmi;
        
        Engeller[][] engeller = new Engeller[haritaBoyut][haritaBoyut];

        Hazine[][] hazineler = new Hazine[haritaBoyut][haritaBoyut]; // Hazineler matrisi



        private Kus kus; // Kuş
        private Ari ari;
        private Image snowKayaResmi; // Snow Kaya resmi
        private Image sunKayaResmi; // Sun Kaya resmi
        private Image snowAgacResmi; // Snow Kaya resmi
        private Image sunAgacResmi; // Sun Kaya resmi
        private Image snowDagResmi; // Snow Kaya resmi
        private Image sunDagResmi; // Sun Kaya resmi
        private Image snowDuvarResmi; // Snow Kaya resmi
        private Image sunDuvarResmi; // Sun Kaya resmi
        // Hazine sandıklarını oluştur
    Image altinResmi = new ImageIcon("cgold.gif").getImage(); // Altın resmini yükle
    Image gumusResmi = new ImageIcon("cgumus.gif").getImage(); // Gümüş resmini yükle
    Image zumrutResmi = new ImageIcon("czumrut.gif").getImage(); // Zümrüt resmini yükle
    Image bakirResmi = new ImageIcon("cbronz.gif").getImage(); // Bakır resmini yükle

    // Her hazine türü için bir hazine oluştur
    // Her hazine türü için bir hazine oluştur


// Hazineyi rastgele bir konuma yerleştirir ve engellerle çakışmamasını sağlar
private void altinSandikYerlestir(Image resim) {
    Random rand = new Random();
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut);
    } while (engelleCakisiyorMu(x, y, 2, 2) || !engelCevresiBosMu(x, y, 2, 2) || harita[x][y] == -2); // Hazine'nin boyutları
    hazineler[x][y] = new AltinSandik(new Lokasyon(x, y), resim); // Yeni bir hazine oluştur ve matrise ekle
    harita[x][y] = 9;
    for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        
    }
}



}

        private void gumusSandikYerlestir(Image resim) {
    Random rand = new Random();
    
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut);
    } while (engelleCakisiyorMu(x, y, 2, 2) || !engelCevresiBosMu(x, y, 2, 2) || harita[x][y] == -2);
    hazineler[x][y] = new GumusSandik(new Lokasyon(x, y), resim); // Yeni bir hazine oluştur ve matrise ekle
    harita[x][y] = 8;
    for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        
    }
}



}

private void zumrutSandikYerlestir(Image resim) {
    Random rand = new Random();
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut);
    } while (engelleCakisiyorMu(x, y, 2, 2) || !engelCevresiBosMu(x, y, 2, 2) || harita[x][y] == -2);
    hazineler[x][y] = new ZumrutSandik(new Lokasyon(x, y), resim); // Yeni bir hazine oluştur ve matrise ekle
    harita[x][y] = 7;
    for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        
    }
}



}

private void bakirSandikYerlestir(Image resim) {
    Random rand = new Random();
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut);
    } while (engelleCakisiyorMu(x, y, 2, 2) || !engelCevresiBosMu(x, y, 2, 2) || harita[x][y] == -2);
    hazineler[x][y] = new BakirSandik(new Lokasyon(x, y), resim); // Yeni bir hazine oluştur ve matrise ekle
    harita[x][y] = 6;
    for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        
    }
}



}

private boolean tumHazinelerToplandi() {
        for (int i = 0; i < haritaBoyut; i++) {
            for (int j = 0; j < haritaBoyut; j++) {
                if (hazineler[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean engelleCakisiyorMu(int x, int y, int boyutX, int boyutY) {
    // Engellerle çakışmayı kontrol et
    for (int i = 0; i < haritaBoyut; i++) {
        for (int j = 0; j < haritaBoyut; j++) {
            Engeller engel = engeller[i][j];
            if (engel != null) {
                Lokasyon konum = engel.getKonum();
                int engelBoyutX = engel.getBoyutY();
                int engelBoyutY = engel.getBoyutX();
                if ((x < konum.getX()+ engelBoyutY && x + boyutY > konum.getX()) && 
                    (y < konum.getY() + engelBoyutX && y + boyutX > konum.getY())) {
                    return true;
                }
            }
        }
    }

    // Hazinelerle çakışmayı kontrol et
/*for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        Hazine hazine = hazineler[i][j];
        if (hazine != null) {
            Lokasyon konum = hazine.getKonum();
            // Hazine'nin boyutları varsayılan olarak 1x1 kabul edilmiştir
            if ((x < konum.getX() + 1 && x + boyutX > konum.getX()) && 
                (y < konum.getY() + 1 && y + boyutY > konum.getY())) {
                return true;
            }
        }
    }
}*/


    return false;
}
    private boolean engelCevresiBosMu(int x, int y, int boyutX, int boyutY) {
    // Engel çevresindeki hücreleri kontrol et
    for (int i = Math.max(0, x - 5); i <= Math.min(haritaBoyut - 1, x + boyutX + 4); i++) {
        for (int j = Math.max(0, y - 5); j <= Math.min(haritaBoyut - 1, y + boyutY + 4); j++) {
            // Eğer hücrede bir engel varsa, false döndür
            if (harita[i][j] == -2) {
                return false;
            }
        }
    }
    // Eğer hiçbir hücrede engel yoksa, true döndür
    return true;
}
  
private boolean hazineCevresiBosMu(int x, int y, int boyutX, int boyutY) {
    // Hazine çevresindeki hücreleri kontrol et
    for (int i = Math.max(0, x - 10); i <= Math.min(haritaBoyut - 1, x + boyutX); i++) {
        for (int j = Math.max(0, y - 10); j <= Math.min(haritaBoyut - 1, y + boyutY); j++) {
            // Eğer hücrede bir hazine veya engel varsa, false döndür
            if (harita[i][j] == 6 || harita[i][j] == 7 || harita[i][j] == 8 || harita[i][j] == 9 || harita[i][j] == -2) {
                return false;
            }
        }
    }
    // Eğer hiçbir hücrede hazine veya engel yoksa, true döndür
    return true;
}





        public void setArkaPlanResmi(Image arkaPlanResmi) {
        this.arkaPlanResmi = arkaPlanResmi;
    }
        public Image getArkaPlanResmi() {
        return arkaPlanResmi;
    }
        
        public HaritaPanel(int haritaBoyut) {
            this.haritaBoyut = haritaBoyut;
            this.harita = new int[haritaBoyut][haritaBoyut];
            this.karakter = new Karakter("1", "Oyuncu", haritaBoyut, haritaBoyut,harita); // Örnek bir oyuncu
            this.arkaPlanResmi = new ImageIcon("map1.png").getImage(); // Arka plan resmini yükle
            this.karakterResmi = new ImageIcon("itachi.gif").getImage(); // Karakter resmini yükle
            this.hazineler = new Hazine[haritaBoyut][haritaBoyut]; // Hazineler matrisini oluştur
            this.engeller = new Engeller[haritaBoyut][haritaBoyut];
            this.görünürHücreler = new boolean[haritaBoyut][haritaBoyut];
           addMouseWheelListener(new MouseWheelListener() {
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            // Mouse wheel yukarı doğru döndürüldü, yakınlaştır
            scale *= 1.1; // Ölçeklendirme faktörünü artır
        } else {
            // Mouse wheel aşağı doğru döndürüldü, uzaklaştır
            scale /= 1.1; // Ölçeklendirme faktörünü azalt
        }
        repaint(); // Haritayı yeniden çiz
    }
});

            JButton btnGri = new JButton("SİS");  // yeni eklenen buton
        btnGri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                griBoyama = true;
                griButonunaBasildi = true;
                repaint();
            }
        });
        add(btnGri);
            JButton btnGo = new JButton("Git");
        final Timer timer = new Timer(200, null);
timer.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if (!griButonunaBasildi) {
                    return;  // Eğer "Gri" butonuna basılmadıysa, hareket etme
                }
                hareketEt();
        if (tumHazinelerToplandi()) {
    timer.stop();
    btnGo.setText("Git");
    long bitisZamani = System.currentTimeMillis();
long gecenSure = bitisZamani - baslangicZamani;
long dakika = gecenSure / (60 * 1000);
long kalanMilisaniye = gecenSure % (60 * 1000);
long saniye = kalanMilisaniye / 1000;
long kalanMilisaniye2 = kalanMilisaniye % 1000;
int hareketEdilenKareSayisi = sonKonumlar.size();  // Hareket edilen kare sayısını hesapla
JOptionPane.showMessageDialog(null, "Tüm hazineler " + dakika + " dakika " + saniye + " saniye " + kalanMilisaniye2 + " milisaniye de toplandı ve toplam " + hareketEdilenKareSayisi + " kare üzerinden geçildi!");
        }
    }
});
btnGo.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if (timer.isRunning()) {
            timer.stop();
            btnGo.setText("Git");
        } else {
            baslangicZamani = System.currentTimeMillis();
            timer.start();
            btnGo.setText("Durdur");
        }
    }
});
        add(btnGo);


// Kuşları oluştur
Image kusResmi = new ImageIcon("bird(1).gif").getImage(); // Kuş resmini yükle
Random rand = new Random();
int kusayisi = Math.max(2, haritaBoyut / 50);
for (int i = 0; i < kusayisi; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut); // Rastgele x konumu
        y = rand.nextInt(haritaBoyut); // Rastgele y konumu
    } while (engelleCakisiyorMu(x, y, 10, 10) || !engelCevresiBosMu(x, y, 10, 10));// Kaya'nın boyutları
    Kus kus = new Kus(x, y, kusResmi, haritaBoyut); // Yeni bir kuş oluştur
    engeller[x][y] = kus; // Kuşu engeller matrisine ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}

// Arıları oluştur
Image ariResmi = new ImageIcon("bee.png").getImage(); // Arı resmini yükle
int arisayisi = Math.max(2, haritaBoyut / 50);
for (int i = 0; i < arisayisi; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut); // Rastgele x konumu
        y = rand.nextInt(haritaBoyut); // Rastgele y konumu
    } while (engelleCakisiyorMu(x, y, 10, 10) || !engelCevresiBosMu(x, y, 10, 10)); // Kaya'nın boyutları
    Ari ari = new Ari(x, y, ariResmi, haritaBoyut); // Yeni bir arı oluştur
    engeller[x][y] = ari; // Arıyı engeller matrisine ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}


// Zamanlayıcıyı oluştur
int gecikme = 200; // 200 milisaniye gecikme ile
ActionListener taskPerformer = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        // Her zamanlayıcı tetiklendiğinde tüm arıların ve kuşların hareketEt metodunu çağır
        for (int i = 0; i < haritaBoyut; i++) {
            for (int j = 0; j < haritaBoyut; j++) {
                Engeller engel = engeller[i][j];
                if (engel instanceof Kus || engel instanceof Ari) { // Engel bir kuş veya arı ise
                    HareketliEngeller hareketliEngel = (HareketliEngeller) engel; // Engel nesnesini HareketliEngeller nesnesine dönüştür
                    hareketliEngel.hareketEt(); // Hareketli engelin hareketEt metodunu çağır
                }
            }
        }
        repaint(); // Paneli yeniden çiz
    }
};
new Timer(gecikme, taskPerformer).start(); // Zamanlayıcıyı başlat

            rastgeleHaritaOlustur();
            
        }

        
        
        private void rastgeleHaritaOlustur() {
            Random rand = new Random();

     
// Dağları yerleştir (sağ yarıda)
int dagSayisi2 = Math.max(3, haritaBoyut / 200); // Dağların sayısı haritaboyut/500 kadar olacak, ama en az 2 dağ oluşacak
for (int i = 0; i < dagSayisi2; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut - 20) +10; // 10 birim içeride
        y = rand.nextInt(haritaBoyut / 2 - 20) + haritaBoyut / 2 + 10; // Sağ yarıda ve 10 birim içeride
    } while (engelleCakisiyorMu(x, y, 15, 15)); // Dağın boyutları
    Image resim = new ImageIcon("dag.png").getImage(); // Dağ resmini yükle
    engeller[x][y] = new Dag(x, y, resim); // Yeni bir Dağ ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}

// Snow Dağları yerleştir (sol yarıda)
int dagSayisi = Math.max(3, haritaBoyut / 200); // Dağların sayısı haritaboyut/500 kadar olacak, ama en az 2 dağ oluşacak
for (int i = 0; i < dagSayisi; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut - 20); // Dağın genişliği hesaba katıldı
        y = rand.nextInt((haritaBoyut / 2) - 15); // Dağın yüksekliği hesaba katıldı
    } while (engelleCakisiyorMu(x, y, 15, 15)); // Dağın boyutları
    Image resim = new ImageIcon("snowdag.png").getImage(); // Snow Dağ resmini yükle
    engeller[x][y] = new Dag(x, y, resim); // Yeni bir Snow Dağ ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}


// Snow Duvarları yerleştir (sol yarıda)
int duvarSayisi2 = Math.max(2, haritaBoyut / 500);
for (int i = 0; i < duvarSayisi2; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut / 2); // Sol yarıda
    } while (engelleCakisiyorMu(x, y, 10, 1)); // Duvarın boyutları
    Image resim = new ImageIcon("snowduvar.png").getImage(); // Snow Duvar resmini yükle
    engeller[x][y] = new Duvar(x, y, resim); // Yeni bir Snow Duvar ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}

// Duvarları yerleştir (sağ yarıda)
int duvarSayisi = Math.max(2, haritaBoyut / 500);
for (int i = 0; i < duvarSayisi; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut / 2) + haritaBoyut / 2; // Sağ yarıda
    } while (engelleCakisiyorMu(x, y, 10, 1)); // Duvarın boyutları
    Image resim = new ImageIcon("duvar.png").getImage(); // Duvar resmini yükle
    engeller[x][y] = new Duvar(x, y, resim); // Yeni bir Duvar ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}
int kayaSayisi = Math.max(2, haritaBoyut / 40);
for (int i = 0; i < kayaSayisi; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut / 2)+ haritaBoyut / 2; // Sol yarıda
    } while (engelleCakisiyorMu(x, y, 2, 2) || !engelCevresiBosMu(x, y, 2, 2) || harita[x][y] >= 6); // Kaya'nın boyutları ve çevresi
    Image resim = new ImageIcon("kaya.png").getImage(); // Snow Kaya resmini yükle
    engeller[x][y] = new Kaya(x, y, resim); // Yeni bir Snow Kaya ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}

// Kayaları yerleştir
int kayaSayisi2 = Math.max(2, haritaBoyut / 40);
for (int i = 0; i < kayaSayisi2; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut / 2); // Sol yarıda
    } while (engelleCakisiyorMu(x, y, 4, 4) || !engelCevresiBosMu(x, y, 4, 4) || harita[x][y] >= 6); // Kaya'nın boyutları ve çevresi
    Image resim = new ImageIcon("snowkaya.png").getImage(); // Snow Kaya resmini yükle
    engeller[x][y] = new Kaya(x, y, resim); // Yeni bir Snow Kaya ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}

// Snow Ağaçları yerleştir (sol yarıda)
int agacSayisi = Math.max(2, haritaBoyut / 10);
for (int i = 0; i < agacSayisi; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut / 2); // Sol yarıda
    } while (engelleCakisiyorMu(x, y, 4, 4) || !engelCevresiBosMu(x, y, 4, 4)); // Ağaç'ın boyutları ve çevresi
    Image resim = new ImageIcon("snowtree.png").getImage(); // Snow Ağaç resmini yükle
    engeller[x][y] = new Agac(x, y, resim); // Yeni bir Snow Ağaç ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}


// Ağaçları yerleştir (sağ yarıda)
int agacSayisi2 = Math.max(2, haritaBoyut / 10);
for (int i = 0; i < agacSayisi2; i++) {
    int x, y;
    do {
        x = rand.nextInt(haritaBoyut);
        y = rand.nextInt(haritaBoyut / 2)+ haritaBoyut / 2; // Sol yarıda
    } while (engelleCakisiyorMu(x, y, 4, 4) || !engelCevresiBosMu(x, y, 4, 4)); // Ağaç'ın boyutları ve çevresi
    Image resim = new ImageIcon("tree.png").getImage(); // Snow Ağaç resmini yükle
    engeller[x][y] = new Agac(x, y, resim); // Yeni bir Snow Ağaç ekle
    harita[x][y] = -2; // Engelin olduğu konumu işaretle
}




        ////////////////////
        //////////////////////
        if (haritaBoyut > 0) {
for (int i = 0; i < Math.max(5, haritaBoyut / 100); i++) {
    altinSandikYerlestir(altinResmi);
    gumusSandikYerlestir(gumusResmi);
    zumrutSandikYerlestir(zumrutResmi);
    bakirSandikYerlestir(bakirResmi);
}

}
 else {
        System.out.println("Hata");
    }
        
        
        
        ///////////////////////
        //////////////////////////////

            
            
            
            
        }

    public void hareketEt() {
    

    Lokasyon hedef = enYakinHazineyiBul();
    if (hedef == null) {
        return;
    }

    int deltaX = hedef.getX() - karakter.getLokasyon().getX();
    int deltaY = hedef.getY() - karakter.getLokasyon().getY();

    // Hedefe bir birimden daha yakınsa, doğrudan hedefe git
if (Math.abs(deltaX) <= 1 && deltaY == 0) {
    karakter.setLokasyon(hedef);
} else if (Math.abs(deltaY) <= 1 && deltaX == 0) {
    karakter.setLokasyon(hedef);
} else {
         // Yatay veya dikey hareket et
    if (Math.abs(deltaX) > Math.abs(deltaY)) {
        // X ekseni boyunca hareket et
        Lokasyon yeniKonum = new Lokasyon(karakter.getLokasyon().getX() + (deltaX > 0 ? 1 : -1), karakter.getLokasyon().getY());
        if (gecerliKonum(yeniKonum.getX(), yeniKonum.getY()) && !sonKonumlar.contains(yeniKonum)) {
            karakter.setLokasyon(yeniKonum);
            sonKonumlar.add(new Lokasyon(yeniKonum.getX(), yeniKonum.getY()));
            uygulama.hareketEt(); // Hareket sayısını artır
        } else {
            rastgeleHareketEt();
        }
    } else {
        // Y ekseni boyunca hareket et
        Lokasyon yeniKonum = new Lokasyon(karakter.getLokasyon().getX(), karakter.getLokasyon().getY() + (deltaY > 0 ? 1 : -1));
        if (gecerliKonum(yeniKonum.getX(), yeniKonum.getY()) && !sonKonumlar.contains(yeniKonum)) {
            karakter.setLokasyon(yeniKonum);
            sonKonumlar.add(new Lokasyon(yeniKonum.getX(), yeniKonum.getY()));
            uygulama.hareketEt(); // Hareket sayısını artır
        } else {
            rastgeleHareketEt();
        }
        }
    }

    Hazine hazine = hazineler[karakter.getLokasyon().getX()][karakter.getLokasyon().getY()];
    if (hazine != null) {
        hazineler[karakter.getLokasyon().getX()][karakter.getLokasyon().getY()] = null;
        hedef = null; 
        
        // Hazine toplandığında, bu konumu sonKonumlar listesine ekle
        sonKonumlar.add(new Lokasyon(karakter.getLokasyon().getX(), karakter.getLokasyon().getY()));

        uygulama.hazineTopla(hazine, karakter.getLokasyon());

        // Tüm hazinelerin toplandığını kontrol et
        boolean tumHazinelerToplandi = true;
        for (int i = 0; i < haritaBoyut; i++) {
            for (int j = 0; j < haritaBoyut; j++) {
                if (hazineler[i][j] != null) {
                    tumHazinelerToplandi = false;
                    break;
                }
            }
            if (!tumHazinelerToplandi) {
                break;
            }
        }

        
    }
    // Karakterin etrafındaki hücreleri görünür yap
        int minX = Math.max(0, karakter.getLokasyon().getX() - 3);
        int maxX = Math.min(haritaBoyut, karakter.getLokasyon().getX() + 4);
        int minY = Math.max(0, karakter.getLokasyon().getY() - 3);
        int maxY = Math.min(haritaBoyut, karakter.getLokasyon().getY() + 4);
        if (griBoyama) {
            for (int i = minX; i < maxX; i++) {
                for (int j = minY; j < maxY; j++) {
                    görünürHücreler[i][j] = true;
                }
            }
        }
}




private void rastgeleHareketEt() {
    Random rand = new Random();
    int hareket = rand.nextInt(4); // 0: Yukarı, 1: Aşağı, 2: Sağ, 3: Sol

    int deltaX = 0;
    int deltaY = 0;

    switch (hareket) {
        case 0: // Yukarı
            deltaX = -1;
            break;
        case 1: // Aşağı
            deltaX = 1;
            break;
        case 2: // Sağ
            deltaY = 1;
            break;
        case 3: // Sol
            deltaY = -1;
            break;
    }

    if (gecerliKonum(karakter.getLokasyon().getX() + deltaX, karakter.getLokasyon().getY() + deltaY)) {
        karakter.getLokasyon().setX(karakter.getLokasyon().getX() + deltaX);
        karakter.getLokasyon().setY(karakter.getLokasyon().getY() + deltaY);

        // Karakterin yeni konumunu sonKonumlar listesine ekle
        sonKonumlar.add(new Lokasyon(karakter.getLokasyon().getX(), karakter.getLokasyon().getY()));
    }
}






private Lokasyon enYakinHazineyiBul() {
    Lokasyon enYakin = null;
    double enKisaMesafe = Double.MAX_VALUE;

    for (int i = 0; i < hazineler.length; i++) {
        for (int j = 0; j < hazineler[i].length; j++) {
            Hazine hazine = hazineler[i][j];
            if (hazine != null) {
                double mesafe = Math.sqrt(Math.pow(hazine.getKonum().getX() - karakter.getLokasyon().getX(), 2) +
                                          Math.pow(hazine.getKonum().getY() - karakter.getLokasyon().getY(), 2));
                if (mesafe < enKisaMesafe) {
                    enKisaMesafe = mesafe;
                    enYakin = hazine.getKonum();
                }
            }
        }
    }

    return enYakin;
}

        
      private boolean gecerliKonum(int x, int y) {
    // Verilen koordinatların geçerli olup olmadığını kontrol et
    if (y < 0 || y >= haritaBoyut || x < 0 || x >= haritaBoyut || harita[y][x] == -1) {
        return false;
    }
    
    // Engellerin üzerinden geçilemez
    for (int i = 0; i < haritaBoyut; i++) {
        for (int j = 0; j < haritaBoyut; j++) {
            Engeller engel = engeller[i][j];
            if (engel != null) {
                Lokasyon konum = engel.getKonum();
                int boyutY = engel.getBoyutY();
                int boyutX = engel.getBoyutX();
                if (y >= konum.getY() && y < konum.getY() + boyutY && x >= konum.getX() && x < konum.getX() + boyutX) {
                    return false;
                }
            }
        }
    }

    // Kuşların ve arıların hareket ettiği konumlar üzerinden geçilemez
    for (int i = 0; i < haritaBoyut; i++) {
        for (int j = 0; j < haritaBoyut; j++) {
            Engeller engel = engeller[i][j];
            if (engel instanceof Kus || engel instanceof Ari) {
                Set<Lokasyon> gecilemezKonumlar = ((HareketliEngeller) engel).getGecilemezKonumlar();
                for (Lokasyon konum : gecilemezKonumlar) {
                    // Kuşların ve arıların tüm alanını kontrol et
                    for (int dx = 0; dx < engel.getBoyutX(); dx++) {
                        for (int dy = 0; dy < engel.getBoyutY(); dy++) {
                            if (y == konum.getY() + dy && x == konum.getX() + dx) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
    }

    return true;
}


        protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    
    // Arka plan resmini çiz
int kareBoyut = getWidth() / haritaBoyut;
// Karakterin konumunu al
    int karakterY = karakter.getLokasyon().getX() * kareBoyut;
    int karakterX = karakter.getLokasyon().getY() * kareBoyut;

    // Yakınlaştırma merkezini karakterin konumuna ayarla
    g2d.translate(getWidth() / 2, getHeight() / 2);
    g2d.scale(scale, scale);
    g2d.translate(-karakterX, -karakterY);
g.drawImage(arkaPlanResmi, 0, 0, kareBoyut * haritaBoyut, kareBoyut * haritaBoyut, this);
 
// Arının hareket ettiği konumları kırmızı renkle boyama
for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        Engeller engel = engeller[i][j];
        if (engel instanceof Ari) {
            Set<Lokasyon> gecilemezKonumlar = ((Ari) engel).getGecilemezKonumlar();
            for (Lokasyon konum : gecilemezKonumlar) {
                g.setColor(Color.RED);
                // Arının tüm alanını boyar
                for (int dx = 0; dx < engel.getBoyutX(); dx++) {
                    for (int dy = 0; dy < engel.getBoyutY(); dy++) {
                        g.fillRect((konum.getY() + dy) * kareBoyut, (konum.getX() + dx) * kareBoyut, kareBoyut, kareBoyut);
                    }
                }
            }
        }
    }
}
  // kusların hareket ettiği konumları kırmızı renkle boyama
for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        Engeller engel = engeller[i][j];
        if (engel instanceof Kus) {
            Set<Lokasyon> gecilemezKonumlar = ((Kus) engel).getGecilemezKonumlar();
            for (Lokasyon konum : gecilemezKonumlar) {
                g.setColor(Color.RED);
                // Arının tüm alanını boyar
                for (int dx = 0; dx < engel.getBoyutX(); dx++) {
                    for (int dy = 0; dy < engel.getBoyutY(); dy++) {
                        g.fillRect((konum.getY() + dy) * kareBoyut, (konum.getX() + dx) * kareBoyut, kareBoyut, kareBoyut);
                    }
                }
            }
        }
    }
}


    // Engelleri çiz
for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        Engeller engel = engeller[i][j];
        if (engel != null) {
            Lokasyon konum = engel.getKonum();
            int boyutX = engel.getBoyutY();
            int boyutY = engel.getBoyutX();
            Image resim = engel.getResim();
            g.drawImage(resim, konum.getY() * kareBoyut, konum.getX() * kareBoyut, kareBoyut * boyutX, kareBoyut * boyutY, this);
        }
    }
}
for (int i = 0; i < haritaBoyut; i++) {
    for (int j = 0; j < haritaBoyut; j++) {
        Hazine hazine = hazineler[i][j];
        if (hazine != null) {
            Lokasyon konum = hazine.getKonum();
            Image resim = hazine.getResim();
            g.drawImage(resim, konum.getY() * kareBoyut, konum.getX() * kareBoyut, kareBoyut, kareBoyut, this);
            
        }
        
    }
    
}

    if (griBoyama) {
            for (int i = 0; i < haritaBoyut; i++) {
                for (int j = 0; j < haritaBoyut; j++) {
                    if (!görünürHücreler[i][j]) {
                        g.setColor(Color.GRAY);
                        g.fillRect(j * kareBoyut, i * kareBoyut, kareBoyut, kareBoyut);
                    }
                    g.setColor(Color.BLACK);
                    g.drawRect(j * kareBoyut, i * kareBoyut, kareBoyut, kareBoyut);
                }
            }
        }
    
    // Hareketsiz engellerin tüm alanını kırmızı renkle boyama
    /*for (int i = 0; i < haritaBoyut; i++) {
        for (int j = 0; j < haritaBoyut; j++) {
            Engeller engel = engeller[i][j];
            if (engel instanceof HareketsizEngeller) {
                g.setColor(Color.RED);
                // Hareketsiz engelin tüm alanını boyar
                for (int dx = 0; dx < engel.getBoyutX(); dx++) {
                    for (int dy = 0; dy < engel.getBoyutY(); dy++) {
                        g.fillRect((engel.getKonum().getY() + dy) * kareBoyut, (engel.getKonum().getX() + dx) * kareBoyut, kareBoyut, kareBoyut);
                    }
                }
            }
        }
    }*/
   
uygulama.ekranaYazdir(g, getHeight(), getWidth());
// Karakterin geçmiş konumlarını yeşil olarak boyar
    g.setColor(Color.GREEN);
    for (Lokasyon konum : sonKonumlar) {
        g.fillRect(konum.getY() * kareBoyut, konum.getX() * kareBoyut, kareBoyut, kareBoyut);
    }
// Karakteri çiz
    g.drawImage(karakterResmi, karakter.getLokasyon().getY() * kareBoyut, karakter.getLokasyon().getX() * kareBoyut, kareBoyut, kareBoyut, this);
    
}}




