package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(view -> {
            if (getString(R.string.display).equals(display.getText().toString())){
               display.setText("");
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }

        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    public void clearBTN(View view) {
        display.setText("");
    }

    public void parenthesesBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().charAt(i) == '(') {
                openPar += 1;
            }
            if (display.getText().toString().charAt(i) == ')') {
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().charAt(textLen - 1) == '('){
            updateText("(");
        }
        if (closedPar < openPar && display.getText().toString().charAt(textLen - 1) != ')'){
            updateText(")");
        }
        display.setSelection((cursorPos + 1));
    }

    public void modBTN(View view) {
        updateText("%");
    }

    public void divideBTN(View view) {
        updateText("÷");
    }

    public void btn7(View view) {
        updateText("7");
    }

    public void btn8(View view) {
        updateText("8");
    }

    public void btn9(View view) {
        updateText("9");
    }

    public void multiplyBTN(View view) {
        updateText("×");
    }

    public void btn4(View view) {
        updateText("4");
    }

    public void btn5(View view) {
        updateText("5");
    }

    public void btn6(View view) {
        updateText("6");
    }

    public void subtractBTN(View view) {
        updateText("-");
    }

    public void btn1(View view) {
        updateText("1");
    }

    public void btn2(View view) {
        updateText("2");
    }

    public void btn3(View view) {
        updateText("3");
    }

    public void addBTN(View view) {
        updateText("+");
    }

    public void minus_plusBTN(View view) {
        updateText("-");
    }

    public void btn0(View view) {
        updateText("0");
    }

    public void pointBTN(View view) {
        updateText(".");
    }

    public void equalBTN(View view) {
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }

    public void cencelBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos -1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);

        }
    }
}