package andriod.training.cat.com.l04sqlite;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_show_list);

         DBHelper db = new DBHelper(getBaseContext());

         ArrayList<QueryObject> data = db.selectMessagesToList();
         DBAdapter adapter = new DBAdapter(this, data);
         ListView listView = (ListView) findViewById(R.id.lo_lv);
         listView.setAdapter(adapter);

     }

}
