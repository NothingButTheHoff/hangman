package com.pefi.Hangman;import android.app.Activity;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.widget.TextView;import android.widget.Toast;import java.util.ArrayList;import java.util.Arrays;import java.util.Random;/** * Created by pererikfinstad on 26/08/14. */public class HangmanActivity extends Activity implements KeyboardFragment.OnItemSelectedListener{    private final static String TAG = "HangmanActivity";    private String  selectedWord;    private int                numberOfWrongLetters = 0,                numberOfWins         = 0,                numberOfGames        = 0,                nextInt              = 0;    private String[]                wordList,                letterArray,                inputLetters,                usedWords;    private String word;    ArrayList<?> touchables;    @Override    public void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        this.requestWindowFeature(Window.FEATURE_NO_TITLE);        setContentView(R.layout.hangman);        //get the list of words        wordList = getResources().getStringArray(R.array.word_array);        usedWords = new String[wordList.length];        startNewGame();    }    //Interface for getting data from KeyboardFragment    @Override    public void onLetterSelected(String s) {        // Capture the article fragment from the activity layout        checkLetter(s);    }    public void startNewGame(){        //pick a random word an put in in a char array        word = randomWord();        //split the word up into an array        buildWordArray(word);        //Letters selected by the user        inputLetters = new String[word.length()];        TextView tv = (TextView) findViewById(R.id.input_word);        //puts correct number of letters into an array        for (int i = 0; i < inputLetters.length; i ++){            inputLetters[i] = "_ ";            tv.append(inputLetters[i]);        }        // FOR DEBUG ONLY        System.out.println("Ordet som ble plukket ut er: " + Arrays.toString(letterArray));    }    public String randomWord(){        Random rand = new Random();        //pick a random number        int randInt = rand.nextInt(wordList.length);        String word = wordList[randInt];        if (checkUsedWords(word, wordList)){            randomWord();        }        //Updates the array of used words        setUsedWords(word);        return word;    }    //checks if the word is already used    public static boolean checkUsedWords(String s, String[] a){        for (int i = 0; i > a.length; i ++){            if (s.contains(a[i])){                return true;            }        }        return false;    }    //puts used words into a used word array    public void setUsedWords(String s){        usedWords[nextInt] = s;        nextInt++;    }    public void buildWordArray(String s){        letterArray = s.split("(?!^)");    }    //returns used words    public String getUsedWords(){        String[] s = usedWords;        return Arrays.toString(s);    }    //returns the position of the selected letter in a word    public int getPosition(String s){        int index = Arrays.asList(letterArray).indexOf(s);        return index;    }    // checks if the word contains the selected letter    public void checkLetter(String s){        //sjekk om bokstaven finner i ordet        if (word.contains(s)){            System.out.println("JEPPPPPPPP");            System.out.println("Posisjonen er " + getPosition(s));            updateInputString(word, getPosition(s), s);        }        else{            setWrongLetter();            System.out.println("NOOOOOOPE");        }        //true -> updateWord        //false ->setWrongLetter    }    public void setWrongLetter(){        numberOfWrongLetters++;        if (numberOfWrongLetters > 6){            gameOverToast();        }        // kall på gameOverToast og øk numberOfGames med 1 (++)    }    public boolean compareWords(){        String s = new String();        for (int i = 0; i < inputLetters.length; i++){            s += inputLetters[i];        }        s = s.toLowerCase();        if (s.equals(word)){            youWin();            increaseNumberOfWins();            return true;        }        return false;    }    public void increaseNumberOfWins(){        numberOfWins ++;    }    public int getNumberOfWrongLetters(){        int i = this.numberOfWrongLetters;        if (i > 6){            updateHangman(i);        }        return numberOfWrongLetters;    }    public int getNumberOfWins(){        return this.numberOfWins;    }    public void updateInputString(String word, int pos, String letter){        TextView tv = (TextView) findViewById(R.id.input_word);        for (int i = 0; i < word.length(); i ++){            if (letterArray[i].equals(letter)){                inputLetters[i] = letter.toUpperCase();                System.out.println(Arrays.toString(inputLetters));            }        }        tv.setText("");        for (int i = 0; i < inputLetters.length; i ++){            tv.append(inputLetters[i]);        }        compareWords();    }    public static void updateHangman(int i){        switch (i) {            case 0:                //ingen grafikk                break;            case 1:                //galge vises                break;            case 2:                //hode vises                break;            case 3:                //kropp vises                break;            case 4:                //venstre arm                break;            case 5:                //høyre arm                break;            case 6:                //venstre fot                break;            case 7:                //høyre fot                break;            default:                //ingen grafikk        }//end of switch    }    public void resetGame(){        //reset the parameters you need for a new game        numberOfWrongLetters = 0;        Arrays.fill(inputLetters, "");        TextView tv = (TextView) findViewById(R.id.input_word);        tv.setText("");        ViewGroup row1 = (ViewGroup) findViewById(R.id.row1);        int count = row1.getChildCount();        for(int i = 0; i < count; i++) {            View childView = row1.getChildAt(i);            childView.setEnabled(true);        }        ViewGroup row2 = (ViewGroup) findViewById(R.id.row2);        int count2 = row2.getChildCount();        for(int i = 0; i < count2; i++) {            View childView = row2.getChildAt(i);            childView.setEnabled(true);        }        ViewGroup row3 = (ViewGroup) findViewById(R.id.row3);        int count3 = row3.getChildCount();        for(int i = 0; i < count3; i++) {            View childView = row3.getChildAt(i);            childView.setEnabled(true);        }        ViewGroup row4 = (ViewGroup) findViewById(R.id.row4);        int count4 = row4.getChildCount();        for(int i = 0; i < count4; i++) {            View childView = row4.getChildAt(i);            childView.setEnabled(true);        }        startNewGame();    }    public void gameOverToast(){        String text = getString(R.string.game_over);        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG ).show();    }    public void youWin(){        resetGame();        String text = getString(R.string.win);        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG ).show();    }    @Override    public void onBackPressed() {        Intent intent = new Intent(this, MainActivity.class);        startActivity(intent);    }} //end of class Hangman