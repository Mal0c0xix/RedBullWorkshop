package comind.fr.redbullworks.fragment;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shawnlin.numberpicker.NumberPicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import comind.fr.redbullworks.R;
import comind.fr.redbullworks.RedApplication;
import comind.fr.redbullworks.activity.ReservationActivity;
import comind.fr.redbullworks.model.Commande;
import comind.fr.redbullworks.model.News;
import comind.fr.redbullworks.utils.NewsAdapter;
import comind.fr.redbullworks.utils.XmlParser;

/**
 * Created by Pascal on 22/03/2017.
 *
 */

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {

    @ViewById(R.id.rss_redbull_lv)
    ListView newsListView;

    @ViewById(R.id.user_can_picker)
    NumberPicker cansPicker;

    @ViewById(R.id.user_table_number)
    NumberPicker tablePicker;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private List<News> newsList;
    private NewsAdapter adapter;
    private Commande currentCommande;

    @Click(R.id.commandBtn)
    void onCommandClicked()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(R.layout.dialog_command);
        dialogBuilder.setPositiveButton("Commander", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentCommande.setCans_number(cansPicker.getValue());
                currentCommande.setTable_number(tablePicker.getValue());
                currentCommande.setType("currents");
                dialog.dismiss();
            }
        });

        dialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog commandDialog = dialogBuilder.create();
        commandDialog.show();
    }

    @AfterViews
    void initialize()
    {

        newsList = new LinkedList<>();
        currentCommande = new Commande();
        XmlParser parser = new XmlParser();

        try {
            newsList = parser.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        adapter = new NewsAdapter(getContext(), R.layout.news_row_layout, newsList);

        newsListView.setAdapter(adapter);

    }

    private void sendRequest(String url)
    {

        try {
            JSONObject reservationObject = new JSONObject(currentCommande.toString());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, reservationObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            parseHttpResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            );
            RedApplication.getRequestQueue(getContext()).add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private void parseHttpResponse(JSONObject response) {
        Log.d("ReservationActivity ", response.toString());
    }

}
