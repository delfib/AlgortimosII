import java.util.ArrayList;
import java.util.List;

public class GraphColoring {
    public static void main (String[] args){
        int[][] graph = new int[8][8];

        /*graph[0][0] = 0;
        graph[0][1] = 1;
        graph[0][2] = 0;
        graph[1][0] = 1;
        graph[1][1] = 0;
        graph[1][2] = 1;
        graph[2][0] = 0;
        graph[2][1] = 1;
        graph[2][2] = 0;*/

        graph[0][1] = 1;
        graph[0][2] = 1;
        graph[0][3] = 1;
        graph[0][4] = 1;
        graph[1][0] = 1;
        graph[1][3] = 1;
        
        graph[2][0] = 1;
        graph[2][3] = 1;

        graph[3][0] = 1;
        graph[3][1] = 1;
        graph[3][2] = 1;
        graph[3][7] = 1;

        graph[4][0] = 1;
        graph[4][5] = 1;
        graph[4][6] = 1;

        graph[5][4] = 1;
        graph[5][6] = 1;
        graph[5][7] = 1;

        graph[6][4] = 1;
        graph[6][5] = 1;
        graph[6][7] = 1;

        graph[7][3] = 1;
        graph[7][6] = 1;
        graph[7][5] = 1;

        Integer[] result = new Integer[8];
        result = graphColoring(graph);

        for (int i = 0; i < result.length; i++){
            System.out.println("Nodo " + i + " = " + result[i]);
        }
    }

    public static Integer[] graphColoring(int[][] graph){
        List<Integer> vertices = new ArrayList<Integer>();  // lista de todos los vertices del grafo
        
        // Lleno a la lista vertices con todos los vertices del grafo
        for(int i = 0; i < graph.length; i++){
            vertices.add(i);
        }

        List<List<Integer>> listaCaminos = new ArrayList<>();    // lista de todos los caminos posibles para recorrer el grafo
        listaCaminos = permutations(vertices);

        List<Integer> caminoActual = new ArrayList<>(); // camino actual de listaCaminos con el que se esta trabajando

        int colorActual;    // coloreamos con enteros, el primer "color" es 1

        List<Integer> nodosColoreados = new ArrayList<Integer>();  // lista de nodos que ya estan coloreados con un color en especial

        Integer[] coloreoMinimo = new Integer[vertices.size()]; // resultado

        // Primer permutacion / recorrido del grafo
        int i = 0;
        Integer[] coloringActual = new Integer[vertices.size()];
        colorActual = 1; 
        caminoActual = listaCaminos.get(i); // camino actual i de listaCaminos
        coloringActual[i] = colorActual; // coloreamos el primer nodo del camino con el primer color
        
        nodosColoreados.add(i); // Agregamos el primer nodo coloreado con el primer color
        while (containsNull(coloringActual)){
            for (int j = 1; j < caminoActual.size(); j++){  // por cada vertice del camino considereado
                // si ese nodo no esta coloreado y no es adjacente a ningun nodo coloreado con el mismo color
                if (coloringActual[caminoActual.get(j)] == null && notAdjacent(nodosColoreados, caminoActual.get(j), graph)){ 
                    coloringActual[caminoActual.get(j)] = colorActual; // lo coloreamos
                    nodosColoreados.add(caminoActual.get(j));   // lo agregamos al conjunto de nodos coloreados
                }
            }
            colorActual++;
            nodosColoreados.removeAll(nodosColoreados); // reseteo el nodosColoreados para agregar nodos coloreados con el siguiente color.   
        }
        for (int j = 0; j < coloringActual.length; j++){
            coloreoMinimo[j] = coloringActual[j];   // seteamos el coloreMinimo con el resultado de la primera permutacion
        }

        // El resto de permutaciones
        for (int k = 1; k < listaCaminos.size(); k++){
            i = 0;
            Integer[] coloringActual2 = new Integer[vertices.size()];
            colorActual = 1; 
            caminoActual = listaCaminos.get(k); // camino actual k de listaCaminos
            coloringActual2[caminoActual.get(i)] = colorActual; // coloreamos el primer nodo del camino con el primer color
            
            nodosColoreados.add(caminoActual.get(i)); // Agregamos el primer nodo coloreado con el primer color
            while (containsNull(coloringActual2)){
                for (int j = 1; j < caminoActual.size(); j++){  // por cada vertice del camino considereado
                    // si ese nodo no esta coloreado y no es adjacente a ningun nodo coloreado con el mismo color
                    if (coloringActual2[caminoActual.get(j)] == null && notAdjacent(nodosColoreados, caminoActual.get(j), graph)){ 
                        coloringActual2[caminoActual.get(j)] = colorActual; // lo coloreamos
                        nodosColoreados.add(caminoActual.get(j));   // lo agregamos al conjunto de nodos coloreados
                    }
                }
                colorActual++;
                nodosColoreados.removeAll(nodosColoreados); // reseteo el nodosColoreados para agregar nodos coloreados con el siguiente color.   
            }
            if (getMaxColor(coloringActual2) < getMaxColor(coloreoMinimo)) {
                for (int j = 0; j < coloringActual.length; j++){
                    coloreoMinimo[j] = coloringActual[j];   // seteamos el coloreMinimo con el nuevo minimo
                }
            }
        }
        return coloreoMinimo;
    }

    /**
     * Metodo privado que dado un arreglo, devuelve True sii al menos uno de sus elementos es null
     */
    private static boolean containsNull(Integer[] coloringActual){
        boolean result = false;
        int i = 0;
        while (i < coloringActual.length && !result){
            if (coloringActual[i] == null){
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


    /**
     * Metodo que dado un arreglo de Integer retorna el maximo numero de ese arreglo
     */
    private static int getMaxColor(Integer[] array){
        if (array == null) throw new IllegalArgumentException("El arreglo es nulo");
        int max = array[0];
        for (int i = 1; i < array.length; i++){
            if (max < array[i]) max = array[i];
        }
        return max;
    }


    /**
     * Metodo privado que dada una lista de enteros, calcula todas sus permutaciones
     */
    private static List<List<Integer>> permutations(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();   // Lista donde se alojaran todas las permutaciones
        backtrack(result, new ArrayList<>(), nums);     
        return result;
    }
    
    /**
     * Metodo privado que realiza el backtracking para calcular las permutacioens
     * @param result lista de listas donde se guardaran todas las permutaciones
     * @param tempList lista temporal para ir calculando las permutaciones
     * @param nums lista original
     */
    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, List<Integer> nums){
        if (tempList.size() == nums.size()){    // Tenemos una nueva permutacion 
            result.add(new ArrayList<>(tempList));  // La agregamos a result
        } 
        else{
            for(int i = 0; i < nums.size(); i++){ 
                if (!(tempList.contains(nums.get(i)))) {
                    tempList.add(nums.get(i));  // nums[i] no existe en tempList, lo agregamos
                    backtrack(result, tempList, nums);
                    // Elimina el ultimo elmento de tempList
                    tempList.remove(tempList.size() - 1);
                } 
            }
        }
    } 
}