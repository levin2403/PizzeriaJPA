/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


/**
 *
 * @author caarl
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import persistencia.Venta;

public class VentaDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public VentaDAO() {
        emf = Persistence.createEntityManagerFactory("pu-Pizzeria");
        em = emf.createEntityManager();
    }

    public void agregar(Venta venta) {
        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void actualizar(Venta venta) {
        try {
            em.getTransaction().begin();
            em.merge(venta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void eliminar(Long id) {
        try {
            Venta venta = em.find(Venta.class, id);
            if (venta != null) {
                em.getTransaction().begin();
                em.remove(venta);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public Venta buscarPorId(Long id) {
        return em.find(Venta.class, id);
    }

    public List<Venta> buscarTodos() {
        TypedQuery<Venta> query = em.createQuery("SELECT v FROM Venta v", Venta.class);
        return query.getResultList();
    }

    public List<Venta> buscarVentasAgostoSeptiembre2024() {
        return em.createQuery(
            "SELECT v FROM Venta v WHERE v.fecha BETWEEN :fechaInicio AND :fechaFin", 
            Venta.class)
            .setParameter("fechaInicio", java.sql.Date.valueOf("2024-08-01"))
            .setParameter("fechaFin", java.sql.Date.valueOf("2024-09-30"))
            .getResultList();
    }

    public void cerrar() {
        em.close();
        emf.close();
    }
}
