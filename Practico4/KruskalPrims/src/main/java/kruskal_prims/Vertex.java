/**
 * Clase Vertex, modela los vertices de un grafo 
 */
package grafos;
import java.util.LinkedList;
import java.util.List;
 
public class Vertex {
     
    private Object info;    // para guardar cualquier tipo de informacion
    private String name;    // nombre del vertice / nodo
    private boolean visited;    // marca para diversos algoritmos de grafos
    private List<Edge> adjList; // lista de adjacentes

    /**
    * Constructor basico
    */
    public Vertex(String name){
        this.name = name;
        this.visited = false;
        this.adjList = new LinkedList<Edge>();
    }
 
    /**
    * Se setea la info del nodo
    */
    public void setInfo(Object info){
        this.info = info;
    }
 
    /**
    * Se obtiene la info del nodo
    */
    public Object getInfo(){
        return info;
    }
 
    /**
    * Se setea el nombre del nodo
    */
    public void setName(String name){
        this.name = name;
    }
 
    /**
    * Se obtiene el nombre del nodo
    */
    public String getName(){
        return name;
    }
 
    /**
    * Se setea el atributo visited del nodo
    */
    public void setVisited(boolean visited){
        this.visited = visited;
    }
 
    /**
    * Se obtiene el atributo visited del nodo
    */
    public boolean getVisited(){
        return visited;
    }
 
    /**
    * Se setea la lista de adyacentes del nodo
    */
    public void setAdjList(List<Edge> adjList){
        this.adjList = adjList;
    }
 
    /**
    * Se obtiene la lista de adyacentes del nodo
    */
    public List<Edge> getAdjList(){
        return adjList;
    }
 }