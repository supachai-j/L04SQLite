package andriod.training.cat.com.l04sqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LqLconf on 11/9/2017.
 */

public class DBAdapter extends ArrayAdapter<QueryObject> {
    private final Context c;

    DBAdapter(Context context, ArrayList<QueryObject> db) {
        super(context, R.layout.item_show_list, db);
        this.c = context;
    }

    private static class ViewHolder {
        ImageView imv_icon;
        TextView tv_firstLine;
        TextView tv_secondLine;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_show_list, parent, false);

            holder = new ViewHolder();
            holder.imv_icon = (ImageView) convertView.findViewById(R.id.lo_imv_icon);
            holder.tv_firstLine = (TextView) convertView.findViewById(R.id.lo_tv_firstLine);
            holder.tv_secondLine = (TextView) convertView.findViewById(R.id.lo_tv_secondLine);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        QueryObject db = getItem(position);

        assert db != null;
        holder.tv_firstLine.setText("("+db.sex+") "+db.name);
        holder.tv_secondLine.setText(db.message+"\r\n"+db.time);
        if (db.sex.startsWith("M")) {
            holder.imv_icon.setImageResource(R.drawable.im_man);
        }else if (db.sex.startsWith("W")){
            holder.imv_icon.setImageResource(R.drawable.im_woman);
        }else{
            holder.imv_icon.setImageResource(R.drawable.im_unknow);
        }
        return convertView;
    }
}

