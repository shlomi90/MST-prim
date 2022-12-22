// Alon segev id:315870543
// Shlomi Karavani id:203576582



package test;

import java.util.LinkedList;

public class Graph {
    int V; // num of vertices
    LinkedList<Edge>[] adj; // adjacency list of nodes
    Graph(int edge){
        V = edge;
        adj=new LinkedList[V];
        for(int i=0; i< V; i++){
            adj[i] = new LinkedList<>(); // vertex and his neighbors
        }
    }

    Graph(Graph graph){
        this.V = graph.V;
        adj = new LinkedList[this.V];
        for(int i=0; i< V; i++){
            adj[i] = new LinkedList<>(graph.adj[i]);
        }
    }

    public Edge srcEdge(int src, int dst){ // look for edge by it source vertex
        for(int i=0; i<V; i++)
        {
            for(int j=0; j<adj[i].size(); j++)
            {
                if(adj[i].get(j).destination == dst && src == i)
                    return adj[i].get(j); // edge with src and dst provided was found!
            }
        }
        return null; // not found
    }

    public void printGraph()
    {
        for (int i=0; i< this.adj.length; i++)
        {
            for(int j=0; j<this.adj[i].size(); j++)
            {
                System.out.println(i+ "<->"+ this.adj[i].get(j).destination + "-"+"weight: "+ this.adj[i].get(j).weight );
            }
        }
    }
}
