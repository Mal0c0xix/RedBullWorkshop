import model.Commande;
import model.Reservation;

import javax.ws.rs.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pascal on 23/03/2017.
 *
 */

@Path("/reservations")
public class ReservationAPI {

    private List<Reservation> reservations = new LinkedList<Reservation>();

    public ReservationAPI()
    {
        Reservation reservation = new Reservation(new Date(), new Date(), "Gérard Menvussa", "ger.men@gmail.com",
                "0123456789", "10:30", "11:30");

        reservations.add(reservation);
    }

    @Path("/accepted")
    @GET
    @Produces("application/json")
    public String getAllReservations()
    {
        return reservations.toString();
    }

    @Path("/create")
    @POST
    @Consumes("application/json")
    public void createReservation(Reservation receivedReservation)
    {
        Reservation reservation = receivedReservation;
        reservations.add(reservation);
    }
}
