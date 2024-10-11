/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.TipoIngrediente;


public class TipoIngredienteDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TipoIngredienteDAO() {
        emf = Persistence.createEntityManagerFactory("pu-Pizzeria");
        em = emf.createEntityManager();
    }
    
    public void agregar(TipoIngrediente tipo) {
        try {
            em.getTransaction().begin();
            em.persist(tipo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        }
    }
    
    public void cerrar() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
