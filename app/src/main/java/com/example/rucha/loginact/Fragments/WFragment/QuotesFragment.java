package com.example.rucha.loginact.Fragments.WFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rucha.loginact.R;

/**
 * Created by rucha on 06-03-2018.
 */

public class QuotesFragment extends Fragment {

    private TextView lcltxtQuoteView;
    private int lclCurrIndex = -1;
    private int lclQuoteArrLen;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.quote_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lcltxtQuoteView = getActivity().findViewById(R.id.txtquoteView);
        lclQuoteArrLen = ViewQuoteActivity.lclQuoteArray.length;
    }

    public int getShownIndex(){
        return lclCurrIndex;
    }

    public void showQuoteAtIndex(int newIndex){
        if(newIndex < 0 || newIndex >=lclQuoteArrLen){
            return;
        }
        lclCurrIndex = newIndex;
        lcltxtQuoteView.setText(ViewQuoteActivity.lclQuoteArray[lclCurrIndex]);
    }
}
