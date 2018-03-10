package com.example.rucha.loginact.MenuExample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.rucha.loginact.Database.DatabaseActivity;
import com.example.rucha.loginact.ListViewActivity;
import com.example.rucha.loginact.R;
import com.example.rucha.loginact.WelcomeActivity;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv_items = findViewById(R.id.lvItem_list);
        lv_items.setOnItemClickListener(this);

        ArrayAdapter adapter = null;
        lv_items.setAdapter(adapter);

        registerForContextMenu(lv_items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_database:
                Intent intentDatabase = new Intent(MenuActivity.this, DatabaseActivity.class);
                startActivity(intentDatabase);
                break;
            case R.id.action_listview:
                Intent intentListview = new Intent(MenuActivity.this, ListViewActivity.class);
                startActivity(intentListview);
                break;
            case R.id.action_welcome:
                Intent intentWelcome = new Intent(MenuActivity.this, WelcomeActivity.class);
                startActivity(intentWelcome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), "You clicked [" + i + "] item", Toast.LENGTH_SHORT).show();
    }

    // Menu for list which comes up when you press the item for long time

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("Position: " + info.position );
        String[] menuItems = getResources().getStringArray(R.array.menu);
        for (int i=0; i<menuItems.length; i++){
            menu.add(menu.NONE, i, i, menuItems[i]);
        }
    }

    // to define what happens when you click on particular
    // item of menu which comes when you press item for long time

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int itemMenuIndex = item.getItemId();

        switch (itemMenuIndex){
            case 0:
                // for delete
                Toast.makeText(getApplicationContext(), "Delete Pressed", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                // for Exclude
                Toast.makeText(getApplicationContext(), "Exclude Pressed", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                // for Marking Favorite
                Toast.makeText(getApplicationContext(), "Mark it favorite", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
