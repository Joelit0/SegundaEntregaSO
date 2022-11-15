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
public class Directorio extends Archivo{
        
    private LinkedList<Fichero> ficheros;
    
    private LinkedList<Directorio> directorios;

    
    public Directorio(String nombre){
        esDirectorio = true;
        ficheros = new LinkedList<Fichero>();
        directorios = new LinkedList<Directorio>();
        this.nombre = nombre;
        permisosUsuario = new Permisos(false, false, false);
        permisosGrupo = new Permisos(false, false, false);
    }
    
    public void MostrarArchivos(){
        for(Directorio directorio: directorios){        
            System.out.println(directorio.nombre);         
        }
        for(Fichero fichero: ficheros){
            System.out.println(fichero.nombre+"."+fichero.getExtension());
        }
    } 
    
    public void NuevaCarpeta(String nombre) throws Exception{
        boolean existe = false;
        for(Directorio directorio: directorios){
            if(directorio.nombre.equals(nombre)){
                existe = true;
                break;
            }
        }
        if(existe){
            System.out.println("Directorio existente");
        }
        else{
            Directorio nuevoDirectorio = new Directorio(nombre);
            directorios.add(nuevoDirectorio);
            System.out.println("Directorio creado");
        }     
    }
    
    public void CrearFichero(String nombre, String extension) throws Exception{
        boolean existe = false;
        for(Fichero fichero: ficheros){
           if(fichero.nombre.equals(nombre) && fichero.getExtension().equals(extension)){
               existe = true;
               break;
           }
        }
        if(existe){
            System.out.println("Fichero existente");        
        }
        else{
            Fichero nuevoFichero = new Fichero(nombre, extension);
            ficheros.add(nuevoFichero);
            System.out.println("Fichero creado correctamente");
        }
    }
    
    public Directorio AbrirDirectorio(String nombre) throws Exception{
        for(Directorio directorio: directorios){
            if(directorio.nombre.equals(nombre)){
                Sistema.setDirectorioAnterior(this);
                return directorio;
            }
        }
        System.out.println("Directorio no encontrado");
        return null;
    }
    
    public void AbrirFichero(String nombre, String extension) throws Exception{
        boolean existe = false;
        for(Fichero fichero: ficheros){
            if(fichero.nombre.equals(nombre) && fichero.getExtension().equals(extension)){
                existe = true;
                fichero.Abrir();
                break;
            }
        }
        if(!existe){
            throw new Exception("Fichero no encontrado");
        }
    }
    
    public Fichero BuscarFichero(String nombre, String extension){
        for(Fichero fichero: ficheros){
            if(fichero.getNombre().equals(nombre) && fichero.getExtension().equals(extension)){
                return fichero;
            }
        }
        return null;
    }
}
