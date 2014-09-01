package com.pefi.Hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by pererikfinstad on 26/08/14.
 */
public class HangmanActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hangman);

        gameOverToast();

    }





    // METODER SOM MÅ IMPLEMENTERES

    public static String randomWord(){

        return "1";
    }

    public static void setCorrectLetter(){
    }

    public static void setWrongLetter(){

    }

    public static void updateWord(){
    }

    public static void updateNumberOfGames(){
    }

    public static void setUsedWords(String s){
    }

    public static void getNumberOfWrongLetters(){

    }

    public static void updateHangman(int i){
        switch (i) {
            case 0:
                //ingen grafikk
                break;
            case 1:
                //galge vises
                break;
            case 2:
                //hode vises
                break;
            case 3:
                //kropp vises
                break;
            case 4:
                //venstre arm
                break;
            case 5:
                //høyre arm
                break;
            case 6:
                //venstre fot
                break;
            case 7:
                //høyre fot
                break;
            default:
                //ingen grafikk

        }//end of switch
    }


    public void gameOverToast(){
        String text = getString(R.string.game_over);
        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG ).show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
} //end of class Hangman