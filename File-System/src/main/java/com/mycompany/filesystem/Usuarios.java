/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.util.LinkedList;

/**
 *
 * @author jtaba
 */
public class Usuarios {
    
    public static LinkedList<Usuario> usuarios = new LinkedList<>();
    
    public static void CrearUsuario(String nombre) throws Exception{
        Usuario usuario = buscarUsuario(nombre);
        if(usuario != null){
            System.out.println("Usuario ya existente");        
        }
        else{
            Usuario nuevoUsuario = new Usuario(nombre);
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario creado correctamente");
        }
    }
    
    public static void EliminarUsuario(String nombre) throws Exception{
        Usuario usuario = buscarUsuario(nombre);
        if(usuario == null){
            System.out.println("Usuario no existente");        
        }
        else{
            usuarios.remove(usuario);
            System.out.println("Usuario eliminado correctamente");
        }
    }
    
    public static Usuario buscarUsuario(String nombre){
        for(Usuario usuario: usuarios){
            if(usuario.getNombre().equals(nombre)){
                return usuario;
            }
        }
        return null;
    }

}
