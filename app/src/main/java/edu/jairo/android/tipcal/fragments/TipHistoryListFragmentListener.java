package edu.jairo.android.tipcal.fragments;

import edu.jairo.android.tipcal.models.TipRecord;

/**
 * Created by TSE on 01/06/2016.
 */
public interface TipHistoryListFragmentListener {
    // Aca agregaremos los eventos ante los cuales reaccionara el fragmento

    void addToList(TipRecord record);
    void clearList();
}
