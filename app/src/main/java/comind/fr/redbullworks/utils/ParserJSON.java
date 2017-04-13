package comind.fr.redbullworks.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import comind.fr.redbullworks.model.Commande;
import comind.fr.redbullworks.model.Reservation;

/**
 * Created by Pascal on 24/03/2017.
 *
 */

public class ParserJSON {


    private static Commande jsonToCommande(JSONObject json)
    {
        Commande result = null;


        try {
            JSONObject commandeObject = json.getJSONObject("Commande");
            result = new Commande(commandeObject.getInt("table_nb"), commandeObject.getInt("cans_nb"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Commande> jsonToCommandeList(String json)
    {
        List<Commande> commandes = new LinkedList<>();


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray commandesArray = jsonObject.getJSONArray("Commandes");

            for(int i=0; i<commandesArray.length(); i++)
            {
                commandes.add(jsonToCommande(commandesArray.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return commandes;
    }

    public static Reservation jsonToReservation(JSONObject json)
    {
        Reservation result = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);


        try {
            JSONObject reservationObject = json.getJSONObject("Reservation");
            result = new Reservation();
            result.setUserName(reservationObject.getString("username"));
            result.setUserMail(reservationObject.getString("usermail"));

            try {
                result.setStartDate(dateFormat.parse(reservationObject.getString("startdate")));
                result.setEndDate(dateFormat.parse(reservationObject.getString("enddate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Reservation> jsonToReservationList(String json)
    {
        List<Reservation> reservations = new LinkedList<>();


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray reservationsArray = jsonObject.getJSONArray("Commandes");

            for(int i=0; i<reservationsArray.length(); i++)
            {
                reservations.add(jsonToReservation(reservationsArray.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return reservations;
    }
}
