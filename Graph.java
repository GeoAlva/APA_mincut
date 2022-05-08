package mincut;

import java.util.*;


class Node{
    String label;

    public Node(String c){
        this.label=c;
    }

    @Override
    public String toString(){
        return label;
    }


}

public class Graph {
    public int node_n;
    public int edge_n;
    public ArrayList<Node> rnd;                     //Array di nodi usato per facilitare la scelta casuale dei nodi per la funzione mincut
    public Map<Node,LinkedList<Node>> graph;

    public Graph(){
        node_n=0;
        edge_n=0;
        rnd=new ArrayList<>();
        graph = new HashMap<>();
    }

    public int edgeNum(){
        return this.edge_n;
    }

    public int nodeNum(){
        System.out.println("node num = "+this.node_n);
        return this.node_n;
    }

    public void addNode(Node e){
        graph.put(e, new LinkedList<>());
        rnd.add(e);
        node_n++;
    }

    public void addEdge(Node a, Node b){
        graph.get(b).add(a);
        graph.get(a).add(b);
        edge_n++;
    }

    public void rmNode(Node e){
        graph.remove(e);
        node_n--;
        for(Node v: graph.keySet()){
            if(graph.get(v).contains(e)){
                graph.get(v).remove(e);
                edge_n--;
                }
        }
        rnd.remove(e);
    }

    public void mergeNodes(Node a, Node b){               //funzione usata per il mincut
        Node st = new Node(a.label.concat(b.label));
        addNode(st);
        for(Node x: graph.get(a)){
            if(x!=b){
                addEdge(st, x);
            }
        }
        for(Node x: graph.get(b)){
            if(x!=b&&!graph.get(st).contains(x)){
                addEdge(st, x);
            }
        }

        rmNode(a);
        rmNode(b);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
 
        for (Node v : graph.keySet()) {
            builder.append(v.toString() + ": ");
            for (Node w : graph.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
 
        return (builder.toString());
    }

}
