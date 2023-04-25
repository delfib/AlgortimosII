package citas;

import java.time.*;

/**
 * Clase que representa el tipo Cita
 */
public class Cita {
    private String descripcion;
    private LocalDate fecha;
    private LocalTime horarioInit;
    private LocalTime horarioFin;  // del tipo 1020 = 18:20
    private int prioridad;  // prioridad del 1 al 10

    // Constructor
    public Cita(String descripcion, LocalDate fecha, LocalTime horarioInit, LocalTime horarioFin, int prioridad){
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horarioInit = horarioInit;
        this.horarioFin = horarioFin;
        this.prioridad = prioridad;
    }

    // Getter para la fecha
    public LocalDate getFecha(){
        return fecha;
    }

    // Getter para el horario de inicio
    public LocalTime getHorarioInit(){
        return horarioInit;
    }

    // Getter para el horario de finalizacion
    public LocalTime getHorarioFin(){
        return horarioFin;
    }

    // Getter para la prioridad
    public int getPrioridad(){
        return prioridad;
    }

    /**
     * Metodo toString para una cita
     */
    @Override
    public String toString(){
        String result = "[Descripcion: " + descripcion;
        result += " - Fecha: " + fecha + " - Horario Inicio: " + horarioInit + " - Horario Fin: " + horarioFin;
        result += " - Prioridad: " + prioridad + "]";
        return result;
    }
}
