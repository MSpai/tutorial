package com.example.mpipini.tutorial.Interfaces;

import android.view.View;

/**
 * Created by Maria Spai on 09/11/2017.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}