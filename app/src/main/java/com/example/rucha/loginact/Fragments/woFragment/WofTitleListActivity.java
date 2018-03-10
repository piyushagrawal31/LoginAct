package com.example.rucha.loginact.Fragments.woFragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rucha.loginact.R;

public class WofTitleListActivity extends ListActivity {


    public static String[] arrayAuthor;
    public static String[] arrayQuote;

    public static final String INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_wof_quote_list);

        arrayAuthor = getResources().getStringArray(R.array.Authors);
        arrayQuote = getResources().getStringArray(R.array.Quotes);

        ArrayAdapter adapter = new ArrayAdapter<String>(WofTitleListActivity.this,
                R.layout.activity_wof_quote_list, WofTitleListActivity.arrayAuthor);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView lv, View v, int position, long id){
        Intent showIntent = new Intent(WofTitleListActivity.this, WofQuoteListActivity.class);
        showIntent.putExtra(INDEX, arrayQuote[position]);
        startActivity(showIntent);
    }

}

