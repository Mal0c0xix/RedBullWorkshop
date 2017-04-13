package utils;

import model.Commande;
import org.json.JSONObject;

/**
 * Created by Pascal on 23/03/2017.
 *
 */
public class XmlParser {


    public static Commande jsonToCommande(String jsonCommande)
    {
        Commande result;

        JSONObject jsonObject = new JSONObject(jsonCommande);
        JSONObject commande = jsonObject.getJSONObject("Commande");

        result = new Commande(commande.getInt("table_nb"), commande.getInt("cans_number"));

        return result;
    }
}
