/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import Persistencia.Empleado;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author skevi
 */
public class EmpleadoDAO {
    
    private final Conexion conexion;

    public EmpleadoDAO() {
        this.conexion = new Conexion();
    }
    
    
    /**
     * Agrega un empleado.
     * 
     * @param empleado empleado a agregar.
     */
    public void agregarEmpleado(Empleado empleado) {
        EntityManager em = conexion.crearConexion();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin(); // Inicia la transacción
            em.persist(empleado); // Guarda el empleado en la base de datos
            transaction.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Reversa la transacción en caso de error
            }
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion(em); // Cierra el EntityManager
        }
    }
    
}
