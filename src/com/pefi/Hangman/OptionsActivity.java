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

    String lang;

    //languages array
    ArrayAdapter<String> adapter;

    //UI
    Button b,b1;

    //passing data
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the selected language from the intent and set it
        intent = getIntent();
        lang = intent.getStringExtra("lang");
        if (lang != null){
            locale = new Locale(lang);
            config = new Configuration();
            config.locale = locale; //assign variable to be sent with intent
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //hides the default android titlebar
        setContentView(R.layout.options);

        //puts the available languages into an array variable
        String[] lang = getResources().getStringArray(R.array.languages_array);
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
                                config.locale = locale; //assign variable to be sent with intent
                                lang = locale.toString();
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                                //update UI
                                b.setText(R.string.select_lang);
                                b1.setText(R.string.back);
                                break;
                            case 1:
                                locale = new Locale("no");
                                config = new Configuration();
                                config.locale = locale;
                                lang = locale.toString(); //assign variable to be sent with intent
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                                //update UI
                                b.setText(R.string.select_lang);
                                b1.setText(R.string.back);
                                break;
                            default:
                                locale = new Locale("en");
                                config = new Configuration();
                                config.locale = locale;
                                lang = "en";
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        }

                        dialog.dismiss();
                    }
                }).create().show();
    }


    public void backToMenu(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //pass the selected language
        intent.putExtra("lang", lang);
        startActivity(intent);
        finish();

        //animated transition
        overridePendingTransition( R.anim.top_in, R.anim.bottom_out);
    }

} // end of class
