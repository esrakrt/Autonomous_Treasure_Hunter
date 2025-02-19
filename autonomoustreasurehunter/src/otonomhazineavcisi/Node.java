
package otonomhazineavcisi;

public class Node {
    public int x;
    public int y;
    public float cost;
    public Node parent;
    public float heuristic;
    public int depth;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int setParent(Node parent) {
        depth = parent.depth + 1;
        this.parent = parent;

        return depth;
    }
}
