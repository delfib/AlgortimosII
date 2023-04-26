package graph.coloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase implementa el coloreo de grafos utilizando la tecnica GREEDY. EJERCICIO 6 PRACTICO 4
 * Para hacer el coloreo de grafos con la tecnica Greedy, ordenamos los nodos del grafo segun su grado,
 * de manera descendente. El grado de un nodo es el numero de aristas conectadas a ese nodo.
 * Se ordena de esta manera porque tiene mayor posibilidad de ser la solucion la correcta.
 * Luego tenemos un recorrido del grafo, donde aplicamos el algoritmo de colorear este grafo, recorriendolo
 * en base a ese nuevo orden.
 * Esta solucion no es optima, hay casos en los que la solucion no es exacta, pero resuelve el problema 
 * en un tiempo polinomial.
 * 
 */
public class GraphColoring {
    public static void main(String[] args){
        // Caso en el que devuelve la respuesta correcta (minimo coloreo)
        int[][] graph = {{0,1,0,1,0}, {1,0,0,0,1}, {0,0,0,1,0}, {1,0,0,0,1}, {0,1,0,1,0}};  // minimo = 2 colores
        System.out.println(graphColoring(graph));

        // Caso en el que no devuelve la respuesta correcta
        int[][] graph2 = {
            {0, 1, 1, 1, 1},
            {1, 0, 1, 0, 0},
            {1, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 1, 0}
        };  
        System.out.println(graphColoring(graph2)); // respuesta correcta: 2 colores, pero retorna tres
    }

    /**
     * Metodo que resuelve el problema del coloreo de grafos con la tecnica GREEDY
     * @param graph
     * @result lista de tuplas, donde el primer elemento es el nodo y el segundo el color (nodo, color)
     */
    public static List<TupleGraph> graphColoring(int[][] graph){
        List<Integer> recorrido = new LinkedList<Integer>();    // lista con todos los nodos del grafo
        List<TupleGraph> result = new LinkedList<TupleGraph>(); // lista de tuplas donde (nodo, color)
        
        // Lleno la lista recorrido y result con todos los nodos del grafo
        for (int i = 0; i < graph.length; i++){
            recorrido.add(i);
            TupleGraph nodo = new TupleGraph(i, null);
            result.add(nodo);   // en result, cada indice se corresponde con su nodo, indice 0 es el nodo 0
        }

        sortByDegree(recorrido, graph);

        int colorActual = 1;    // coloreamos con enteros, el primer "color" es 1
        List<Integer> nodosColoreados = new ArrayList<Integer>();  // lista de nodos que ya estan coloreados con un color particular
        
        int i = 0;
        result.get(0).setSecond(colorActual);   // coloreamos el primer nodo con 1 en el resultado

        nodosColoreados.add(i); // agregamos el primer nodo coloreado con el primer color
        while (containsNull(result)){
            for (int j = 1; j < recorrido.size(); j++){  // por cada vertice 
                // si ese nodo no esta coloreado y no es adjacente a ningun nodo coloreado con el mismo color
                if (result.get(recorrido.get(j)).getSecond() == null && notAdjacent(nodosColoreados, recorrido.get(j), graph)){ 
                    result.get(recorrido.get(j)).setSecond(colorActual); // lo coloreamos
                    nodosColoreados.add(recorrido.get(j));   // lo agregamos al conjunto de nodos coloreados
                }
            }
            colorActual++;
            nodosColoreados.removeAll(nodosColoreados); // reseteo el nodosColoreados para agregar nodos coloreados con el siguiente color.   
        }
        
        return result;
    }


    /**
     * Metodo privado que dado una lista de nodos de un grafo no dirigido y ese grafo, 
     * devuelve la lista de nodos ordenada de manera descendente segun los grados de cada nodo.
     * Grado de un nodo: cantidad de aristas que estan conectadas con ese nodo.
     */
    private static void sortByDegree(List<Integer> vertices, int[][] graph){
        final int[] degrees = new int[graph.length];

        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j < graph[i].length; j++){
                if (graph[i][j] == 1){
                    degrees[i]++;
                }
            }
        }

        // Creamos un objeto Comparator para comparar los nodos según sus grados
        Comparator<Integer> comparator = new Comparator<Integer>() {
            // metodo compare propio de ese comparador
            public int compare(Integer nodo1, Integer nodo2) {
                return Integer.compare(degrees[nodo2], degrees[nodo1]);
            }
        };

        // Ordenar la lista de vértices en base al comparador
        Collections.sort(vertices, comparator);
    }   


    /**
     * Metodo privado que dado un arreglo, devuelve True sii al menos uno de sus elementos es null
     */
    private static boolean containsNull(List<TupleGraph> list){
        boolean result = false;
        int i = 0;
        while (i < list.size() && !result){
            if (list.get(i).getSecond() == null){
                result = true;
            }
            i++;
        }
        return result;
    }


     /**
     * Metodo privado que dado un conjunto de nodos coloreados con un color especifico, un nodo y un grafo,
     * retorna True sii no es adjacente a ningun nodo pintado con el color especifico
     */
    private static boolean notAdjacent(List<Integer> nodosColoreados, Integer nodo, int[][] graph){
        if (nodosColoreados.isEmpty()) return true;
        boolean noEsAdjacent = true;
        int i = 0;
        while (i < nodosColoreados.size() && noEsAdjacent){
            if (graph[nodo][nodosColoreados.get(i)] == 1){
                noEsAdjacent = false;
            }
            i++;
        }
        return noEsAdjacent;
    }
}
