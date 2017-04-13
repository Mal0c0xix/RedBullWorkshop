package comind.fr.redbullworks.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import comind.fr.redbullworks.R;
import comind.fr.redbullworks.RedApplication;
import comind.fr.redbullworks.model.Reservation;

@EActivity(R.layout.activity_reservation)
public class ReservationActivity extends AppCompatActivity {

    private Reservation reservation;
    private static final String BASE_URL = RedApplication.BASE_URL + "reservations/";

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.reservation_username_et)
    EditText UserName;

    @ViewById(R.id.reservation_mail_et)
    EditText UserMail;

    @ViewById(R.id.reservation_userphone_et)
    EditText UserPhone;

    @ViewById(R.id.startdate_picker_icon)
    ImageView startDatePickerIcon;

    @ViewById(R.id.startdate_picker_label)
    TextView startDateLabel;

    @ViewById(R.id.enddate_picker_label)
    TextView endDateLabel;

    @ViewById(R.id.reservation_enddate_icon)
    ImageView endDatePickerIcon;

    @AfterViews
    void initialize()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reservation = new Reservation();
    }

    @Click(R.id.startdate_picker_icon)
    void onStartDatePickerClicked()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View dialogView = layoutInflater.inflate(R.layout.dialog_datepicker_layout, null);
        final DatePicker datepicker = (DatePicker) dialogView.findViewById(R.id.reservation_datepicker);
        final TimePicker timepicker = (TimePicker) dialogView.findViewById(R.id.reservation_timepicker);

        builder.setView(dialogView);
        builder.setTitle(R.string.datepicker_startdate_label_text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int day = datepicker.getDayOfMonth();
                int month = datepicker.getMonth();
                int year =  datepicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                Date tempDate = calendar.getTime();

                datepicker.setVisibility(View.GONE);
                timepicker.setVisibility(View.VISIBLE);

                tempDate.setHours(timepicker.getCurrentHour());
                tempDate.setMinutes(timepicker.getCurrentMinute());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);

                String selectedDate = "Date de début choisie : " + dateFormat.format(tempDate);

                reservation.setStartDate(tempDate);
                startDateLabel.setText(selectedDate);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog datePickerDialog = builder.create();
        datePickerDialog.show();
    }

    @Click(R.id.reservation_enddate_icon)
    void onEndDatePickerClicked()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View dialogView = layoutInflater.inflate(R.layout.dialog_datepicker_layout, null);
        final DatePicker datepicker = (DatePicker) dialogView.findViewById(R.id.reservation_datepicker);
        final TimePicker timepicker = (TimePicker) dialogView.findViewById(R.id.reservation_timepicker);

        builder.setView(dialogView);
        builder.setTitle(R.string.reservation_enddate_label);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int day = datepicker.getDayOfMonth();
                int month = datepicker.getMonth();
                int year =  datepicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                Date tempDate = calendar.getTime();

                tempDate.setHours(timepicker.getCurrentHour());
                tempDate.setMinutes(timepicker.getCurrentMinute());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);

                String selectedDate = "Date de fin choisie : " + dateFormat.format(tempDate);

                reservation.setEndDate(tempDate);
                endDateLabel.setText(selectedDate);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog datePickerDialog = builder.create();
        datePickerDialog.show();
    }

    @Click(R.id.reservation_send_button)
    void onReservationSendBtn()
    {
        reservation.setUserName(UserName.getText().toString());
        reservation.setUserMail(UserMail.getText().toString());
        reservation.setUserPhone(UserPhone.getText().toString());

        //TODO Ajouter requête serveur pour insertion réservation dans la base
        sendRequest(BASE_URL + "/create");
        Toast.makeText(this, "Réservation envoyée !!!", Toast.LENGTH_LONG).show();
    }

    private void sendRequest(String url)
    {

        try {
            JSONObject reservationObject = new JSONObject(reservation.toString());
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
                            Toast.makeText(ReservationActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            );
            RedApplication.getRequestQueue(ReservationActivity.this).add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private void parseHttpResponse(JSONObject response) {
        Log.d("ReservationActivity ", response.toString());
    }


}
