/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.filesystem;

import java.util.Scanner;

/**
 *
 * @author Juan
 */
public class FileSystem {

    public static void main(String[] args) throws Exception {
        
        Scanner inp = new Scanner(System.in);

        Usuarios.CrearUsuario("root");
        
        Sistema.cambiarUsuario("root");
        
        Grupos.CrearGrupo("sudo");
        
        Directorio directorioPrincipal = new Directorio(); 
        
        Sistema.setDirectorioActual(directorioPrincipal);
        
        while(true){    
            String input = inp.nextLine();
            String[] comando_parametros = input.split(" "); 
            Usuario usuarioActual = Sistema.getUsuarioActual();
            Directorio directorioActual = Sistema.getDirectorioActual();
            try{
                switch(comando_parametros[0]){
                    case "cambiarUsuario":
                        Sistema.cambiarUsuario(comando_parametros[1]);
                        break;
                    case "crearUsuario":
                        Usuarios.CrearUsuario(comando_parametros[1]);
                        break;
                    case "eliminarUsuario":
                        Usuarios.EliminarUsuario(comando_parametros[1]);
                        break;
                    case "crearGrupo":
                        Grupos.CrearGrupo(comando_parametros[1]);
                        break;
                    case "eliminarGrupo":
                        Grupos.EliminarGrupo(comando_parametros[1]);
                        break;
                    case "agregarUsuarioAGrupo":
                        Grupos.agregarUsuarioAGrupo(comando_parametros[1], comando_parametros[2]);
                        break;
                    case "NuevaCarpeta":
                        Sistema.getDirectorioActual().NuevaCarpeta(comando_parametros[1]);
                        break;
                    case "NuevoArchivo":
                        if(!input.contains(".")){
                            System.out.println("No puede crear archivos sin extension");
                        }
                        else{
                            String nombreFichero = comando_parametros[1];
                            String[] ficheroNuevo = nombreFichero.split("\\.");
                            directorioActual.CrearFichero(ficheroNuevo[0], ficheroNuevo[1]);   
                        }
                        break;
                    case "AbrirCarpeta":
                        directorioActual.AbrirDirectorio(comando_parametros[1]);
                        break;
                    case "AbrirArchivo":
                        String[] fichero = comando_parametros[1].split("\\.");
                        Fichero ficheroAAbrir = directorioActual.BuscarFichero(fichero[0], fichero[1]);
                        if(fichero != null){
                           ficheroAAbrir.Abrir();
                        }
                        break;
                    case "MostrarArchivos":
                        directorioActual.MostrarArchivos();
                        break;
                    case "..":
                        Sistema.setDirectorioActual(Sistema.getDirectorioAnterior());
                        System.out.println("Directorio Actual: "+Sistema.getDirectorioActual().getNombre());
                        break;
                    case "cambiarPermisosUsuario":
                        directorioActual.SetPermisosUsuario(comando_parametros[1], comando_parametros[2]);
                        break;
                    case "cambiarPermisosGrupo":
                        directorioActual.SetPermisosGrupo(comando_parametros[1], comando_parametros[2]);
                        break;
                    case "eliminar":
                        directorioActual.Eliminar(comando_parametros[1]);
                        break;
                    case "renombrar":
                        directorioActual.Renombrar(comando_parametros[1], comando_parametros[2]);
                        break;
                    default:
                        System.out.println("Comando no valido");
                }
            }catch(Exception e){
               System.out.println("Comando no valido");
            }
        }
    }
}
