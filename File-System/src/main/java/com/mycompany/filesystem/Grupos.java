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
public class Grupos {

    public static LinkedList<Grupo> grupos = new LinkedList<>();

    public static void CrearGrupo(String nombre) throws Exception {
        if (buscarGrupo(nombre) != null) {
            System.out.println("El grupo ya existe");
        } else {
            Grupo grupoNuevo = new Grupo(nombre);
            grupos.add(grupoNuevo);
            System.out.println("Grupo creado correctamente");
        }
    }

    public static void EliminarGrupo(String nombre) throws Exception {
        Grupo grupo = buscarGrupo(nombre);
        if (grupo == null) {
            System.out.println("El grupo no existe");
        } else {
            grupos.remove(grupo);
            System.out.println("Grupo eliminado correctamente");
        }
    }

    public static Grupo buscarGrupo(String nombre) {
        for (Grupo grupo : grupos) {
            if (grupo.getNombre().equals(nombre)) {
                return grupo;
            }
        }
        return null;
    }

    public static void agregarUsuarioAGrupo(String nombreUsuario, String nombreGrupo) throws Exception {
        Grupo grupo = Grupos.buscarGrupo(nombreGrupo);
        Usuario usuario = Usuarios.buscarUsuario(nombreUsuario);
        if (grupo == null || usuario == null) {
            if (grupo == null) {
                System.out.println("Grupo no existente");
            }
            if (usuario == null) {
                System.out.println("Usuario no existente");
            }
        } else {
            if (grupo.ConieneUsuario(nombreUsuario)) {
                System.out.println("El usuario " + nombreUsuario + " ya pertenece al grupo " + nombreGrupo);
            } else {
                if (grupo.getNombre().equals("sudo")) {
                    usuario.setPermisos(true);
                }
                grupo.agregarUusario(usuario);
                usuario.agregarGrupo(grupo);
                System.out.println("Usuario " + nombreUsuario + " agregado correctamente al gurpo " + nombreGrupo);
            }
        }
    }
}
