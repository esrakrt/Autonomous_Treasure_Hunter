package otonomhazineavcisi;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.Random; // Random sınıfını import et
public class Hazine {
    private Lokasyon konum;
    private Image resim;
    private String ad; // Hazine türünün adını tutan bir alan
    protected int boyutX;
    protected int deger;
    protected int boyutY;

    public Hazine(Lokasyon konum, Image resim, String ad) {
        this.konum = konum;
        
        this.resim = resim;
        this.ad = ad;
        this.boyutX = boyutX;
        this.boyutY = boyutY;
    }
    public int getBoyutX() {
        return boyutX;
    }
    public int getDeger() {
        return this.deger;
    }
    public int getBoyutY() {
        return boyutY;
    }
    public Lokasyon getKonum() {
        return konum;
    }
    
    public String getAd() {
        return ad;
    }
    public Image getResim() {
        return resim;
    }
}

class AltinSandik extends Hazine {
    public AltinSandik(Lokasyon konum, Image resim) {
        super(konum, resim, "Altın Sandık");
        this.deger = 9; // Altın için değer 9
    }
    public int getDeger() {
        return this.deger;
    }
    // Diğer metotlar...
}

class GumusSandik extends Hazine {
    public GumusSandik(Lokasyon konum, Image resim) {
        super(konum, resim, "Gümüş Sandık");
    this.deger = 8; // Altın için değer 9
    }
    public int getDeger() {
        return this.deger;
    }
}

class ZumrutSandik extends Hazine {
    public ZumrutSandik(Lokasyon konum, Image resim) {
        super(konum, resim, "Zümrüt Sandık");
    this.deger = 7; // Altın için değer 9
    }
    public int getDeger() {
        return this.deger;
    }
}

class BakirSandik extends Hazine {
    public BakirSandik(Lokasyon konum, Image resim) {
        super(konum, resim, "Bakır Sandık");
    this.deger = 6; // Altın için değer 9
    }
    public int getDeger() {
        return this.deger;
    }
}

