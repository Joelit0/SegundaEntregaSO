/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.filesystem;

/**
 *
 * @author Juan
 */
public class FileSystem {

    public static void main(String[] args) throws Exception {
        Grupos.CrearGrupo("sudo");
        
        Usuarios.CrearUsuario("root");
        
        Sistema.cambiarUsuario("root");
        
        Directorio directorioPrincipal = new Directorio("Sistema");
        
        Sistema.setDirectorioActual(directorioPrincipal);
              
        while(true){
            String input = System.console().readLine();
            String[] comando_parametros = input.split(" "); 
            Usuario usuarioActual = Sistema.getUsuarioActual();
            Directorio directorioActual = directorioPrincipal;

            switch(comando_parametros[0]){
                case "cambiarUsuario":
                    Sistema.cambiarUsuario(comando_parametros[1]);
                    break;
                case "crearUsuario":
                    if(usuarioActual.getPermisos()){
                        Usuarios.CrearUsuario(comando_parametros[1]);
                    }
                    else{
                        System.out.println("No tiene permisos para realizar esta acción");
                    }
                    break;
                case "eliminarUsuario":
                    if(usuarioActual.getPermisos()){
                        Usuarios.EliminarUsuario(comando_parametros[1]);
                    }
                    else{
                        System.out.println("No tiene permisos para realizar esta acción");
                    }
                    break;
                case "crearGrupo":
                    Grupos.CrearGrupo(comando_parametros[1]);
                    break;
                case "eliminarGrupo":
                    if(usuarioActual.getPermisos() && !comando_parametros[1].equals("sudo")){
                        Grupos.EliminarGrupo(comando_parametros[1]);
                    }
                    else{
                        System.out.println("No tiene permisos para realizar esta acción");
                    }
                    break;
                case "agregarUsuarioAGrupo":
                    if(usuarioActual.getPermisos()){
                        Grupos.agregarUsuarioAGrupo(comando_parametros[1], comando_parametros[2]);
                    }
                    else{
                        System.out.println("No tiene permisos para realizar esta acción");
                    }
                    break;
                case "NuevaCarpeta":
                    if(usuarioActual.getPermisos() || usuarioActual.getNombre().equals(directorioActual.getPropietario()) || directorioActual.getPermisosUsuario().getEscritura()){
                        Sistema.getDirectorioActual().NuevaCarpeta(comando_parametros[1]);
                    }
                    else{
                        System.out.println("No tiene permisos para realizar esta acción");
                    }
                    break;
                case "NuevoArchivo":
                    if(usuarioActual.getPermisos() || usuarioActual.getNombre().equals(directorioActual.getPropietario()) || directorioActual.getPermisosUsuario().getEscritura()){
                        String[] fichero = comando_parametros[1].split(".");
                        directorioActual.CrearFichero(fichero[0], fichero[1]);
                    }
                    else{
                        System.out.println("No tiene permisos para realizar esta acción");
                    }
                    break;
                case "AbrirCarpeta":
                    Directorio directorio = directorioActual.AbrirDirectorio(comando_parametros[1]);
                    if((directorio != null) && (usuarioActual.getPermisos() || directorio.getPropietario().equals(usuarioActual.getNombre()) || directorioActual.getPermisosUsuario().getLectura())){
                       Sistema.setDirectorioActual(directorio);
                    }
                    break;
                case "AbrirArchivo":
                    String[] fichero = comando_parametros[1].split(".");
                    if(usuarioActual.getPermisos() || directorioActual.b)
            }
            
        }
    }
}
