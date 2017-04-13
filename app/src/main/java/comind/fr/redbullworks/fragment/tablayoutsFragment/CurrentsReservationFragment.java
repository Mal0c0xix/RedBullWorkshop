package comind.fr.redbullworks.fragment.tablayoutsFragment;

import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import comind.fr.redbullworks.R;
import comind.fr.redbullworks.RedApplication;
import comind.fr.redbullworks.model.Reservation;
import comind.fr.redbullworks.utils.ParserJSON;
import comind.fr.redbullworks.utils.ReservationAdapter;

/**
 * Created by Pascal on 27/03/2017.
 *
 */

@EFragment(R.layout.current_reservation_layout)
public class CurrentsReservationFragment extends Fragment {

    private List<Reservation> reservationDataSet = new ArrayList<>();
    private ReservationAdapter adapter;

    private static final String BASE_URL = RedApplication.BASE_URL + "reservations/";

    @ViewById(R.id.reservation_listview)
    ListView reservationListView;

    @AfterViews
    void initialize()
    {

        adapter = new ReservationAdapter(getActivity(), reservationDataSet);

        reservationListView.setAdapter(adapter);

        sendRequest(BASE_URL+"currents");
    }

    private void sendRequest(String url)
    {
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseHttpResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RedApplication.getRequestQueue(getContext()).add(stringRequest);
    }

    private void parseHttpResponse(String response) {
        reservationDataSet.addAll(ParserJSON.jsonToReservationList(response));
        adapter.notifyDataSetChanged();
    }

}
