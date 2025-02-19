
package otonomhazineavcisi;
import java.util.List;
import java.util.ArrayList;

public class Path {
    private ArrayList<Node> path = new ArrayList<Node>();

    public int getLength() {
        return path.size();
    }

    public Node getNode(int index) {
        return path.get(index);
    }

    public int getX(int index) {
        return getNode(index).x;
    }

    public int getY(int index) {
        return getNode(index).y;
    }

    public void prependWayPoint(Node n) {
        path.add(0, n);
    }

    public boolean contains(int x, int y) {
        for(Node node : path) {
            if (node.x == x && node.y == y)
                return true;
        }
        return false;
    }
}
