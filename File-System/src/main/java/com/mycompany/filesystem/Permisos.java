/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

/**
 *
 * @author Juan
 */
public class Permisos {
    
    private boolean lectura;
    
    private boolean escritura;
    
    private boolean ejecucion;
       
    public Permisos(boolean lectura, boolean escritura, boolean ejecucion){
        this.lectura = lectura;
        this.escritura = escritura;
        this.ejecucion = ejecucion;
    }

    public boolean getLectura() {
        return lectura;
    }

    public boolean getEscritura() {
        return escritura;
    }

    public boolean getEjecucion() {
        return ejecucion;
    }

    public void setLectura(boolean lectura) {
        this.lectura = lectura;
    }

    public void setEscritura(boolean escritura) {
        this.escritura = escritura;
    }

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
    }
   
}
