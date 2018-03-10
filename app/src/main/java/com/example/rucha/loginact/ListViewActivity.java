package com.example.rucha.loginact;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.rucha.loginact.Classes.Country;
import com.example.rucha.loginact.Classes.LineType;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lclListViewCountry;
    List<Country> country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        country = getCountry();
        lclListViewCountry = findViewById(R.id.listviewCountry);

        ArrayAdapter adapter = new ShowCountryAdapter(getApplicationContext(), country);
        lclListViewCountry.setAdapter(adapter);
        lclListViewCountry.setOnItemClickListener(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //works only for version above marshmellow
//            lclListViewCountry.setOnContextClickListener(this);
//        }

//      Create context menu when we hold for long
        registerForContextMenu(lclListViewCountry);
    }

    private List<Country> getCountry() {

        List<Country> countries = new ArrayList<>();
     /*   Country.Builder builder = new Country.Builder();
        builder.setId(1).setLineType(LineType.GREEN.toString());
        countries.add(builder.build("India"));

        builder.setId(2).setLineType(LineType.BLUE.toString());
        countries.add(builder.build("America"));*/
        Country india = new Country(1, "India", LineType.GREEN, R.drawable.landscape);
        countries.add(india);

        Country america = new Country(2, "America", LineType.BLUE, R.drawable.landscape);
        countries.add(america);
        return countries;
    }

//    @Override
//    public boolean onContextClick(View view) {
//        return false;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_help:
                Toast.makeText(getApplicationContext(), "You have clicked Help!!!", Toast.LENGTH_SHORT);
            break;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "You just clicked Settings!!!", Toast.LENGTH_SHORT);
            break;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), country.get(i).toString(), Toast.LENGTH_SHORT).show();
//        view.findViewById();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}