import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pascal on 23/03/2017.
 *
 */

@ApplicationPath("/")
public class RBApplication extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( CommandeAPI.class );
        h.add( ReservationAPI.class );
        return h;
    }
}
