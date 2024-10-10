/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author skevi
 */
public class Conexion {

    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Error al crear la EntityManagerFactory");
        }
    }

    /**
     * Metodo para crear la conexion
     * @return retorna la conexion
     */
    public EntityManager crearConexion() { 
        return entityManagerFactory.createEntityManager();
    }


    /**
     * Metodo para cerrar la conexion;
     * 
     */
    public void cerrarConexion() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
