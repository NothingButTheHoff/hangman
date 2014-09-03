package com.pefi.Hangman;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

/**
 * Created by pererikfinstad on 01/09/14.
 */
public class KeyboardFragment extends Fragment implements OnClickListener {
    OnItemSelectedListener mCallback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.keyboard, container, false);

        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(this);

        return view;
    }








    //interface that must be implemented in the host activity
    public interface OnItemSelectedListener {
        public void onLetterSelected(int i);
    }

    //binds the fragment interface onAttach
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented the callback interface. If not, it throws an exception
        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
    }


    //When user selects a letter from the keyboard, send it to the host activity
    public void onKeyboardClick(int pos){
        mCallback.onLetterSelected(pos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                onKeyboardClick(1);
                break;
        }
    }




}//end of class
