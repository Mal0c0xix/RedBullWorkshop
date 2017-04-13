package comind.fr.redbullworks.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import comind.fr.redbullworks.model.News;
import comind.fr.redbullworks.R;

/**
 * Created by Pascal on 21/03/2017.
 *
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private List<News> dataSet;

    // View lookup cache
    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        TextView txtLink;

    }

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.dataSet = objects;
    }

    @Nullable
    @Override
    public News getItem(int position) {
        return dataSet.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        News dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;

        View result = convertView;

        if (result == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.news_row_layout, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.new_title);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.new_description);
            viewHolder.txtLink = (TextView) convertView.findViewById(R.id.new_link);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDescription.setText(dataModel.getDescription());
        viewHolder.txtLink.setText(dataModel.getLink());

        // Return the completed view to render on screen
        return result;
    }
}
