package com.pefi.Hangman;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by pererikfinstad on 01/09/14.
 */
public class KeyboardFragment extends Fragment implements OnClickListener {
    public final static String TAG = "KeyboardFragment";
    OnItemSelectedListener mCallback;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Locale locale = getResources().getConfiguration().locale;
        System.out.println(locale);
        if (locale.toString().equals("no")){
            view = inflater.inflate(R.layout.keyboard_no, container, false);
        }
        else{
            view = inflater.inflate(R.layout.keyboard, container, false);
        }

        Typeface font = Typeface.createFromAsset(getResources().getAssets(), "ComingSoon.ttf");



        Button b = (Button) view.findViewById(R.id.key1);
        b.setOnClickListener(this);
        b.setTypeface(font);
        Button b2 = (Button) view.findViewById(R.id.key2);
        b2.setOnClickListener(this);
        b2.setTypeface(font);
        Button b3 = (Button) view.findViewById(R.id.key3);
        b3.setTypeface(font);
        b3.setOnClickListener(this);
        Button b4 = (Button) view.findViewById(R.id.key4);
        b4.setOnClickListener(this);
        b4.setTypeface(font);
        Button b5 = (Button) view.findViewById(R.id.key5);
        b5.setOnClickListener(this);
        b5.setTypeface(font);
        Button b6 = (Button) view.findViewById(R.id.key6);
        b6.setOnClickListener(this);
        b6.setTypeface(font);
        Button b7 = (Button) view.findViewById(R.id.key7);
        b7.setOnClickListener(this);
        b7.setTypeface(font);
        Button b8 = (Button) view.findViewById(R.id.key8);
        b8.setOnClickListener(this);
        b8.setTypeface(font);
        Button b9 = (Button) view.findViewById(R.id.key9);
        b9.setOnClickListener(this);
        b9.setTypeface(font);
        Button b10 = (Button) view.findViewById(R.id.key10);
        b10.setOnClickListener(this);
        b10.setTypeface(font);
        Button b11 = (Button) view.findViewById(R.id.key11);
        b11.setOnClickListener(this);
        b11.setTypeface(font);
        Button b12 = (Button) view.findViewById(R.id.key12);
        b12.setOnClickListener(this);
        b12.setTypeface(font);
        Button b13 = (Button) view.findViewById(R.id.key13);
        b13.setOnClickListener(this);
        b13.setTypeface(font);
        Button b14 = (Button) view.findViewById(R.id.key14);
        b14.setOnClickListener(this);
        b14.setTypeface(font);
        Button b15 = (Button) view.findViewById(R.id.key15);
        b15.setOnClickListener(this);
        b15.setTypeface(font);
        Button b16 = (Button) view.findViewById(R.id.key16);
        b16.setOnClickListener(this);
        b16.setTypeface(font);
        Button b17 = (Button) view.findViewById(R.id.key17);
        b17.setOnClickListener(this);
        b17.setTypeface(font);
        Button b18 = (Button) view.findViewById(R.id.key18);
        b18.setOnClickListener(this);
        b18.setTypeface(font);
        Button b19 = (Button) view.findViewById(R.id.key19);
        b19.setOnClickListener(this);
        b19.setTypeface(font);
        Button b20 = (Button) view.findViewById(R.id.key20);
        b20.setOnClickListener(this);
        b20.setTypeface(font);
        Button b21 = (Button) view.findViewById(R.id.key21);
        b21.setOnClickListener(this);
        b21.setTypeface(font);
        Button b22 = (Button) view.findViewById(R.id.key22);
        b22.setOnClickListener(this);
        b22.setTypeface(font);
        Button b23 = (Button) view.findViewById(R.id.key23);
        b23.setOnClickListener(this);
        b23.setTypeface(font);
        Button b24 = (Button) view.findViewById(R.id.key24);
        b24.setOnClickListener(this);
        b24.setTypeface(font);
        Button b25 = (Button) view.findViewById(R.id.key25);
        b25.setOnClickListener(this);
        b25.setTypeface(font);
        Button b26 = (Button) view.findViewById(R.id.key26);
        b26.setOnClickListener(this);
        b26.setTypeface(font);
        if (locale.toString().equals("no")){

            Button b27 = (Button) view.findViewById(R.id.key27);
            b27.setOnClickListener(this);
            b27.setTypeface(font);
            Button b28 = (Button) view.findViewById(R.id.key28);
            b28.setOnClickListener(this);
            b28.setTypeface(font);
            Button b29 = (Button) view.findViewById(R.id.key29);
            b29.setOnClickListener(this);
            b29.setTypeface(font);
        }

