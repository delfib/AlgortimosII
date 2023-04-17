import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.awt.*;

/**
 * Esta clase implementa el problema de Convex-Hull, donde dado un conjunto de puntos
 * del plano, encuentra el poligono convexo mas pequeno que incluye a todos los puntos del conjunto dado
 */
public class ConvexHull {
    public static void main(String[] args){
        List<Point> set = new ArrayList<>();
        
        Point A = new Point(1,1);   // A = (1,1)
        Point B = new Point(2,2);   // B = (2,2)
        Point C = new Point(3,1);   // C = (3,1)
        Point D = new Point(2,3);   // D = (2,3)
        set.add(A);
        set.add(B);
        set.add(C);
        set.add(D);

        System.out.println("Convex-Hull = " + convexHull(set));
    }

    /**
     * Este metodo recibe un conjunto de puntos en el plano de la forma (x,y). 
     * Retorna el poligono convexo mas pequeno que contiene a todos los puntos de este conjunto.
     */
    public static List<Point> convexHull(List<Point> set){
        if (set == null) return null;
        if (set.size() == 1) return set;

        /**
         * voy a guardar aca los datos del poligono convexo. Esta lista tendra la informacion de los puntos bordes que forman el poligono convexo
         * cada lista dentro de esta lista contiene representada 
         */
        List<Point> result = new ArrayList<>(); 

        for (int i = 0; i < set.size(); i++){
            for (int j = 0; j < set.size(); j++){
                if (j != i) {
                    double[] lineEq = new double[3];    // arreglo que representa la ecuacion
                    lineEq[0] = (set.get(j).getY() - set.get(i).getY());  // a = (y2 - y1)
                    lineEq[1] = (set.get(i).getX() - set.get(j).getX());  // b = (x1 - x2)
                    lineEq[2] = ((set.get(i).getX() * set.get(j).getY()) - (set.get(i).getY() * set.get(j).getX()));  // c = x1y2 − y1x2
                    Boolean[] allResultsEq = new Boolean[set.size()];   // voy a guardar aca todos los signos de la evaluacion de ax + by = c para cada punto
                    for (int k = 0; k < set.size(); k++){
                        Double resEq; // resultado de la ecuacion ax + by = c 
                        resEq = (set.get(k).getX() * lineEq[0]) + (set.get(k).getY() * lineEq[1]) - lineEq[2];  // aplico ax + by = c con un punto (x,y)
                        allResultsEq[k] = sign(resEq);
                    }
                    // Nos fijamos que para todos los puntos la ecuacion da el mismo signo
                    int m = 0;
                    boolean sameSign = true;
                    while (m < allResultsEq.length && sameSign){
                        if (allResultsEq[m] != true) sameSign = false;
                        m++;
                    }
                    if (sameSign == true) {
                        if (!result.contains(set.get(i))) result.add(set.get(i));                    
                        if (!result.contains(set.get(j))) result.add(set.get(j));
                    }
                }
            }
        }
        return result;
    }


    /**
     * Metodo privado que dado un entero determina su signo. True ssi es positivo, False sii es negativo.
     */
    private static boolean sign(double n){
        return n >= 0;
    }
}
