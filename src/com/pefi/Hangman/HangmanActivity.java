package com.pefi.Hangman;import android.app.Activity;import android.app.AlertDialog;import android.content.DialogInterface;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.widget.ImageView;import android.widget.TextView;import java.util.ArrayList;import java.util.Arrays;import java.util.Random;/** * Created by pererikfinstad on 26/08/14. */public class HangmanActivity extends Activity implements KeyboardFragment.OnItemSelectedListener{    private final static String TAG = "HangmanActivity";    private String  selectedWord;    private int                numberOfWrongLetters = 0,                numberOfWins         = 0,                numberOfGames        = 0,                nextArrayIndex       = 0;    private String[]                wordList,                letterArray,                inputLetters,                usedWords;    private String word;    private ArrayList<?> touchables;    @Override    public void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        this.requestWindowFeature(Window.FEATURE_NO_TITLE);        setContentView(R.layout.hangman);        //get the list of words        wordList = getResources().getStringArray(R.array.word_array);        usedWords = new String[wordList.length];        startNewGame();    }    /**     * Interface for getting data from KeyboardFragment     *     * @param s (string) the letter typed in by the user from the keyboard fragment     */    @Override    public void onLetterSelected(String s) {        // Capture the article fragment from the activity layout        checkLetter(s);    }    /**     *  Starts a new game when the activity loads     */    public void startNewGame(){        //pick a random word an put in in a char array        word = randomWord();        //split the word up into an array        buildWordArray(word);        //Letters selected by the user        inputLetters = new String[word.length()];        TextView tv = (TextView) findViewById(R.id.input_word);        //puts correct number of letters into an array        for (int i = 0; i < inputLetters.length; i ++){            inputLetters[i] = "_ ";            tv.append(inputLetters[i]);        }        // FOR DEBUG ONLY        System.out.println("Ordet som ble plukket ut er: " + Arrays.toString(letterArray));    }    /**     *  Selects a random word from the words.xml file (array)     *  The method also checks if the word has already been used. If not     *  the word is returned, and the "usedWords" variable is updated     *     * @return  the selected word     */    public String randomWord(){        Random rand = new Random();        //pick a random number        int randomInt = rand.nextInt(wordList.length);        String word = wordList[randomInt];        if (checkUsedWords(word, usedWords)){            return randomWord();        }        else {            //Updates the array of used words            setUsedWords(word);            System2.out.println("Brukte ord: " + Arrays.toString(usedWords));        }        return word;    }    /**     * Tests if a word is already used. If it is, it will be in the "usedWords" array     *     * @param s     (String) the word to be checked     * @param a     (String[]) the array the word should be checked     * @return      (boolean) if the word is in the array return true     */    public static boolean checkUsedWords(String s, String[] a){        System.out.println("Ord som sammenlignes er " + s + " og " + Arrays.toString(a));        for (int i = 0; i < a.length; i ++){            if (s.equals(a[i])){                System.out.println("fant ordet");                return true;            }        }        System.out.println("fant ikke ordet");        return false;    }    /**     * Updates the (array) variable containing the already used word     * and assigns the next empty index in the array to the variable "nextArrayIndex"     *     * @param s (int) the next empty index in the "usedWords" array     */    public void setUsedWords(String s){        usedWords[nextArrayIndex] = s;        nextArrayIndex++;    }    /**     * Builds an array of the randomly selected word to give each letter a position     *     * @param s String of the randomly selected word     */    public void buildWordArray(String s){        letterArray = s.split("(?!^)");    }    /**     * Returns the words already used in the game     *     * @return a string of used words     */    public String getUsedWords(){        String[] s = usedWords;        return Arrays.toString(s);    }    /**     * Finds the (array) index of a letter in the selected word     *     * @param  s The letter provided by the user     * @return index The position of the letter     */    public int getPosition(String s){        int index = Arrays.asList(letterArray).indexOf(s);        return index;    }    /**     *  Checks if the word contains the selected letter     *      * @param s the letter the user selects when guessing     */    public void checkLetter(String s){        //sjekk om bokstaven finner i ordet        if (word.contains(s)){            System.out.println("JEPPPPPPPP");            System.out.println("Posisjonen er " + getPosition(s));            updateInputLetters(word, getPosition(s), s);        }        else{            setWrongLetter();            System.out.println("NOOOOOOPE");        }    }    /**     * Increases the variable "numberOfWrongLetters and updates the Hangman drawing     *     * Tests if it is game over or not     */    public void setWrongLetter(){        numberOfWrongLetters++;        System.out.println("Antall feil bokstaver tastet inn er: " + numberOfWrongLetters);        if (numberOfWrongLetters > 6) {            youLose();        }        updateHangman(numberOfWrongLetters);    }    /**     * Compares the randomly selected word and the word the user has typed in     *     * @return returns true if the typed in word matches the randomly selected words     */    public boolean compareWords(){        String s = new String();        for (int i = 0; i < inputLetters.length; i++){            s += inputLetters[i];        }        s = s.toLowerCase();        if (s.equals(word)){            youWin();            increaseNumberOfWins();            return true;        }        return false;    }    /**     * updates the "numberOfWins" by 1 for every time it is calleds     */    public void increaseNumberOfWins(){        numberOfWins ++;    }    /**     * Gets the number of wrong guessed letter. Works also as the test if the hangman is complete     *     * @return the actual number of wrong guessed letters     */    public int getNumberOfWrongLetters(){        int i = this.numberOfWrongLetters;        if (i > 6){            updateHangman(i);        }        return numberOfWrongLetters;    }    /**     * Returns the number of wins/correct words guessed     *     * @return i    the number in the variable "numberOfWins"     */    public int getNumberOfWins(){        int i = this.numberOfWins;        return i;    }    /**     * Updates the the string array "inputLetters" which keeps track of the     * correct letters typed by the user.     *     * @param word      the randomly selected word the user is supposed to guess     * @param pos       the (int) position in the word where the correct guessed letter should go     * @param letter    the actual letter the user types     */    public void updateInputLetters(String word, int pos, String letter){        TextView tv = (TextView) findViewById(R.id.input_word);        for (int i = 0; i < word.length(); i ++){            if (letterArray[i].equals(letter)){                inputLetters[i] = letter.toUpperCase();                System.out.println(Arrays.toString(inputLetters));            }        }        tv.setText("");        for (int i = 0; i < inputLetters.length; i ++){            tv.append(inputLetters[i]);        }        compareWords();    }    /**     * Updates the hangman image for every wrong guessed letter     *     * @param i     the actual number of wrong guessed letters so     *              far taken from the "numberOfWrongLetters" variable     */    public void updateHangman(int i){        ImageView hangman = (ImageView) findViewById(R.id.hangman_image);        switch (i) {            case 0:                hangman.setImageResource(R.drawable.hangman);                break;            case 1:                hangman.setImageResource(R.drawable.hangman1);                break;            case 2:                hangman.setImageResource(R.drawable.hangman2);                break;            case 3:                hangman.setImageResource(R.drawable.hangman3);                break;            case 4:                hangman.setImageResource(R.drawable.hangman4);                break;            case 5:                hangman.setImageResource(R.drawable.hangman5);                break;            case 6:                hangman.setImageResource(R.drawable.hangman6);                break;            case 7:                hangman.setImageResource(R.drawable.hangman7);                break;            default:                //ingen grafikk        }//end of switch    }    /**     * For each words, some of the parameters in the game needs to be reset, such as     * the the counter for wrong letters and the typed in letters. The disabled     * buttons needs to be enabled for the next word     */    /*    *    *           DENNE METODEN BØR SKRIVES OM TIL Å BLI DYNAMISK    * */     public void resetGame(){        //reset the parameters you need for a new game        numberOfWrongLetters = 0;        Arrays.fill(inputLetters, "");        TextView tv = (TextView) findViewById(R.id.input_word);        tv.setText("");        ViewGroup row1 = (ViewGroup) findViewById(R.id.row1);        int count = row1.getChildCount();        for(int i = 0; i < count; i++) {            View childView = row1.getChildAt(i);            childView.setEnabled(true);        }        ViewGroup row2 = (ViewGroup) findViewById(R.id.row2);        int count2 = row2.getChildCount();        for(int i = 0; i < count2; i++) {            View childView = row2.getChildAt(i);            childView.setEnabled(true);        }        ViewGroup row3 = (ViewGroup) findViewById(R.id.row3);        int count3 = row3.getChildCount();        for(int i = 0; i < count3; i++) {            View childView = row3.getChildAt(i);            childView.setEnabled(true);        }        ViewGroup row4 = (ViewGroup) findViewById(R.id.row4);        int count4 = row4.getChildCount();        for(int i = 0; i < count4; i++) {            View childView = row4.getChildAt(i);            childView.setEnabled(true);        }        int orientation = this.getResources().getConfiguration().orientation;        System.out.println(orientation);        //Fix for landscapemode because it has more rows than orientation mode        if (orientation == 2){            ViewGroup row5 = (ViewGroup) findViewById(R.id.row5);            int count5 = row5.getChildCount();            for(int i = 0; i < count5; i++) {                View childView = row5.getChildAt(i);                childView.setEnabled(true);            }            ViewGroup row6 = (ViewGroup) findViewById(R.id.row6);            int count6 = row6.getChildCount();            for(int i = 0; i < count6; i++) {                View childView = row6.getChildAt(i);                childView.setEnabled(true);            }        }        startNewGame();    }    /**     *  Presents an alert dialog to the user with the correct word,     *  and the a button next to keep playing. Resets the game engine     *  and clears the hangman drawing     */    public void youLose(){        new AlertDialog.Builder(this)                .setTitle(R.string.lose)                .setMessage("Word: " + word.toUpperCase())                .setPositiveButton(R.string.new_game, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        resetGame();                        updateHangman(0);                    }                })                .show();    }    /**     *  Presents an alert dialog to the user with the correct word,     *  and the a button next to keep playing. Resets the game engine     *  and clears the hangman drawing     */    public void youWin(){        new AlertDialog.Builder(this)                .setTitle(word.toUpperCase() + " is correct!")                .setPositiveButton(R.string.next_word, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        resetGame();                        updateHangman(0);                    }                }).show();    }    /**     * Presents an alert dialog to the user when there are noe more unique     * words left to guess. The user can choose whether he/she want to play again     * or stop     */    public void noMoreWords(){        new AlertDialog.Builder(this)                .setTitle(R.string.congrats)                .setMessage(R.string.no_more_words)                .setMessage(R.string.play_again)                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        resetGame();                        updateHangman(0);                    }                })                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        onBackPressed();                    }                })                .show();    }    /**     * Takes you back to the MainActivity (starting page)     */    @Override    public void onBackPressed() {        Intent intent = new Intent(this, MainActivity.class);        startActivity(intent);    }} //end of class Hangman