        return view;
    }

    //this interface must be implemented in the host activity
    public interface OnItemSelectedListener {
        public void onLetterSelected(String s, String id);
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
    public void onKeyboardClick(String s, String id){

        mCallback.onLetterSelected(s, id);
    }

    public void generateKeyboard(){}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.key1:
                onKeyboardClick("a","key1");
                v.setEnabled(false);
                break;
            case R.id.key2:
                onKeyboardClick("b", "key2");
                v.setEnabled(false);
                break;
            case R.id.key3:
                onKeyboardClick("c", "key3");
                v.setEnabled(false);
                break;
            case R.id.key4:
                onKeyboardClick("d", "key4");
                v.setEnabled(false);
                break;
            case R.id.key5:
                onKeyboardClick("e","key5");
                v.setEnabled(false);
                break;
            case R.id.key6:
                onKeyboardClick("f","key6");
                v.setEnabled(false);
                break;
            case R.id.key7:
                onKeyboardClick("g", "key7");
                v.setEnabled(false);
                break;
            case R.id.key8:
                onKeyboardClick("h","key8");
                v.setEnabled(false);
                break;
            case R.id.key9:
                onKeyboardClick("i","key9");
                v.setEnabled(false);
                break;
            case R.id.key10:
                onKeyboardClick("j","key10");
                v.setEnabled(false);
                break;
            case R.id.key11:
                onKeyboardClick("k","key11");
                v.setEnabled(false);
                break;
            case R.id.key12:
                onKeyboardClick("l","key12");
                v.setEnabled(false);
                break;
            case R.id.key13:
                onKeyboardClick("m","key13");
                v.setEnabled(false);
                break;
            case R.id.key14:
                onKeyboardClick("n","key14");
                v.setEnabled(false);
                break;
            case R.id.key15:
                onKeyboardClick("o","key15");
                v.setEnabled(false);
                break;
            case R.id.key16:
                onKeyboardClick("p","key16");
                v.setEnabled(false);
                break;
            case R.id.key17:
                onKeyboardClick("q","key17");
                v.setEnabled(false);
                break;
            case R.id.key18:
                onKeyboardClick("r","key18");
                v.setEnabled(false);
                break;
            case R.id.key19:
                onKeyboardClick("s","key19");
                v.setEnabled(false);
                break;
            case R.id.key20:
                onKeyboardClick("t","key20");
                v.setEnabled(false);
                break;
            case R.id.key21:
                onKeyboardClick("u", "key21");
                v.setEnabled(false);
                break;
            case R.id.key22:
                onKeyboardClick("v","key22");
                v.setEnabled(false);
                break;
            case R.id.key23:
                onKeyboardClick("w","key23");
                v.setEnabled(false);
                break;
            case R.id.key24:
                onKeyboardClick("x","key24");
                v.setEnabled(false);
                break;
            case R.id.key25:
                onKeyboardClick("y","key25");
                v.setEnabled(false);
                break;
            case R.id.key26:
                onKeyboardClick("z","key26");
                v.setEnabled(false);
                break;
            case R.id.key27:
                onKeyboardClick("æ","key27");
                v.setEnabled(false);
                break;
            case R.id.key28:
                onKeyboardClick("ø","key28");
                v.setEnabled(false);

                break;
            case R.id.key29:
                onKeyboardClick("å","key29");
                v.setEnabled(false);
                break;


        }
    } //end of switch



}//end of class
