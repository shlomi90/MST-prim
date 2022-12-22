// Alon segev id:315870543
// Shlomi Karavani id:203576582


package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Prim {

    // undirected graph
    public static void addEdge(Graph graph, int src, int dest, int weight) {
        Edge e1 = new Edge(dest, src, weight);
        Edge e2 = new Edge(src, dest, weight);
        graph.adj[src].addLast(e1);
        graph.adj[dest].addLast(e2);

    }

    public static void addEdgeToMST(Graph graph, int src, int dst, int weight) {
        Edge e = new Edge(dst, src, weight);
        graph.adj[src].addLast(e);

    }


    static Graph prim_mst(Graph graph) {
        Graph mst = new Graph(graph.V); // the graph that we will return after mst

        Boolean[] mstSet = new Boolean[graph.V]; // inside mst or not
        Node[] nodes = new Node[graph.V];
        int[] parent = new int[graph.V]; // parent array

        for (int i = 0; i < graph.V; i++) {
            nodes[i] = new Node();
        }

        // initialize all properties to start with Prim's algorithm
        for (int i = 0; i < graph.V; i++) {
            mstSet[i] = false;
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].vertex = i;
            parent[i] = -1;
        }
        // initialize the source vertex
        mstSet[1] = true;
        nodes[1].distance = 0;


        // queue implements by tree set that smallest node is first
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o1.distance - o2.distance);
            }
        });

        queue.addAll(Arrays.asList(nodes).subList(0, graph.V));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            mstSet[node.vertex] = true;
            for (Edge curr : graph.adj[node.vertex]) // run through neighbors of node
            {
                if (!mstSet[curr.destination]) {
                    if (curr.weight < nodes[curr.destination].distance) {

                        queue.remove(nodes[curr.destination]);
                        nodes[curr.destination].distance = curr.weight; //change the key of vertex that was infinite to the edge weight
                        queue.add(nodes[curr.destination]);
                        parent[curr.destination] = node.vertex; // updates parent to be poll node

                    }
                }
            }
        }
        // creation MST graph

        for (int i = 2; i < graph.V; i++) {

            Prim.addEdge(mst, parent[i], i, graph.srcEdge(parent[i], i).weight);
            mst.srcEdge(parent[i], i).isPartOfMSTEdge = true;
        }

        return mst;
    }



    public static void main(String[] args){


        int V = 21;
        Graph graph2 = new Graph(V);

        addEdge(graph2,1,2,2);
        addEdge(graph2,1, 6,10);
        addEdge(graph2,1,7,5);

        addEdge(graph2,2 ,6 ,7);
        addEdge(graph2,2 ,3 ,9);
        addEdge(graph2,2 ,8,3);
        addEdge(graph2, 2,7,20);

        addEdge(graph2, 3,4,5);
        addEdge(graph2, 3,8,1);
        addEdge(graph2, 3,7,1);
        addEdge(graph2, 3,9,3);

        addEdge(graph2,4 ,5,7);
        addEdge(graph2, 4,8,6);
        addEdge(graph2, 4,9,3);
        addEdge(graph2, 4,10,4);


        addEdge(graph2, 5,9,1 );
        addEdge(graph2, 5,10,2);

        addEdge(graph2, 6,7,15);
        addEdge(graph2, 6,11,13);
        addEdge(graph2, 6,12,10);

        addEdge(graph2, 7,8,3);
        addEdge(graph2, 7,12,20);
        addEdge(graph2, 7,13,5);

        addEdge(graph2, 8,12,7);
        addEdge(graph2, 8,13,10);
        addEdge(graph2, 8,9,10);
        addEdge(graph2,8 ,14,5);

        addEdge(graph2, 9,13,11);
        addEdge(graph2, 9,14,1);
        addEdge(graph2, 9,15,3);
        addEdge(graph2, 9,10,5);

        addEdge(graph2,10 ,14,17);
        addEdge(graph2, 10,15,9);

        addEdge(graph2, 11,12,12);
        addEdge(graph2, 11,16,2);
        addEdge(graph2, 11,17,1);

        addEdge(graph2, 12,17,10);
        addEdge(graph2, 12,13,13);
        addEdge(graph2, 12,18,7);

        addEdge(graph2, 13,18,9);
        addEdge(graph2, 13,14,5);
        addEdge(graph2,13,19,8);

        addEdge(graph2,14,15,11);
        addEdge(graph2,14,19,3);
        addEdge(graph2,14,20,5);

        addEdge(graph2,15,20,6);

        addEdge(graph2,16,17,3);

        addEdge(graph2,17,18,5);

        addEdge(graph2,18,19,7);

        addEdge(graph2,19,20,10);

        System.out.println("Original tree before we do MST:");
        graph2.printGraph();

        System.out.println();
        System.out.println("New tree after we perform MST:");
        Graph MST = prim_mst(graph2);
        MST.printGraph();

        System.out.println();
        System.out.println("Add Edge: "+"17-->16:"+"weight: 3");
        System.out.println("Updated MST:");
        Targil2Class t2 = new Targil2Class();
        t2.Targil2(MST,new Edge(17,16,3));
        MST.printGraph();

        System.out.println();
        System.out.println("Add Edge: " + "17-->16: "+ " weight: 1");
        System.out.println("Updated MST with change : 17-->16 weight:2  Deleted!");

        t2.Targil2(MST, new Edge(17,16,1));
        MST.printGraph();

    }
}

