package mincut;

import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static int count=0;

    public static int getRandomNumber(int min, int max) {      //funzione usata per ottenere
        Random random = new Random();                          //due indici casuali nel array dei nodi
        return random.nextInt(max - min) + min;
    }

    public static Graph MCmincut(Graph g){
        while(g.node_n>2){
            int x = getRandomNumber(0,g.node_n-1);          //scelta di due indici casuali
            int y = getRandomNumber(0,g.node_n-1);
            
            if(g.graph.get(g.rnd.get(x)).contains(g.rnd.get(y))){
                g.mergeNodes(g.rnd.get(x), g.rnd.get(y));
                count++;
            }
        }
        return g;
    }


    public static void main(String[] args) {
        Double p=0.0;
        int run_n=1;
        ArrayList<Double> p_arr = new ArrayList<>();

        while(run_n<=10000){
            count=0;
            //Creazione del grafo di Fritsch
            var graph = new Graph();

            Node a = new Node("A");
            Node b = new Node("B");
            Node c = new Node("C");
            Node d = new Node("D");
            Node e = new Node("E");
            Node f = new Node("F");
            Node g = new Node("G");
            Node h = new Node("H");
            Node i = new Node("I");

            graph.addNode(a);
            graph.addNode(b);
            graph.addNode(c);
            graph.addNode(d);
            graph.addNode(e);
            graph.addNode(f);
            graph.addNode(g);
            graph.addNode(h);
            graph.addNode(i);

            graph.addEdge(a,b);
            graph.addEdge(a,c);
            graph.addEdge(a,d);
            graph.addEdge(a,h);
            graph.addEdge(a,i);
            graph.addEdge(b,c);
            graph.addEdge(b,e);
            graph.addEdge(b,h);
            graph.addEdge(c,e);
            graph.addEdge(c,d);
            graph.addEdge(c,f);
            graph.addEdge(d,f);
            graph.addEdge(d,i);
            graph.addEdge(e,f);
            graph.addEdge(e,h);
            graph.addEdge(e,g);
            graph.addEdge(f,g);
            graph.addEdge(f,i);
            graph.addEdge(g,h);
            graph.addEdge(g,i);
            graph.addEdge(h,i);

            //Stampa del grafo di Fritsch
            // System.out.println("Grafo : \n"+ graph.toString());
            // graph.edgeNum();
        
            p = 2.0/(graph.nodeNum() * (graph.nodeNum() - 1));
            p_arr.add(run_n-1, p);
            

            graph=MCmincut(graph);
            // System.out.println("Grafo : \n"+ graph.toString());

            System.out.println("Run n = " + run_n);
            graph.edgeNum();
            System.out.println("Numero di tagli : " + count);

            //if(count==4) System.exit(0);

            System.out.println("La probabilità di ottenere il taglio minimo è : "+ String.valueOf(p));
            run_n++;
        }

        //System.out.println(p_arr.toString());



        
    }
}
