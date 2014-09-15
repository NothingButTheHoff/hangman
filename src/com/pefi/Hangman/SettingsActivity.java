package com.pefi.Hangman;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

/**
 * Created by pererikfinstad on 26/08/14.
 */
public class SettingsActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Locale locale;
    Configuration config;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);

        Spinner spinner = (Spinner) findViewById(R.id.languages_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages_array, R.layout.list);
        adapter.setDropDownViewResource(R.layout.list);
        //  Attach the adapter to the spinner
        spinner.setAdapter(adapter);
        //Add listener to spinner
        spinner.setOnItemSelectedListener(this);

    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (pos) {
            case 0:
                locale = new Locale("en");
           //     Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                config.setLocale(locale);
                break;
            case 1:
                locale = new Locale("no");
          //      Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                break;
            default:
                locale = new Locale("en");
              //  Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}