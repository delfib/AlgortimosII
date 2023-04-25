/**
 * Esta clase resuelve el EJERCICIO 3 de la PRACTICA 4.
 * Dado un conjunto de citas correspondientes a una fecha particular,
 * encuentra un subconjunto de tareas compatibles de prioridad maximal (o una aproximacion a esto)
 */

package citas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


public class CitasMaximal {
    public static void main(String[] args){

        LocalDate date = LocalDate.of(2023,5,20); // 2023/05/20
        LocalTime hI1 = LocalTime.of(10,30);  // 10:30
        LocalTime hF1 = LocalTime.of(11,30);  // 11:30
        LocalTime hF2 = LocalTime.of(12,30);  // 12:30
        LocalTime hI3 = LocalTime.of(9,00);  // 9:00
        LocalTime hF3 = LocalTime.of(11,00);  // 11:00

      
        Cita c1 = new Cita("medico", date, hI1, hF1, 6);    // 10:30 - 11:30
        Cita c2 = new Cita("cliente", date, hF1, hF2, 3);   // 11:30 - 12:30
        Cita c3 = new Cita("jefe", date, hI3, hF3, 2);      // 9:00 - 11:00

        Cita[] citas = new Cita[] {c3,c2,c1};

        List<Cita> result = citasMaximales(citas, date); 
        System.out.println(result);
    }


    /*
     * Metodo que dada una lista de citas que tiene una persona y un dia especifico
     * retorna un subconjunto de tareas compatibles de prioridad maximal
     */
    public static List<Cita> citasMaximales(Cita[] citas, LocalDate date){
        List<Cita> result = new LinkedList<Cita>();
        selectionSort(citas);

        if (citas[0].getFecha() == date){
            result.add(citas[0]);
            
            for(int i = 1; i < citas.length; i++){
                if (citas[i].getFecha() == date){
                    if (!seSolapanResto(citas[i], result)){
                        result.add(citas[i]);
                    }
                }
            }
        }
        else {
            int i = 1;
            while (citas[i].getFecha() != date){
                i++;
            }
            if (i < citas.length) result.add(citas[i]);
            for (int j = i+1; j < citas.length; j++){
                if (citas[j].getFecha() == date){
                    if (!seSolapanResto(citas[j], result)){
                        result.add(citas[j]);
                    }
                }
            }
        }
        return result;
    }


    /*
     * Metodo privado que dada una cita y una lista de citas, retorna true sii se solapa con alguna de ellas
     */
    private static boolean seSolapanResto(Cita c1, List<Cita> result){
        for (int i = 0; i < result.size(); i++){
            if (seSolapan(c1, result.get(i))) return true;
        }
        return false;
    }


    /**
     * Metodo privado que dadas dos citas, determina si estas se solapan en algun momento
     */
    private static boolean seSolapan(Cita c1, Cita c2){
        // c1 empieza durante la c2 (ej: C2 -> 10-12   C1 -> 11-13)
        if (c1.getHorarioInit().compareTo(c2.getHorarioFin()) < 0 && c1.getHorarioFin().compareTo(c2.getHorarioFin()) > 0) return true;

        // c1 termina durante la c2 (ej: C2 -> 10-12   C1 -> 9-11)
        if (c1.getHorarioInit().compareTo(c2.getHorarioInit()) < 0 && c1.getHorarioFin().compareTo(c2.getHorarioFin()) < 0) return true;

        // c1 empieza y termina durante la c2 (ej: C2 -> 10-13    C1 -> 11-12)
        if (c1.getHorarioInit().compareTo(c2.getHorarioInit()) > 0 && c1.getHorarioFin().compareTo(c2.getHorarioFin()) < 0) return true;

        // c2 empieza y termina durante la c1 (ej: C1 -> 10-13    C1 -> 11-12)
        if (c2.getHorarioInit().compareTo(c1.getHorarioInit()) > 0 && c2.getHorarioFin().compareTo(c1.getHorarioFin()) < 0) return true;

        return false;
    }


    /* 
    * Usa SelectionSort para ordenar de manera decreciente un arreglo de citas 
    * en base a la prioridad
    */ 
    private static void selectionSort(Cita[] array) {
    	if (array == null) throw new IllegalArgumentException("array is null, can't sort");
      	//last = indice del ultimo elemento de la parte no ordenada
      	for (int i = 0; i < array.length; i++) {
         	//largest = posicion del elemento mas grande
         	int largest = indexOfLargest(array, array.length, i);
         	swap(array, i, largest);
      	}
   	}

    
    /*
     * Metodo privado utilizado en selectionSort, dado un array devuelve el indice del elemento mas grande
     */
	private static int indexOfLargest(Cita[] array, int n, int i){
	    int largest = i;
	  	while (i < n){
            if (array[i].getPrioridad() > array[largest].getPrioridad()){
                largest = i;
            }
            i++;
        }
	  	return largest;
   	}


    /**
     * Metodo privado utilizado en selectionSort
     */
   	private static void swap(Cita[] array, int i, int j) {
    	Cita temp = array[i];
    	array[i] = array[j];
    	array[j] = temp;
	}
}
