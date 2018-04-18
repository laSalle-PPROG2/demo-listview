package edu.salle.pprog2.demolistview.activities;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import edu.salle.pprog2.demolistview.R;
import edu.salle.pprog2.demolistview.adapters.MyListViewAdapter;
import edu.salle.pprog2.demolistview.model.Item;

public class MainActivity extends AppCompatActivity {

    private EditText myEditText;
    private Button myButton;
    private ArrayList<Item> data;
    private MyListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_round);
        toolbar.setTitle("Titol hardcoded");

        myEditText = findViewById(R.id.editText);
        myButton = findViewById(R.id.button);

//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (myEditText != null) {
//                    String s = myEditText.getText().toString();
//                    Log.d(MainActivity.class.getName(), s);
//                }
//            }
//        });

        data = new ArrayList<>();
        for (int i = 10; i >=1; i--) {
            data.add(new Item("Title "+i,"text " +i ));
        }

//        adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                data);

        adapter = new MyListViewAdapter(data, this);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity_menu_sort_action_button:
                Collections.sort(data, Item.COMPARATOR);
                adapter.notifyDataSetChanged();
                break;

            case R.id.main_activity_menu_settings_action_button:
                break;
        }
        return false;
    }

    public void onMyButtonClick (View v) {
        if (myEditText != null) {
            String s = myEditText.getText().toString();
            Log.d(MainActivity.class.getName(), s);

            InputMethodManager inputManager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

            Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
            toast.show();

            Snackbar.make(findViewById(R.id.constraint_layout),
                    s, Snackbar.LENGTH_LONG).show();

            data.add(new Item(s,s));
            adapter.notifyDataSetChanged();
//            adapter.notifyDataSetInvalidated();
        }
    }

}
