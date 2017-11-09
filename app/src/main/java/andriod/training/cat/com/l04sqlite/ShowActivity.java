package andriod.training.cat.com.l04sqlite;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by LqLconf on 11/9/2017.
 */

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        DBHelper db = new DBHelper(getBaseContext());
        TextView tv_show = (TextView) findViewById(R.id.lo_tv_show);
        String data = db.selectMessages();
        //Check logic Browser version
        if (data.length() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_show.setText(Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY));
            } else {
                tv_show.setText(Html.fromHtml(data));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_show.setText(Html.fromHtml(getResources().getString(R.string.strXML_data_empty), Html.FROM_HTML_MODE_LEGACY));
            } else {
                tv_show.setText(Html.fromHtml(getResources().getString(R.string.strXML_data_empty)));
            }

        }
    }
}
