package citas;

import java.time.*;

/**
 * Clase que representa el tipo Cita
 */
public class Cita {
    private String descripcion;
    private LocalDate fecha;
    private LocalTime horarioInit;
    private LocalTime horarioFin;  // del tipo 1820 = 18:20
    private int prioridad;  // prioridad del 1 al 10

    // Constructor
    public Cita(String descripcion, LocalDate fecha, LocalTime horarioInit, LocalTime horarioFin, int prioridad){
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horarioInit = horarioInit;
        this.horarioFin = horarioFin;
        this.prioridad = prioridad;
    }

    // A Getter method for fecha
    public LocalDate getFecha(){
        return fecha;
    }

    // A Getter method for horarioInit
    public LocalTime getHorarioInit(){
        return horarioInit;
    }

    // A Getter method for horarioFin
    public LocalTime getHorarioFin(){
        return horarioFin;
    }

    // A Getter method for prioridad
    public int getPrioridad(){
        return prioridad;
    }

    /**
     * ToString method for a date
     */
    @Override
    public String toString(){
        String result = "[Descripcion: " + descripcion;
        result += " - Fecha: " + fecha + " - Horario Inicio: " + horarioInit + " - Horario Fin: " + horarioFin;
        result += " - Prioridad: " + prioridad + "]";
        return result;
    }
}
