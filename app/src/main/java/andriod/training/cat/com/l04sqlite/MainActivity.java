package andriod.training.cat.com.l04sqlite;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] titles;
    String[] subtitles;
    String[] messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.strXML_titles_insert);
        subtitles = getResources().getStringArray(R.array.strXML_subtitles_insert);
        messages = getResources().getStringArray(R.array.strXML_messages_insert);

        //Button Clear
        Button bt_clear = (Button) findViewById(R.id.lo_bt_clear);
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(getBaseContext());
                db.onReset();
                Toast.makeText(getBaseContext(), "Clear Database Success!", Toast.LENGTH_SHORT).show();
            }
        });

        //Button Clear
        Button bt_insert = (Button) findViewById(R.id.lo_bt_insert);
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(getBaseContext());
                Random r = new Random();
                String title = titles[r.nextInt(titles.length)];
                String subtitle = subtitles[r.nextInt(subtitles.length)];
                String message = messages[r.nextInt(messages.length)];
                @SuppressLint("SimpleDateFormat") String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
                //insert data to db
                db.insertMessage(title, subtitle, message, timestamp);
                Toast.makeText(getBaseContext(), "Insert ("+subtitle + ") " + title + ":" + message + " [" + timestamp + "]", Toast.LENGTH_SHORT).show();

            }
        });

        //Button Show
        Button bt_show = (Button) findViewById(R.id.lo_bt_show);
        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPage(ShowActivity.class, true);

            }
        });


    }
    public void gotoPage(Class dest_class, boolean transition) {
        Intent subIntent = new Intent(getApplicationContext(), dest_class);
        startActivity(subIntent);
        if (transition) overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        }
}
