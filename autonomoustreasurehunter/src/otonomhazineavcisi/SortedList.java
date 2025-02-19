
package otonomhazineavcisi;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class SortedList {
    private ArrayList<Node> list = new ArrayList<Node>();

    public Node getFirst() {
        return list.get(0);
    }
    public SortedList() {
    this.list = new ArrayList<Node>();
}

public void setList(ArrayList<Node> list) {
    this.list = list;
}

     public Node get(int index) {
        return list.get(index);
    }
    public Iterator<Node> iterator() {
        return list.iterator();
    }

    public void clear() {
        list.clear();
    }

    public void add(Node node) {
        
        list.add(node);
        list.sort((Node node1, Node node2) -> {
            if (node1.heuristic < node2.heuristic)
                return -1;
            else if (node1.heuristic > node2.heuristic)
                return 1;
            else
                return 0;
        });
    }

    public void remove(Node node) {
        list.remove(node);
    }
    

    public int size() {
        return list.size();
    }

    public boolean contains(Node node) {
        return list.contains(node);
    }
}
