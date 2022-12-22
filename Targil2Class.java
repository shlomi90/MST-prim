// Alon segev id:315870543
// Shlomi Karavani id:203576582


package test;

import java.util.LinkedList;

public class Targil2Class {
    LinkedList<Integer> finish = new LinkedList<>();

    public void Targil2(Graph g, Edge e) {

        LinkedList<Integer> maslul = new LinkedList<>();
        maslul.add(e.source);
        findCycle(g, maslul, e.destination);

        LinkedList<Edge> edgeMaslul = new LinkedList<>();

        for (int i = 0; i < finish.size() - 1; i++) {
            edgeMaslul.add(g.srcEdge(finish.get(i), finish.get(i + 1)));
            edgeMaslul.add(g.srcEdge(finish.get(i + 1), finish.get(i)));
        }

        int weightMax = Integer.MIN_VALUE;
        int indexWeightMax = -1;

        for (int i = 0; i < edgeMaslul.size(); i++) {
            if (weightMax < edgeMaslul.get(i).weight) {
                weightMax = edgeMaslul.get(i).weight;
                indexWeightMax = i;
            }
        }

        if (e.weight < weightMax) {
            Edge firstEdgeDelete = g.srcEdge(finish.get(indexWeightMax), finish.get(indexWeightMax + 1));
            Edge secondEdgeDelete = g.srcEdge(finish.get(indexWeightMax + 1), finish.get(indexWeightMax));

            g.adj[finish.get(indexWeightMax)].remove(firstEdgeDelete);
            g.adj[finish.get(indexWeightMax + 1)].remove(secondEdgeDelete);

            Prim.addEdgeToMST(g, e.source, e.destination, e.weight); // add both src to dest and dst to src
            Prim.addEdgeToMST(g, e.destination, e.source, e.weight);

            g.srcEdge(e.source, e.destination).isPartOfMSTEdge = true;
            g.srcEdge(e.destination, e.source).isPartOfMSTEdge = true;

        }


    }

    public void findCycle(Graph graph, LinkedList<Integer> visited, int destination){

        LinkedList<Integer> vertexes = new LinkedList<>();
        for(Edge e: graph.adj[visited.getLast()]){
            vertexes.add(e.destination);
        }

        for(Integer vertex: vertexes){
            if(visited.contains(vertex))
                continue;
            if(vertex.equals(destination)){
                visited.add(vertex);
                setMaslul(visited);
                visited.removeLast();
                break;
            }
        }
        for(Integer vertex : vertexes){
            if(visited.contains(vertex) || vertex.equals(destination))
                continue;
            visited.addLast(vertex);
            findCycle(graph,visited,destination);
            visited.removeLast();
        }
    }

    void setMaslul(LinkedList<Integer> visited){
        if(this.finish.isEmpty())
            this.finish = new LinkedList<>(visited);
    }
}

