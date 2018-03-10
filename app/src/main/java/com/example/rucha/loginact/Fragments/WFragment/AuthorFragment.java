package com.example.rucha.loginact.Fragments.WFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rucha.loginact.R;

/**
 * Created by rucha on 06-03-2018.
 */

public class AuthorFragment extends ListFragment {

    private ListSelectionListener lclListener;

    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        lclListener = (ListSelectionListener) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.author_item,
                ViewQuoteActivity.lclAuthorArray));
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id){
        getListView().setItemChecked(position, true);
        lclListener.onListSelection(position);
    }

}
