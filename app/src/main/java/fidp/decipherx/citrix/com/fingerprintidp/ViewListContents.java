package fidp.decipherx.citrix.com.fingerprintidp;

/**
 * Created by sanketmishra on 8/19/17.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        Cursor cursor = myDB.getListContents();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No clients have been added!",Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                HashMap<String,String> datum = new HashMap<>();
                datum.put("FQDN", cursor.getString(1));
                datum.put("ClientId", cursor.getString(2));
                data.add(datum);

                //ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,clientList);
                SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listitem_layout,
                        new String[] {"FQDN", "ClientId"}, new int[] {R.id.text1, R.id.text2});

                listView.setAdapter(adapter);
            }
        }
    }


}

