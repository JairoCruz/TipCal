package edu.jairo.android.tipcal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.jairo.android.tipcal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener {


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tip_history_list, container, false);
    }

    @Override
    public void action(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}