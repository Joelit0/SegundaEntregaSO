/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.io.File;

/**
 *
 * @author Juan
 */
public class Fichero extends Archivo{
    
    private String extension;

    public Fichero(String nombre, String extension){
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = new File("./src/Contenidos/"+nombre+extension+".txt");
        this.esDirectorio = false;
        permisosUsuario = new Permisos(false, false, false);
        permisosGrupo = new Permisos(false, false, false);
    }
    
    public String getExtension(){
        return extension;
    } 
    
    @Override
    public void Abrir(){
        
    }
}
