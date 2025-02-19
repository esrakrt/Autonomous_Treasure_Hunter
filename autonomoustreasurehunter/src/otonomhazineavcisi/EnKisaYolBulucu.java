package otonomhazineavcisi;

import java.util.*;

public class EnKisaYolBulucu {

    private int[][] harita;
    private int haritaBoyut;
    private Lokasyon baslangic;
    private Lokasyon hedef;

    public EnKisaYolBulucu(int[][] harita, Lokasyon baslangic, Lokasyon hedef) {
        this.harita = harita;
        this.haritaBoyut = harita.length;
        this.baslangic = baslangic;
        this.hedef = hedef;
    }

    public List<Lokasyon> bulEnKisaYol() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        Set<Lokasyon> closedSet = new HashSet<>();
        Map<Lokasyon, Lokasyon> cameFrom = new HashMap<>();
        Map<Lokasyon, Integer> gScore = new HashMap<>();

        openSet.add(new Node(baslangic, 0, hesaplaManhattanMesafe(baslangic, hedef)));

        gScore.put(baslangic, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            Lokasyon currentLocation = current.getLocation();

            if (currentLocation.equals(hedef)) {
                return rekonstruksiyon(cameFrom, hedef);
            }

            closedSet.add(currentLocation);

            for (Lokasyon komsu : getKomsular(currentLocation)) {
                if (closedSet.contains(komsu)) {
                    continue;
                }

                int yeniGScore = gScore.get(currentLocation) + 1;

                if (!gScore.containsKey(komsu) || yeniGScore < gScore.get(komsu)) {
                    gScore.put(komsu, yeniGScore);
                    int hScore = hesaplaManhattanMesafe(komsu, hedef);
                    int fScore = yeniGScore + hScore;

                    Node komsuNode = new Node(komsu, yeniGScore, hScore);
                    openSet.add(komsuNode);

                    cameFrom.put(komsu, currentLocation);
                }
            }
        }

        return Collections.emptyList(); // Hedefe ulaşılamadı
    }

    private List<Lokasyon> rekonstruksiyon(Map<Lokasyon, Lokasyon> cameFrom, Lokasyon current) {
        List<Lokasyon> yol = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            yol.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(yol);
        return yol;
    }

    private List<Lokasyon> getKomsular(Lokasyon lokasyon) {
        List<Lokasyon> komsular = new ArrayList<>();
        int x = lokasyon.getX();
        int y = lokasyon.getY();

        if (x > 0) {
            komsular.add(new Lokasyon(x - 1, y));
        }
        if (x < haritaBoyut - 1) {
            komsular.add(new Lokasyon(x + 1, y));
        }
        if (y > 0) {
            komsular.add(new Lokasyon(x, y - 1));
        }
        if (y < haritaBoyut - 1) {
            komsular.add(new Lokasyon(x, y + 1));
        }

        return komsular;
    }

    private int hesaplaManhattanMesafe(Lokasyon from, Lokasyon to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    private static class Node {
        private Lokasyon location;
        private int g; // Başlangıç noktasından bu noktaya olan maliyet
        private int h; // Bu noktadan hedefe olan tahmini maliyet

        public Node(Lokasyon location, int g, int h) {
            this.location = location;
            this.g = g;
            this.h = h;
        }

        public Lokasyon getLocation() {
            return location;
        }

        public int getF() {
            return g + h;
        }
    }
}
