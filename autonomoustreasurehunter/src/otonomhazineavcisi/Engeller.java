
package otonomhazineavcisi;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;

import javax.swing.ImageIcon;
import java.util.Random; // Random sınıfını import et

public abstract class Engeller {
    protected Lokasyon konum;
    protected int boyutX;
    protected int boyutY;
    protected Image resim;

    public Engeller(int x, int y, int boyutX, int boyutY, Image resim) {
        this.konum = new Lokasyon(x, y);
        this.boyutX = boyutX;
        this.boyutY = boyutY;
        this.resim = resim;
    }
    public Lokasyon getKonum() {
        return konum;
    }

    public int getBoyutX() {
        return boyutX;
    }
    
    public int getBoyutY() {
        return boyutY;
    }

    public Image getResim() {
        return resim;
    }
}

abstract class HareketliEngeller extends Engeller {
    public HareketliEngeller(int x, int y, Image resim) {
        super(x, y, 2,2, resim); // Boyutu 2x2 olarak belirledik
    }

    public abstract void hareketEt();
    public abstract Set<Lokasyon> getGecilemezKonumlar();
}


class HareketsizEngeller extends Engeller {
    public HareketsizEngeller(int x, int y, int boyutX, int boyutY, Image resim) {
        super(x, y, boyutX,boyutY,resim);
    }
}

class Dag extends HareketsizEngeller {
    public Dag(int x, int y, Image resim) {
        super(x, y, 15, 15, resim); 
    }
}

class Duvar extends HareketsizEngeller {
    public Duvar(int x, int y, Image resim) {
        super(x, y, 1, 10, resim); 
    }
}

class Kaya extends HareketsizEngeller {
    private static final Random rand = new Random();

    public Kaya(int x, int y, Image resim) {
        this(x, y, rand.nextInt(2) + 2, resim);
    }

    private Kaya(int x, int y, int boyut, Image resim) {
        super(x, y, boyut, boyut, resim); 
    }
}

class Agac extends HareketsizEngeller {
    private static final Random rand = new Random(); // Random nesnesi oluştur

    public Agac(int x, int y, Image resim) {
        this(x, y, rand.nextInt(4) + 2, resim);
    }

    private Agac(int x, int y, int boyut, Image resim) {
        super(x, y, boyut, boyut, resim); 
    }
}

class Kus extends HareketliEngeller {
    private static final int HAREKET_MESAFESI = 2; 
    private int baslangicX;
    private int baslangicY;
    private int hareketYonu = 1; 
    private int haritaBoyut; 
    private Set<Lokasyon> gecilemezKonumlar = new HashSet<>(); // Kuşun hareket ettiği tüm konumları saklayan bir set

    public Kus(int x, int y, Image resim, int haritaBoyut) {
        super(x, y, resim);
        this.baslangicX = x; // Başlangıç x konumunu sakla
        this.baslangicY = y; // Başlangıç y konumunu sakla
        this.haritaBoyut = haritaBoyut; // Harita boyutunu sakla
        gecilemezKonumlar.add(new Lokasyon(x, y)); // Başlangıç konumunu set'e ekle
    }

    @Override
    public void hareketEt() {
        // Yeni x konumu hesapla
        int yeniX = konum.getX() + hareketYonu;

        // Eğer kuş hareket alanının dışına çıkarsa, hareket yönünü değiştir
        if (yeniX < baslangicX - HAREKET_MESAFESI || yeniX > baslangicX + HAREKET_MESAFESI) {
            hareketYonu *= -1;
        } else {
            // Aksi halde, kuşun konumunu güncelle
            konum.setX(Math.max(0, Math.min(haritaBoyut - 1, yeniX)));
            gecilemezKonumlar.add(new Lokasyon(konum.getX(), konum.getY())); // Yeni konumu set'e ekle
        }
    }

    public Set<Lokasyon> getGecilemezKonumlar() {
        return gecilemezKonumlar;
    }
}
class Ari extends HareketliEngeller {
    private static final int HAREKET_MESAFESI = 2; 
    private int baslangicY;
    private int hareketYonu = 1; 
    private int haritaBoyut; 
    private Set<Lokasyon> gecilemezKonumlar = new HashSet<>(); // Arının hareket ettiği tüm konumları saklayan bir set

    public Ari(int x, int y, Image resim, int haritaBoyut) {
        super(x, y, resim);
        this.baslangicY = y; // Başlangıç x konumunu sakla
        this.haritaBoyut = haritaBoyut; // Harita boyutunu sakla
        gecilemezKonumlar.add(new Lokasyon(x, y)); // Başlangıç konumunu set'e ekle
    }

    @Override
    public void hareketEt() {
        // Yeni y konumu hesapla
        int yeniY = konum.getY() + hareketYonu;

        if (yeniY < baslangicY - HAREKET_MESAFESI || yeniY > baslangicY + HAREKET_MESAFESI) {
            hareketYonu *= -1;
        } else {
            konum.setY(Math.max(0, Math.min(haritaBoyut - 1, yeniY)));
            gecilemezKonumlar.add(new Lokasyon(konum.getX(), yeniY));
        }
    }

    public Set<Lokasyon> getGecilemezKonumlar() {
        return gecilemezKonumlar;
    }
}
