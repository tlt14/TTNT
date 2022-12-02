import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class AStar{
    public static void main(String[] args){
        Node n1 = new Node("(0,0)",4);
        Node n2 = new Node("(0,1)",3);
        Node n3 = new Node("(1,0)",3);
        Node n4 = new Node("(0,2)",2);
        Node n5 = new Node("(1,2)",1);
        Node n6 = new Node("(1,1)",2);
        Node n7 = new Node("(2,0)",2);
        Node n8 = new Node("(2,1)",1);
        Node n9 = new Node("(2,2)",0);
        n1.adjacencies = new Edge[]{
                new Edge(n2,2),
                new Edge(n3,5)
        };
        n2.adjacencies = new Edge[]{
                new Edge(n1,2),
                new Edge(n4,2)
        };
        n3.adjacencies = new Edge[]{
                new Edge(n1, 5),
                new Edge(n6,3),
                new Edge(n7,1)
        };
        n4.adjacencies = new Edge[]{
                new Edge(n2,2),
                new Edge(n5,1)
        };
        n5.adjacencies = new Edge[]{
                new Edge(n4,1),
                new Edge(n6,1)
        };
        n6.adjacencies = new Edge[]{
                new Edge(n5,1),
                new Edge(n3,3),
                new Edge(n8,3)
        };
        n7.adjacencies = new Edge[]{
                new Edge(n3,1)
        };
        n8.adjacencies= new Edge[]{
                new Edge(n6,3),
                new Edge(n9,1)
        };
        n9.adjacencies= new Edge[]{
                new Edge(n8,1)
        };

        AstarSearch(n1, n9);

        List<Node> path = printPath(n9);

        System.out.println("Path: " + path);


    }

    public static List<Node> printPath(Node target){
        List<Node> path = new ArrayList<Node>();

        for(Node node = target; node!=null; node = node.parent){
            path.add(node);
        }

        Collections.reverse(path);

        return path;
    }

    public static void AstarSearch(Node source, Node goal){

        Set<Node> explored = new HashSet<Node>();

        PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
                new Comparator<Node>(){
                    public int compare(Node i, Node j){
                        if(i.f_scores > j.f_scores){
                            return 1;
                        }

                        else if (i.f_scores < j.f_scores){
                            return -1;
                        }

                        else{
                            return 0;
                        }
                    }

                }
        );

        source.g_scores = 0;

        queue.add(source);

        boolean found = false;

        while((!queue.isEmpty())&&(!found)){

            Node current = queue.poll();

            explored.add(current);

            if(current.value.equals(goal.value)){
                found = true;
            }

            for(Edge e : current.adjacencies){
                Node child = e.target;
                double cost = e.cost;
                double temp_g_scores = current.g_scores + cost;
                double temp_f_scores = temp_g_scores + child.h_scores;

                if((explored.contains(child)) &&
                        (temp_f_scores >= child.f_scores)){
                    continue;
                }
                else if((!queue.contains(child)) ||
                        (temp_f_scores < child.f_scores)){

                    child.parent = current;
                    child.g_scores = temp_g_scores;
                    child.f_scores = temp_f_scores;

                    if(queue.contains(child)){
                        queue.remove(child);
                    }

                    queue.add(child);

                }

            }

        }

    }

}

class Node{

    public final String value;
    public double g_scores;
    public final double h_scores;
    public double f_scores = 0;
    public Edge[] adjacencies;
    public Node parent;

    public Node(String val, double hVal){
        value = val;
        h_scores = hVal;
    }

    public String toString(){
        return value;
    }

}

class Edge{
    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal){
        target = targetNode;
        cost = costVal;
    }
}