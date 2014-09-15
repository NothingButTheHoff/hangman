package com.pefi.Hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.*;
import android.view.View;
import android.view.Window;

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

    }


    public void startGame(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
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
    }



    public void killApp(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}
