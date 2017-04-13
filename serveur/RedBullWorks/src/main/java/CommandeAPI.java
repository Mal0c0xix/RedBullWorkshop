
import model.Commande;
import stockage.Dao;

import javax.ws.rs.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pascal on 23/03/2017.
 *
 */

@Path("/commandes")
public class CommandeAPI {

    private Dao<Commande> dao;
    private List<Commande> commandes = new LinkedList<Commande>();

    public CommandeAPI()
    {
        //dao = new DaoJPA<Commande>("Commande");
        Commande testCommande = new Commande(5, 30, "currents");
        Commande testCommande2 = new Commande(23, 50, "received");
        Commande testCommande3 = new Commande(6, 70, "received");
        Commande testCommande4 = new Commande(3, 90, "currents");

        commandes.add(testCommande);
        commandes.add(testCommande2);
        commandes.add(testCommande3);
        commandes.add(testCommande4);
    }

    @Path("/currents")
    @GET
    @Produces("application/json")
    public String getAllCurrentsCommandes(@QueryParam("id") String user_id)
    {
        List<Commande> result = new LinkedList<Commande>();

        for (Commande c : commandes) {
            if(c.getType().equalsIgnoreCase("currents")) result.add(c);
        }

        return "{ \"Commandes\": " + result.toString() + "}";
    }

    @Path("/received")
    @GET
    @Produces("application/json")
    public String getAllReceivedCommandes(@QueryParam("id") String user_id)
    {
        List<Commande> result = new LinkedList<Commande>();

        for (Commande c : commandes) {
            if(c.getType().equalsIgnoreCase("received")) result.add(c);
        }

        return "{ \"Commandes\": " + result.toString() + "}";
    }

    @Path("/create")
    @POST
    @Consumes("application/json")
    public void createCommande(Commande receivedCommande)
    {
        commandes.add(receivedCommande);
    }

}
