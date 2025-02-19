
package otonomhazineavcisi;
import java.util.Random;

// Karakter.java
public class Karakter {
    private String ID;
    private String ad;
    private int[][] harita; // Haritaya bir referans ekle
    private Lokasyon lokasyon;
    

    public Karakter(String ID, String ad, int maxX, int maxY, int[][] harita) {
        this.ID = ID;
        this.ad = ad;
        this.harita = harita; // Haritayı başlat
        this.lokasyon = rastgeleBaslangicLokasyonu(maxX, maxY);
        
    }

    public String getID() {
        return ID;
    }

    public String getAd() {
        return ad;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    

    private Lokasyon rastgeleBaslangicLokasyonu(int maxX, int maxY) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(maxX);
            y = rand.nextInt(maxY);
        } while (harita[x][y] == -2); // Engellerin üzerine gelmemek için kontrol et
        return new Lokasyon(x, y);
    }
}