package com.pefi.Hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;

import java.util.Locale;

/**
 * Created by pererikfinstad on 15/09/14.
 */

public class OptionsActivity extends Activity {
    Configuration config;
    Locale locale;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.options);

        String[] lang = new String[] {"Engelsk", "Norsk"};

        adapter = new ArrayAdapter<String>(this, R.layout.list, lang);
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
                                break;
                            case 1:
                                locale = new Locale("no");
                                config = new Configuration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
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

    }

    public void backToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} // end of class
