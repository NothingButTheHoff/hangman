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
import android.widget.Button;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        Configuration c = new Configuration();
        System.out.println(c.describeContents());

        //get the custom font
        Typeface font = Typeface.createFromAsset(getAssets(), "ComingSoon.ttf");

        //get Buttons
        Button b  = (Button) findViewById(R.id.button_start);
        Button b1 = (Button) findViewById(R.id.button_rules);
        Button b2 = (Button) findViewById(R.id.button_settings);
        Button b3 = (Button) findViewById(R.id.button_exit);


        //set typeface to buttons
        b.setTypeface(font);
        b1.setTypeface(font);
        b2.setTypeface(font);
        b3.setTypeface(font);

    }


    public void startGame(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
        //animated transitions
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void showRules(View view){
        new AlertDialog.Builder(this)
                .setTitle(R.string.rules_button)
                .setMessage(R.string.rules)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    public void openSettings(View view) {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
    }



    public void killApp(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}
