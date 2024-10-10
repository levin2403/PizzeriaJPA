/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import Persistencia.Venta;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author skevi
 */
public class VentaDAO {
    
    private final Conexion conexion;

    public VentaDAO() {
        this.conexion = new Conexion();
    }
   
/**
     * Agrega una nueva venta.
     * 
     * @param venta Venta a agregar.
     */
    public void agregar(Venta venta) {
        EntityManager em = conexion.crearConexion();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin(); // Inicia la transacción
            em.persist(venta); // Persiste la venta en la base de datos
            transaction.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Reversa la transacción en caso de error
            }
            e.printStackTrace();
        } finally {
            em.close(); // Cierra el EntityManager
        }
    }

    /**
     * Busca las ventas realizadas desde agosto del 2024 hasta septiembre de 2024.
     * 
     * @return Lista de ventas.
     */
    public List<Venta> buscarPorFecha() {
        EntityManager em = conexion.crearConexion();
        List<Venta> ventas = null;

        try {
            // Define el rango de fechas
            LocalDate fechaInicio = LocalDate.of(2024, 8, 1);
            LocalDate fechaFin = LocalDate.of(2024, 9, 30);

            // Consulta JPQL para buscar ventas en el rango de fechas
            String jpql = "SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin";
            TypedQuery<Venta> query = em.createQuery(jpql, Venta.class);
            query.setParameter("inicio", fechaInicio);
            query.setParameter("fin", fechaFin);

            ventas = query.getResultList(); // Ejecuta la consulta y obtiene la lista de ventas
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Cierra el EntityManager
        }

        return ventas; // Retorna la lista de ventas
    }
    
}
