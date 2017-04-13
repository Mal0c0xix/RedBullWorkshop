package stockage;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pascal on 23/03/2017.
 */
public interface Dao<T> {
    T lire (int index);
    List<T> lireTous();

    void creer(T t) throws SQLException;
    void creerTous(List<T> t) throws SQLException;

    void mettreAJour(T t);
    void mettreAJour(List<T> t);

    void supprimer(T t) throws SQLException;
    void supprimer(List<T> t) throws SQLException;
}
