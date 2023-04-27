/**
 * Esta clase implementa Grafos con listas de adyacencia, 
 * para esto utiliza las clases Vertex y Edge.
 */
package grafos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class GraphAdj implements Graph {
      
    private String name;    // nombre del grafo
    private Map<String, Vertex> vertices = new HashMap<String, Vertex>();   // un mapping con todos los vertices del grafo
    private int numberNodes;  // numero de vertices/nodos del grafo
    private int numberEdges;  // numero de arcos del grafo
  
    /**
    * Constructor basico
    */
    public GrafoAdj(String name){
        this.name = name;
    }
  
    /**
    * Otro constructor mas completo
    */
    public GrafoAdj(String name, Map<String, Vertex> vertices, int numberNodes, int numberEdges){
        this.name = name;
        this.vertices = vertices;
        this.numberNodes = numberNodes;
        this.numberEdges = numberEdges;
    }
  
    /**
    * Inserta un nuevo vertice al grafo
    */
    public void insertVertex(Vertex vertex){
        vertices.put(vertex.getName(), vertex);
        numberNodes++;
        int cantEdges = vertex.getAdjList().size(); // cantidad de arcos que salen de vertex
        numberEdges = numberEdges + cantEdges;
    }
  
    /**
    * Devuelve el vertice asociado con un nombre, si no existe, crea uno nuevo
    */
    public Vertex getVertex(String name){
        if (vertices.get(name) == null) {
            Vertex newVertex = new Vertex(name);
            vertices.put(name, newVertex);
        } 
        return vertices.get(name);
    }
  
    public void addEdge(String x, String y, float w){
        Vertex vx = getVertex(x);
        Vertex vy = getVertex(y);
 
        Edge edge = new Edge(vy, w);
  
        vx.getAdjList().add(edge);
        numberEdges++;
    }
  
    /**
    * Devuelve el tamano del nodo |V|+|E|
    */
    public int graphSize(){
        return numberNodes + numberEdges;
    }
  
    public int numberOfNodes(){
        return numberNodes;
    }
  
    public int numberOfEdges(){
        return numberEdges;
    }
     
    /** 
    * Dos nodos son adyacentes si hay un arco entre ellos, este arco puede ser no dirigido o
    * puede estar dirigido de x a y o viceversa.
    */
    public boolean adjacent(String x, String y){
        if (vertices.get(x) == null || vertices.get(y) == null){    // alguno o ninguno de los vertices pertenece al grafo
            return false;
        }
  
        Vertex vx = getVertex(x);
        Vertex vy = getVertex(y);
  
        // check if vx has an Edge with vertexTo == vy
        List<Edge> listvx = vx.getAdjList();
        for (int i = 0; i < listvx.size(); i++){
            if (listvx.get(i).getDest().equals(vy)) return true;
        }
  
        // check if vy has an Edge with vertexTo == vx
        List<Edge> listvy = vy.getAdjList();
        for (int i = 0; i < listvy.size(); i++){
            if (listvy.get(i).getDest().equals(vx)) return true;
        }
  
        return false;
    }
  
    public List<String> DFS(String x){
        throw new UnsupportedOperationException("falta implementar");
    }
  
    public List<String> BFS(String x){
        throw new UnsupportedOperationException("falta implementar");
    }
}