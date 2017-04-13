package comind.fr.redbullworks.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import comind.fr.redbullworks.R;
import comind.fr.redbullworks.model.Commande;

/**
 * Created by Pascal on 24/03/2017.
 *
 */

public class CommandeAdapter extends ArrayAdapter<Commande> {

    private List<Commande> dataSet;
    private LayoutInflater mInflater;

    public CommandeAdapter(Context context, List<Commande> objects) {
        super(context, R.layout.commande_row_layout, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataSet = objects;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Commande getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView commande_row_table;
        TextView commande_row_cans_number;

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
            holder.commande_row_table = (TextView) convertView.findViewById(R.id.commande_row_table);
            holder.commande_row_cans_number = (TextView) convertView.findViewById(R.id.commande_row_cans_number);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        holder.commande_row_table.setText(String.valueOf(dataSet.get(position).getTable_number()));
        holder.commande_row_cans_number.setText(String.valueOf(dataSet.get(position).getCans_number()));


        return convertView;
    }
}
