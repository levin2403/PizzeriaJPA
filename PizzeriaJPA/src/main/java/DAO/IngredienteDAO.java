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
import persistencia.Ingrediente;


public class IngredienteDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public IngredienteDAO() {
        emf = Persistence.createEntityManagerFactory("pu-Pizzeria");
        em = emf.createEntityManager();
    }

    public void agregar(Ingrediente ingrediente) {
        try {
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void actualizar(Ingrediente ingrediente) {
        try {
            em.getTransaction().begin();
            em.merge(ingrediente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void eliminar(Long id) {
        try {
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente != null) {
                em.getTransaction().begin();
                em.remove(ingrediente);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public Ingrediente buscarPorId(Long id) {
        return em.find(Ingrediente.class, id);
    }

    public List<Ingrediente> buscarTodos() {
        TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
        return query.getResultList();
    }

    public List<Ingrediente> buscarIngredientesConA() {
        TypedQuery<Ingrediente> query = em.createQuery(
            "SELECT i FROM Ingrediente i WHERE LOWER(i.nombre) LIKE 'a%' OR LOWER(i.nombre) LIKE '%a'", 
            Ingrediente.class);
        return query.getResultList();
    }

    public void cerrar() {
        em.close();
        emf.close();
    }
}
