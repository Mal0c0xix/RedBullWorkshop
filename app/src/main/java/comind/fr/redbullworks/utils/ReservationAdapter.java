package comind.fr.redbullworks.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import comind.fr.redbullworks.R;
import comind.fr.redbullworks.model.Reservation;

/**
 * Created by Pascal on 27/03/2017.
 *
 */

public class ReservationAdapter extends ArrayAdapter<Reservation> {


    private List<Reservation> dataSet;
    private LayoutInflater mInflater;

    public ReservationAdapter(Context context, List<Reservation> objects) {
        super(context, R.layout.reservation_row_layout, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataSet = objects;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Reservation getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView title;
        TextView client_name;
        TextView periode;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.

        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.commande_row_layout, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.reservation_row_title);
            holder.client_name = (TextView) convertView.findViewById(R.id.reservation_client_name);
            holder.periode = (TextView) convertView.findViewById(R.id.reservation_periode_label);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        holder.title.setText("Reservation 23");
        holder.client_name.setText(dataSet.get(position).getUserName());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

        holder.periode.setText(format.format(dataSet.get(position).getStartDate()));

        return convertView;
    }


}
