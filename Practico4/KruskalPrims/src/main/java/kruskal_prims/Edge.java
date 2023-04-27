/**
 * Clase Edge para implementar grafos 
 */
package grafos;

public class Edge {
     
    private Vertex vertexTo;    // nodo destino
    private float weight;       // peso del arco
 
    /**
    * Constructor basico
    */
    public Edge(Vertex vertexTo){
        this.vertexTo = vertexTo;
        this.weight = 0;
    }
 
    /**
    * Otro constructor
    */
    public Edge(Vertex vertexTo, float weight){
        this.vertexTo = vertexTo;
        this.weight = weight;
    }
 
    /**
    * Se setea el nodo destino
    */
    public void setDest(Vertex vertexTo){
        this.vertexTo = vertexTo;
    }
 
    /**
    * Se obtiene el nodo destino
    */
    public Vertex getDest(){
        return vertexTo;
    }
 
    /**
    * Se setea el peso del arco
    */
    public void setWeight(float weight){
        this.weight = weight;
    }
 
    /**
    * Se obtiene el peso del arco
    */
    public float getWeight(){
        return weight;
    }
}