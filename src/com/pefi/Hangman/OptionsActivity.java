package com.pefi.Hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by pererikfinstad on 15/09/14.
 */

public class OptionsActivity extends Activity {
    //configs
    Configuration config;
    Locale locale;

    //languages array
    ArrayAdapter<String> adapter;

    //UI
    Button b,b1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.options);

        String[] lang = new String[] {"Engelsk", "Norsk"};

        adapter = new ArrayAdapter<String>(this, R.layout.list, lang);

        //get and set new font
        Typeface font = Typeface.createFromAsset(getAssets(), "ComingSoon.ttf");

        b = (Button) findViewById(R.id.button_lang_select);
        b1 = (Button) findViewById(R.id.back_to_menu);
        b.setTypeface(font);
        b1.setTypeface(font);
    }

    public void selectLanguage(View v) {
        new AlertDialog.Builder(this)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int pos) {

                        switch (pos) {
                            case 0:
                                locale = new Locale("en");
                                config = new Configuration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                                b.setText(R.string.select_lang);
                                b1.setText(R.string.back);
                                break;
                            case 1:
                                locale = new Locale("no");
                                config = new Configuration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                                b.setText(R.string.select_lang);
                                b1.setText(R.string.back);
                                break;
                            default:
                                locale = new Locale("en");
                                config = new Configuration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        }

                        dialog.dismiss();
                    }
                }).create().show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //animated transition
        overridePendingTransition( R.anim.top_in, R.anim.bottom_out);
    }

    public void backToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //animated transition
        overridePendingTransition( R.anim.top_in, R.anim.bottom_out);
    }

} // end of class
