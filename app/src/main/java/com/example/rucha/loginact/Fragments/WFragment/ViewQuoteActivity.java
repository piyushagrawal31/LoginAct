package com.example.rucha.loginact.Fragments.WFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rucha.loginact.R;

public class ViewQuoteActivity extends AppCompatActivity implements AuthorFragment.ListSelectionListener {

    public static String[] lclAuthorArray;
    public static String[] lclQuoteArray;
    public QuotesFragment lclDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lclAuthorArray = getResources().getStringArray(R.array.Authors);
        lclQuoteArray = getResources().getStringArray(R.array.Quotes);

        setContentView(R.layout.activity_main_fragment);

        lclDetailsFragment = (QuotesFragment) getSupportFragmentManager().
                findFragmentById(R.id.quotes);

    }

    @Override
    public void onListSelection(int index) {
        if (lclDetailsFragment.getShownIndex() != index){
            lclDetailsFragment.showQuoteAtIndex(index);
        }
    }
}
