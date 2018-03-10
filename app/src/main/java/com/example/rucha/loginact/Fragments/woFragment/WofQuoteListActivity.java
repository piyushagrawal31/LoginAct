package com.example.rucha.loginact.Fragments.woFragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.rucha.loginact.R;


public class WofQuoteListActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String quote = intent.getStringExtra(WofTitleListActivity.INDEX);

        if (quote != null){
            setListAdapter(new ArrayAdapter<String>(WofQuoteListActivity.this, R.layout.activity_wof_quote_list,
            new String[] {quote}));
        }
    }
}
