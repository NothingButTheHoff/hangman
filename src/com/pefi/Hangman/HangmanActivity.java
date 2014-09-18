package com.pefi.Hangman;import android.app.Activity;import android.app.AlertDialog;import android.content.DialogInterface;import android.content.Intent;import android.content.res.Configuration;import android.graphics.Typeface;import android.os.Bundle;import android.util.Log;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.widget.Button;import android.widget.ImageView;import android.widget.TextView;import java.util.Arrays;import java.util.Locale;import java.util.Random;/** * Created by pererikfinstad on 26/08/14. */public class HangmanActivity extends Activity implements KeyboardFragment.OnItemSelectedListener{    private final static String TAG = "HangmanActivity";    //counters    private int numberOfWrongLetters = 0;    private int totalUsedLetters     = 0;    private int gamesWon             = 0;    private int gamesLost            = 0;    private int nextArrayIndex       = 0;    // game variables    private String[] wordList;    private String[] letterArray;    private String[] inputLetters;    private String[] usedWords;    private String[] usedLetters = new String[29]; //used for disabling buttons on orientation change    private String word;    private String lang;    private int cat;    //UI    private TextView lost;    private TextView wins;    private TextView winsHead;    private TextView losesHead;    private TextView inputWord;    private TextView categoryHead;    private TextView category;    private Button button_back;    //Config    Configuration config;    Locale locale;    Intent intent;    @Override    public void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        //get intent        intent = getIntent();        //get the selected language from the intent        lang = intent.getStringExtra("lang");        if (lang != null){            locale = new Locale(lang);            config = new Configuration();            config.locale = locale; //assign variable to be sent with intent            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());        }        //get the selected category        cat = intent.getIntExtra("cat", 4);        Log.i(TAG, "Selected category: " + cat);        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //hides the default android titlebar        setContentView(R.layout.hangman);        //get typeface        Typeface font = Typeface.createFromAsset(getAssets(), "ComingSoon.ttf");        //get UI elements        lost            = (TextView) findViewById(R.id.noOfLost);        wins            = (TextView) findViewById(R.id.noOfWins);        winsHead        = (TextView) findViewById(R.id.noOfWinsHead);        losesHead       = (TextView) findViewById(R.id.noOfLostHead);        inputWord       = (TextView) findViewById(R.id.input_word);        categoryHead    = (TextView) findViewById(R.id.categoryHead);        category        = (TextView) findViewById(R.id.category);        //get categories and set correct text        String[] catg = getResources().getStringArray(R.array.category_array);        category.setText(catg[cat].toUpperCase());        button_back = (Button) findViewById(R.id.back2);        //assign typeface        lost.setTypeface(font);        wins.setTypeface(font);        inputWord.setTypeface(font);        winsHead.setTypeface(font);        losesHead.setTypeface(font);        button_back.setTypeface(font);        categoryHead.setTypeface(font);        category.setTypeface(font);        Log.i(TAG, "POSISJONEN ER: " + cat);        //check which category is selected        switch (cat){            case 0:                wordList = getResources().getStringArray(R.array.animals);                break;            case 1:                wordList = getResources().getStringArray(R.array.home);                break;            case 2:                wordList = getResources().getStringArray(R.array.countries);                break;            case 3:                wordList = getResources().getStringArray(R.array.fruits);                break;            case 4:                wordList = getResources().getStringArray(R.array.word_array);                break;            case 5:                wordList = getResources().getStringArray(R.array.word_array);                break;            default:                wordList = getResources().getStringArray(R.array.word_array);        }        //get the list of word        usedWords = new String[wordList.length];        //restores the saved data        if (savedInstanceState != null){            gamesWon = savedInstanceState.getInt("gamesWon");            wins.setText(Integer.toString(gamesWon));            gamesLost = savedInstanceState.getInt("gamesLost");            lost.setText(Integer.toString(gamesLost));            nextArrayIndex = savedInstanceState.getInt("nextIndex");            numberOfWrongLetters = savedInstanceState.getInt("numberOfWrongLetters");            word = savedInstanceState.getString("word");            letterArray = savedInstanceState.getStringArray("letterArray");            inputLetters = savedInstanceState.getStringArray("inputLetters");            usedWords = savedInstanceState.getStringArray("usedWords");            usedLetters = savedInstanceState.getStringArray("usedLetters");            totalUsedLetters = savedInstanceState.getInt("totalUsedLetters");            //disable buttons            for (int i = 0; i < usedLetters.length; i++){                if (usedLetters[i] != null){                    String name = usedLetters[i];                    System.out.println("Brukt: " + name);                    int resourceId = getResources().getIdentifier(name, "id", getPackageName());                    Button b = (Button)findViewById(resourceId);                    b.setEnabled(false);                }            }            //print the word            inputWord.setText("");            for (int i = 0; i < inputLetters.length; i ++){                inputWord.append(inputLetters[i]);            }            updateHangman(numberOfWrongLetters);        }        else {            startNewGame();        }        //debug     //   System.out.println("antall riktige ord:" + gamesWon);        Log.i(TAG, "Used letters is: " + Arrays.toString(usedLetters));        Log.i(TAG, "Total used letters is: " + Integer.toString(totalUsedLetters));    } //end onCreate()    /**     * Method for save the instance state on orientation change     *     * @param savedInstanceState    containing the data that needs to be stored     */    protected void onSaveInstanceState(Bundle savedInstanceState) {        super.onSaveInstanceState(savedInstanceState);        savedInstanceState.putInt("gamesWon", gamesWon);        savedInstanceState.putInt("gamesLost", gamesLost);        savedInstanceState.putInt("nextIndex", nextArrayIndex);        savedInstanceState.putInt("numberOfWrongLetters", numberOfWrongLetters);        savedInstanceState.putString("word", word);        savedInstanceState.putStringArray("letterArray", letterArray);        savedInstanceState.putStringArray("inputLetters", inputLetters);        savedInstanceState.putStringArray("usedWords", usedWords);        savedInstanceState.putStringArray("usedLetters", usedLetters);        savedInstanceState.putInt("totalUsedLetters", totalUsedLetters);    }    /**     * Interface for getting data from KeyboardFragment     *     * @param s (string) the letter typed in by the user from the keyboard fragment     */    @Override    public void onLetterSelected(String s, String id) {        usedLetters[totalUsedLetters] = id;        totalUsedLetters++;        checkLetter(s);    }    /**     *  Starts a new game when the activity loads     */    public void startNewGame(){        //pick a random word an put in in a char array        word = randomWord();        //split the word up into an array        buildWordArray(word);        //Letters selected by the user        inputLetters = new String[word.length()];        TextView tv = (TextView) findViewById(R.id.input_word);        //puts correct number of letters into an array        for (int i = 0; i < inputLetters.length; i ++){            inputLetters[i] = "_ ";            tv.append(inputLetters[i]);        }        // FOR DEBUG ONLY        System.out.println("Ordet som ble plukket ut er: " + Arrays.toString(letterArray));    }    /**     *  Selects a random word from the words.xml file (array)     *  The method also checks if the word has already been used. If not     *  the word is returned, and the "usedWords" variable is updated     *     * @return  the selected word     */    public String randomWord(){        Random rand = new Random();        //pick a random number        int randomInt = rand.nextInt(wordList.length);        String word = wordList[randomInt];        if (checkUsedWords(word, usedWords)){            return randomWord();        }        else {            //Updates the array of used words            setUsedWords(word);            System.out.println("Brukte ord: " + Arrays.toString(usedWords));        }        return word;    }    /**     * Tests if a word is already used. If it is, it will be in the "usedWords" array     *     * @param s     (String) the word to be checked     * @param a     (String[]) the array the word should be checked     * @return      (boolean) if the word is in the array return true     */    public static boolean checkUsedWords(String s, String[] a){        System.out.println("Ord som sammenlignes er " + s + " og " + Arrays.toString(a));        for (int i = 0; i < a.length; i ++){            if (s.equals(a[i])){                System.out.println("fant ordet");                return true;            }        }        System.out.println("fant ikke ordet");        return false;    }    /**     * Updates the (array) variable containing the already used word     * and assigns the next empty index in the array to the variable "nextArrayIndex"     *     * @param s (int) the next empty index in the "usedWords" array     */    public void setUsedWords(String s){        usedWords[nextArrayIndex] = s;        nextArrayIndex++;    }    /**     * Builds an array of the randomly selected word to give each letter a position     *     * @param s String of the randomly selected word     */    public void buildWordArray(String s){        letterArray = s.split("(?!^)");    }    /**     * Returns the words already used in the game     *     * @return a string of used words     */    public String getUsedWords(){        String[] s = usedWords;        return Arrays.toString(s);    }    /**     * Finds the (array) index of a letter in the selected word     *     * @param       s The letter provided by the user     * @return      index The position of the letter     */    public int getPosition(String s){        int index = Arrays.asList(letterArray).indexOf(s);        return index;    }    /**     *  Checks if the word contains the selected letter     *      * @param s    the letter the user selects when guessing     */    public void checkLetter(String s){        //sjekk om bokstaven finner i ordet        if (word.contains(s)){            System.out.println("JEPPPPPPPP");            System.out.println("Posisjonen er " + getPosition(s));            updateInputLetters(word, s);        }        else{            setWrongLetter();            System.out.println("NOOOOOOPE");        }    }    /**     * Increases the variable "numberOfWrongLetters and updates the Hangman drawing     *     * Tests if it is game over or not     */    public void setWrongLetter(){        numberOfWrongLetters++;        System.out.println("Antall feil bokstaver tastet inn er: " + numberOfWrongLetters);        if (numberOfWrongLetters > 5) {            youLose();        }        updateHangman(numberOfWrongLetters);    }    /**     * Compares the randomly selected word and the word the user has typed in     *     * @return      returns true if the typed in word matches the randomly selected words     */    public boolean compareWords(){        String s = new String();        for (int i = 0; i < inputLetters.length; i++){            s += inputLetters[i];        }        s = s.toLowerCase();        if (s.equals(word)){            youWin();            return true;        }        return false;    }    public void unsetUsedWords(String[] usedWords){        for (int i = 0; i < usedWords.length; i ++){            usedWords[i] = null;        }    }    /**     * updates the "gamesWon" by 1 for every time the function is calleds     */    public void updateGamesWon(){        gamesWon ++;    }    /**     * updates the "gamesLost" by 1 for every time the function is called     */    public void updateGamesLost(){        gamesLost ++;    }    /**     * Gets the number of wrong guessed letter. Works also as the test if the hangman is complete     *     * @return      the actual number of wrong guessed letters     */    public int getNumberOfWrongLetters(){        int i = this.numberOfWrongLetters;        if (i > 5){            updateHangman(i);        }        return numberOfWrongLetters;    }    /**     * Returns the number of wins/correct words guessed     *     * @return i    the number in the variable "gamesWon"     */    public int getgamesWon(){        int i = this.gamesWon;        return i;    }    /**     * Updates the the string array "inputLetters" which keeps track of the     * correct letters typed by the user.     *     * @param word      the randomly selected word the user is supposed to guess     * @param letter    the actual letter the user types     */    public void updateInputLetters(String word, String letter){        for (int i = 0; i < word.length(); i ++){            if (letterArray[i].equals(letter)){                inputLetters[i] = letter.toUpperCase();                System.out.println(Arrays.toString(inputLetters));            }        }        inputWord.setText("");        for (int i = 0; i < inputLetters.length; i ++){            inputWord.append(inputLetters[i]);        }        compareWords();    }    /**     * Updates the hangman image for every wrong guessed letter     *     * @param i     the actual number of wrong guessed letters so     *              far taken from the "numberOfWrongLetters" variable     */    public void updateHangman(int i){        ImageView hangman = (ImageView) findViewById(R.id.hangman_image);        switch (i) {            case 0:                hangman.setImageResource(R.drawable.hangmanblank);                break;            case 1:                hangman.setImageResource(R.drawable.hangman1);                break;            case 2:                hangman.setImageResource(R.drawable.hangman2);                break;            case 3:                hangman.setImageResource(R.drawable.hangman3);                break;            case 4:                hangman.setImageResource(R.drawable.hangman4);                break;            case 5:                hangman.setImageResource(R.drawable.hangman5);                break;            case 6:                hangman.setImageResource(R.drawable.hangman6);                break;            //case 7:            //    hangman.setImageResource(R.drawable.hangman7);            //    break;            default:                //no graphics        }//end of switch    }    /**     * For each words, some of the parameters in the game needs to be reset, such as     * the the counter for wrong letters and the typed in letters. The disabled     * buttons needs to be enabled for the next word     */       public void resetGame(){        if (nextArrayIndex == wordList.length){            noMoreWords();            gamesWon = 0;            gamesLost = 0;        }        else{            //reset the parameters you need for a new game            totalUsedLetters = 0;            numberOfWrongLetters = 0;            Arrays.fill(usedLetters, null);            Arrays.fill(inputLetters, "");            TextView tv = (TextView) findViewById(R.id.input_word);            tv.setText("");          //enables the disabled buttons            ViewGroup row1 = (ViewGroup) findViewById(R.id.row1);            int count = row1.getChildCount();            for(int i = 0; i < count; i++) {                View childView = row1.getChildAt(i);                childView.setEnabled(true);            }            ViewGroup row2 = (ViewGroup) findViewById(R.id.row2);            int count2 = row2.getChildCount();            for(int i = 0; i < count2; i++) {                View childView = row2.getChildAt(i);                childView.setEnabled(true);            }            ViewGroup row3 = (ViewGroup) findViewById(R.id.row3);            int count3 = row3.getChildCount();            for(int i = 0; i < count3; i++) {                View childView = row3.getChildAt(i);                childView.setEnabled(true);            }            ViewGroup row4 = (ViewGroup) findViewById(R.id.row4);            int count4 = row4.getChildCount();            for(int i = 0; i < count4; i++) {                View childView = row4.getChildAt(i);                childView.setEnabled(true);            }            int orientation = this.getResources().getConfiguration().orientation;            System.out.println(orientation);            //Fix for landscapemode because it has more rows than orientation mode            if (orientation == 2){                ViewGroup row5 = (ViewGroup) findViewById(R.id.row5);                int count5 = row5.getChildCount();                for(int i = 0; i < count5; i++) {                    View childView = row5.getChildAt(i);                    childView.setEnabled(true);                }                ViewGroup row6 = (ViewGroup) findViewById(R.id.row6);                if (row6 != null){                    int count6 = row6.getChildCount();                    for(int i = 0; i < count6; i++) {                        View childView = row6.getChildAt(i);                        childView.setEnabled(true);                    }                }            }            startNewGame();        }    }    /**     *  Presents an alert dialog to the user with the correct word,     *  and the a button next to keep playing. Resets the game engine     *  and clears the hangman drawing     */    public void youLose(){        new AlertDialog.Builder(this)                .setCancelable(false)                //.setTitle(R.string.lose)                .setMessage(getResources().getString(R.string.lose_msg) + " " + word.toUpperCase())                .setPositiveButton(R.string.next_word, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        updateGamesLost();                        lost.setText(Integer.toString(gamesLost));                        resetGame();                        updateHangman(0);                    }                }).show();    }    /**     *  Presents an alert dialog to the user with the correct word,     *  and the a button next to keep playing. Resets the game engine     *  and clears the hangman drawing     */    public void youWin(){        new AlertDialog.Builder(this)                .setCancelable(false)                //.setTitle(R.string.win)                .setMessage(word.toUpperCase() + " " + getResources().getString(R.string.win_msg))                .setPositiveButton(R.string.next_word, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        updateGamesWon();                        wins.setText(Integer.toString(gamesWon));                        resetGame();                        updateHangman(0);                    }                }).show();    }    /**     * Presents an alert dialog to the user when there are noe more unique     * words left to guess. The user can choose whether he/she want to play again     * or stop     */    public void noMoreWords(){        new AlertDialog.Builder(this)                .setCancelable(false)                .setTitle(R.string.no_more_words)                .setMessage(R.string.play_again)                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        nextArrayIndex = 0;                        unsetUsedWords(usedWords);                        lost.setText("");                        wins.setText("");                        resetGame();                        updateHangman(0);                    }                })                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {                    public void onClick(DialogInterface dialog, int which) {                        onBackPressed();                    }                }).show();    }    /**     * Button for taking the user back to the menu     */    public void goBack(View v){        onBackPressed();    }    /**     * Takes you back to the MainActivity (starting page)     */    @Override    public void onBackPressed() {        Intent intent = new Intent(this, MainActivity.class);        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);        intent.putExtra("lang", lang);        startActivity(intent);        finish();        //animated transitions        overridePendingTransition(R.anim.left_in, R.anim.right_out);    }} //end of class Hangman