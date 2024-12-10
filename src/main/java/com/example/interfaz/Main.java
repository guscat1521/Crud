package com.example.interfaz;

import com.example.interfaz.CancionDAO;

public class Main {
    public static void main(String[] args){
        CancionDAO cancionDAO = new CancionDAO();


        //ELEMINAR REGISTRO
        System.out.println("Eliminar cancion con ID 2");
        cancionDAO.eliminarCancion(4);



        //CONSULTAR REGRISTRO NUEVAMENTE
        System.out.println("Cancion despues de actualizar: ");
        CancionDAO.consultarCancion();





    }
}


