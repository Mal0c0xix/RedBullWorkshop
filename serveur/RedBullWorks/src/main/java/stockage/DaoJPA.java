package stockage;

import org.eclipse.persistence.jpa.PersistenceProvider;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Created by Pascal on 23/03/2017.
 *
 */

@Stateless
public class DaoJPA<T> implements Dao<T> {

    private EntityManager em;
    private Class<?> classPojo;

    public DaoJPA(){}

    public DaoJPA(String className)
    {
        try {
            classPojo = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        em = Persistence.createEntityManagerFactory("redbullpersistance").createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public T lire(int index) {
        return (T) em.find(classPojo, index);
    }

    @SuppressWarnings("unchecked")
    public Vector lireTous() {
        return (Vector)em.createNamedQuery(classPojo.getSimpleName()+".findAll").getResultList();
    }

    public void creer(T t) throws SQLException {
        em.getTransaction().begin();

        em.persist(t);

        em.getTransaction().commit();
    }

    public void creerTous(List<T> t) throws SQLException {
        em.getTransaction().begin();

        for(T toto: t) em.persist(toto);

        em.getTransaction().commit();
    }

    public void mettreAJour(T t) {
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
    }

    public void mettreAJour(List<T> t) {
        em.getTransaction().begin();

        for (T tutu : t) {
            em.merge(tutu);
        }

        em.getTransaction().commit();
    }

    public void supprimer(T t) throws SQLException {
        em.getTransaction().begin();

        em.remove(t);

        em.getTransaction().commit();
    }

    public void supprimer(List<T> t) throws SQLException {
        em.getTransaction().begin();

        for(T titi: t) em.remove(titi);

        em.getTransaction().commit();
    }
}
