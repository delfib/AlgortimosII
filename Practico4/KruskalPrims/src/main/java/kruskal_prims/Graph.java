/**
 * Interfaz para grafos, puede ser implementada mediante matrices o listas de adyacencia
 * En la bibliografia de Algoritmos I hay una implementacion de grafos
 */
package grafos;
import java.util.List;

public interface Graph {
     
    /**
    * Agrega un arco entre dos nodos
    * @param x nombre del nodo origen
    * @param y nombre del nodo destino
    * @param w costo del arco
    */
    public void addEdge(String x, String y, float w);
 
    /**
    * @return el numero de nodos del grafo
    */
    public int numberOfNodes();
 
    /**
    * @return la cantidad de arcos del grafo
    */
    public int numberOfEdges();
 
    /**
    * @param x nombre del primer nodo
    * @param y nombre del segundo nodo
    * @return True ssi hay un arco entre ambos nodos
    */
    public boolean adjacent(String x, String y);
 
    /**
    * @param x el nodo desde donde se empieza el recorrido
    * @return una lista con el recorrido del grafo
    */
    public List<String> DFS(String x);
 
    /**
    * @param x el nodo desde donde se empieza el recorrido
    * @return una lista con el recorrido del grafo
    */
    public List<String> BFS(String x);

    /**
     * @param graph a weighted connected graph
     * @return a set of edges composing a minimum spanning tree of graph
     */
    public List<Edge> Prim(Graph graph);

     /**
     * @param graph a weighted connected graph
     * @return a set of edges composing a minimum spanning tree of graph
     */
    public List<Edge> Kruskal(Graph graph); 
}