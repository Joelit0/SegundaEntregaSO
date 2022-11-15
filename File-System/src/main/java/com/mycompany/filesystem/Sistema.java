/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

/**
 *
 * @author jtaba
 */
public class Sistema {
    
    private static Usuario usuarioActual;
    
    private static Directorio directorioActual;
    
    private static Directorio directorioAnterior;
    
    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }
    
    public static void cambiarUsuario(String nombre){
        Usuario usuario = Usuarios.buscarUsuario(nombre);
        if(usuario == null){
            System.out.println("Usuario no encontrado");
        }
        else{
            usuarioActual = usuario;
            System.out.println("Usuario actual: "+usuario.getNombre());
        }
    }
    
    public static Directorio getDirectorioActual(){
        return directorioActual;
    }
    
    public static void setDirectorioActual(Directorio directorio){
        directorioActual = directorio;
    }
    
    public static Directorio getDirectorioAnterior(){
        return directorioAnterior;
    }
    
    public static void setDirectorioAnterior(Directorio directorio){
        directorioAnterior = directorio;
    }
}
