/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import persistencia.Empleado;


public class EmpleadoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EmpleadoDAO() {
        emf = Persistence.createEntityManagerFactory("pu-Pizzeria");
        em = emf.createEntityManager();
    }

    public void agregar(Empleado empleado) {
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void actualizar(Empleado empleado) {
        try {
            em.getTransaction().begin();
            em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void eliminar(Long id) {
        try {
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.getTransaction().begin();
                em.remove(empleado);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public Empleado buscarPorId(Long id) {
        return em.find(Empleado.class, id);
    }

    public List<Empleado> buscarTodos() {
        TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
        return query.getResultList();
    }

    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {
        return em.createQuery("SELECT e FROM Empleado e WHERE e.nombre = :nombre", Empleado.class)
            .setParameter("nombre", nombre)
            .getResultList();
    }

    public void cerrar() {
        em.close();
        emf.close();
    }
}
