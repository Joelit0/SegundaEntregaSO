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
public class Grupo {
    
    private String nombre;
    
    private LinkedList<Usuario> usuarios;
    
    public Grupo(String nombre){
        this.nombre = nombre;
        usuarios = new LinkedList<>();
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public LinkedList<Usuario> getUsuarios(){
        return usuarios;
    }
    
    public boolean ContieneUsuarioPorNombre(String nombre){
        for(Usuario usuario: usuarios){
            if(usuario.getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
}
