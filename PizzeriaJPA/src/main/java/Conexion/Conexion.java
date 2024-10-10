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
            entityManagerFactory = Persistence.createEntityManagerFactory("pu-Pizzeria");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Error al crear la EntityManagerFactory");
        }
    }

    /**
     * Método para crear la conexión.
     * 
     * @return Retorna el EntityManager.
     */
    public EntityManager crearConexion() {
        return entityManagerFactory.createEntityManager();
    }

     /**
     * Método para cerrar un EntityManager específico.
     * 
     * @param em El EntityManager a cerrar.
     */
    public void cerrarConexion(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
    
}
