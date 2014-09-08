package com.pefi.Hangman;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by pererikfinstad on 01/09/14.
 */
public class KeyboardFragment extends Fragment implements OnClickListener {
    private final static String TAG = "KeyboardFragment";
    OnItemSelectedListener mCallback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.keyboard, container, false);





        Button b = (Button) view.findViewById(R.id.key1);
        b.setOnClickListener(this);
        Button b2 = (Button) view.findViewById(R.id.key2);
        b2.setOnClickListener(this);
        Button b3 = (Button) view.findViewById(R.id.key3);
        b3.setOnClickListener(this);
        Button b4 = (Button) view.findViewById(R.id.key4);
        b4.setOnClickListener(this);
        Button b5 = (Button) view.findViewById(R.id.key5);
        b5.setOnClickListener(this);
        Button b6 = (Button) view.findViewById(R.id.key6);
        b6.setOnClickListener(this);
        Button b7 = (Button) view.findViewById(R.id.key7);
        b7.setOnClickListener(this);
        Button b8 = (Button) view.findViewById(R.id.key8);
        b8.setOnClickListener(this);
        Button b9 = (Button) view.findViewById(R.id.key9);
        b9.setOnClickListener(this);
        Button b10 = (Button) view.findViewById(R.id.key10);
        b10.setOnClickListener(this);
        Button b11 = (Button) view.findViewById(R.id.key11);
        b11.setOnClickListener(this);
        Button b12 = (Button) view.findViewById(R.id.key12);
        b12.setOnClickListener(this);
        Button b13 = (Button) view.findViewById(R.id.key13);
        b13.setOnClickListener(this);
        Button b14 = (Button) view.findViewById(R.id.key14);
        b14.setOnClickListener(this);
        Button b15 = (Button) view.findViewById(R.id.key15);
        b15.setOnClickListener(this);
        Button b16 = (Button) view.findViewById(R.id.key16);
        b16.setOnClickListener(this);
        Button b17 = (Button) view.findViewById(R.id.key17);
        b17.setOnClickListener(this);
        Button b18 = (Button) view.findViewById(R.id.key18);
        b18.setOnClickListener(this);
        Button b19 = (Button) view.findViewById(R.id.key19);
        b19.setOnClickListener(this);
        Button b20 = (Button) view.findViewById(R.id.key20);
        b20.setOnClickListener(this);
        Button b21 = (Button) view.findViewById(R.id.key21);
        b21.setOnClickListener(this);
        Button b22 = (Button) view.findViewById(R.id.key22);
        b22.setOnClickListener(this);
        Button b23 = (Button) view.findViewById(R.id.key23);
        b23.setOnClickListener(this);
        Button b24 = (Button) view.findViewById(R.id.key24);
        b24.setOnClickListener(this);
        Button b25 = (Button) view.findViewById(R.id.key25);
        b25.setOnClickListener(this);
        Button b26 = (Button) view.findViewById(R.id.key26);
        b26.setOnClickListener(this);
        Button b27 = (Button) view.findViewById(R.id.key27);
        b27.setOnClickListener(this);
        Button b28 = (Button) view.findViewById(R.id.key28);
        b28.setOnClickListener(this);
        Button b29 = (Button) view.findViewById(R.id.key29);
        b29.setOnClickListener(this);

        return view;
    }

    //this interface must be implemented in the host activity
    public interface OnItemSelectedListener {
        public void onLetterSelected(String s);
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
    public void onKeyboardClick(String s){
        mCallback.onLetterSelected(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.key1:
                onKeyboardClick("a");
                v.setEnabled(false);
                break;
            case R.id.key2:
                onKeyboardClick("b");
                v.setEnabled(false);
                break;
            case R.id.key3:
                onKeyboardClick("c");
                v.setEnabled(false);
                break;
            case R.id.key4:
                onKeyboardClick("d");
                v.setEnabled(false);
                break;
            case R.id.key5:
                onKeyboardClick("e");
                v.setEnabled(false);
                break;
            case R.id.key6:
                onKeyboardClick("f");
                v.setEnabled(false);
                break;
            case R.id.key7:
                onKeyboardClick("g");
                v.setEnabled(false);
                break;
            case R.id.key8:
                onKeyboardClick("h");
                v.setEnabled(false);
                break;
            case R.id.key9:
                onKeyboardClick("i");
                v.setEnabled(false);
                break;
            case R.id.key10:
                onKeyboardClick("j");
                v.setEnabled(false);
                break;
            case R.id.key11:
                onKeyboardClick("k");
                v.setEnabled(false);
                break;
            case R.id.key12:
                onKeyboardClick("l");
                v.setEnabled(false);
                break;
            case R.id.key13:
                onKeyboardClick("m");
                v.setEnabled(false);
                break;
            case R.id.key14:
                onKeyboardClick("n");
                v.setEnabled(false);
                break;
            case R.id.key15:
                onKeyboardClick("o");
                v.setEnabled(false);
                break;
            case R.id.key16:
                onKeyboardClick("p");
                v.setEnabled(false);
                break;
            case R.id.key17:
                onKeyboardClick("q");
                v.setEnabled(false);
                break;
            case R.id.key18:
                onKeyboardClick("r");
                v.setEnabled(false);
                break;
            case R.id.key19:
                onKeyboardClick("s");
                v.setEnabled(false);
                break;
            case R.id.key20:
                onKeyboardClick("t");
                v.setEnabled(false);
                break;
            case R.id.key21:
                onKeyboardClick("u");
                v.setEnabled(false);
                break;
            case R.id.key22:
                onKeyboardClick("v");
                v.setEnabled(false);
                break;
            case R.id.key23:
                onKeyboardClick("w");
                v.setEnabled(false);
                break;
            case R.id.key24:
                onKeyboardClick("x");
                v.setEnabled(false);
                break;
            case R.id.key25:
                onKeyboardClick("y");
                v.setEnabled(false);
                break;
            case R.id.key26:
                onKeyboardClick("z");
                v.setEnabled(false);
                break;
            case R.id.key27:
                onKeyboardClick("æ");
                v.setEnabled(false);
                break;
            case R.id.key28:
                onKeyboardClick("ø");
                v.setEnabled(false);

                break;
            case R.id.key29:
                onKeyboardClick("å");
                v.setEnabled(false);
                break;


        }
    } //end of switch

}//end of class
