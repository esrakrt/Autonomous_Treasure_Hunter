
/*package otonomhazineavcisi;
import java.util.List;
import java.util.ArrayList;


public class AStar {
    private int haritaBoyut;
    private ArrayList<Node> closed = new ArrayList<Node>();
    private SortedList open = new SortedList();
    private int[][] harita;
    public AStar(int haritaBoyut, int[][] harita) {
        this.haritaBoyut = haritaBoyut;
        this.harita = harita;
    }

    public Node getFirstInOpen() {
    return open.getFirst();
    
}
public Path tracePath(Node node) {
    Path path = new Path();
    while(node.parent != null) {
        path.prependWayPoint(node);
        node = node.parent;
    }
    return path;
}
public boolean inOpenList(Node node) {
    return open.contains(node);
}

public boolean isValidLocation(int startX, int startY, int x, int y) {
    boolean invalid = (x < 0) || (y < 0) || (x >= haritaBoyut) || (y >= haritaBoyut);
    if ((!invalid) && ((startX != x) || (startY != y))) {
        invalid = (harita[y][x] == -1);
    }
    return !invalid;
}
public Node findNodeInList(SortedList list, int x, int y) {
    for (int i = 0; i < list.size(); i++) {
        Node node = list.get(i);
        if ((node.x == x) && (node.y == y)) {
            return node;
        }
    }
    return null;
}

public float getHeuristicCost(int x, int y, int goalX, int goalY) {
    float dx = goalX - x;
    float dy = goalY - y;
    return (float) (Math.sqrt((dx*dx)+(dy*dy)));
}
private int maxDepth = 0;


    

    public Path findPath(int startX, int startY, int goalX, int goalY) {
        open.clear();
        closed.clear();

        open.add(new Node(startX, startY));

        while (open.size() != 0) {
            Node current = getFirstInOpen();
            if (current.x == goalX && current.y == goalY) {
                return tracePath(current);
            }

            open.remove(current);
            closed.add(current);

            for (int x=-1;x<2;x++) {
                for (int y=-1;y<2;y++) {
                    if ((x == 0) && (y == 0)) {
                        continue;
                    }

                    int xp = x + current.x;
                    int yp = y + current.y;

                    if (isValidLocation(startX,startY,xp,yp)) {
                        float nextStepCost = current.cost + 1;
                        Node neighbour = findNodeInList(open,xp,yp);
                        if (neighbour == null) {
                            neighbour = findNodeInList(closed,xp,yp);
                        }

                        if (neighbour == null || nextStepCost < neighbour.cost) {
                            if (neighbour == null) {
                                neighbour = new Node(xp, yp);
                            }
                            neighbour.cost = nextStepCost;
                            neighbour.heuristic = getHeuristicCost(xp, yp, goalX, goalY);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                            if (!inOpenList(neighbour)) {
                                open.add(neighbour);
                            }
                        }
                    }
                }
            }
        }

        return null;
    }
}
*/