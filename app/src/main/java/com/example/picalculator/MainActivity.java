package com.example.picalculator;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements  TextView.OnEditorActionListener, View.OnKeyListener{
    private EditText userInput;
    private TextView piDigits;
    private String inputString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = (EditText) findViewById(R.id.inputEditText);
        piDigits = (TextView) findViewById(R.id.piTextView);
        userInput.setOnKeyListener(this);
        userInput.setOnEditorActionListener(this);
    }
//create pi calc object to call fuinction from
    public void calculateAndDisplay() {
        inputString = userInput.getText().toString();
        int input;
        double x = 50.10;
        BigDecimal output;
        piCalculator piObject = new piCalculator();


        if (inputString.equals("")) {
            input = 2;
        } else {
            input = parseInt(inputString);
        }
        output = piObject.pi(input);
        piDigits.setText(String.valueOf(output));
        }



        @Override
        public boolean onKey (View view,int keyCode, KeyEvent keyEvent){
            switch (keyCode) {
                case KeyEvent.KEYCODE_ENTER:
                case KeyEvent.KEYCODE_DPAD_CENTER:

                    calculateAndDisplay();
                    //hide soft keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            userInput.getWindowToken(), 0);
                    //consume event
                    return true;

            }
            return false;
        }






        @Override
        public boolean onEditorAction (TextView textView,int i, KeyEvent keyEvent){
            if (i == EditorInfo.IME_ACTION_DONE ||
                    i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                calculateAndDisplay();

            }
            return false;
        }

}