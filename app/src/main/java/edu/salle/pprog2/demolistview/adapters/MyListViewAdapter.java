package edu.salle.pprog2.demolistview.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.salle.pprog2.demolistview.R;
import edu.salle.pprog2.demolistview.model.Item;

public class MyListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private ArrayList<Item> data;
    private Context context;

    public MyListViewAdapter(ArrayList<Item> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_listview_activity_main,
                    parent, false);
        } else {
            view = convertView;
        }

        TextView text1 = view.findViewById(R.id.main_listview_item_text1);
        text1.setText(data.get(position).getTitle());

        TextView text2 = view.findViewById(R.id.main_listview_item_text2);
        text2.setText(data.get(position).getText());

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(context);

        builder.setTitle("Delete?");
        builder.setMessage("Do you want to delete "+
                data.get(position).getTitle() + "?");
        builder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.create().show();
    }
}
