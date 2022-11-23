/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.util.LinkedList;

/**
 *
 * @author Juan
 */
public class Usuario {
    
    private String nombre;
    
    private boolean permisosRoot;
        
    public Usuario(String nombre){
        this.nombre = nombre;
        if(nombre.equals("root")){
            permisosRoot = true;
        }
        else{
            permisosRoot = false;
        }
    }
    
    public String getNombre(){
        return this.nombre;
    } 
    
    public boolean getPermisos(){
        return permisosRoot;
    }
    
    public void setPermisos(boolean permisos){
        permisosRoot = permisos;
    }
}
