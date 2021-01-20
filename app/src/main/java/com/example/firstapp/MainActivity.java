package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    Suffix suf;
    Evaluation ev;
    TextView Number, res;
    private ArrayList<String> k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number = (TextView) findViewById(R.id.textView6);
        res = (TextView) findViewById(R.id.textView5);

    }


    public boolean isNumber(String s) {
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || c == '.') {
                flag = true;
                break;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean isOperand(char s) {
        return s == '+' || s == '-' || s == '*' || s == '/';
    }

    public boolean isVariable(String s) {
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean check(char c, char last) {

        if ((isNumber(c + "") && isNumber(last + ""))) {
            return true;
        } else if ((isOperand(c) && isNumber(last + ""))) {
            return true;
        } else if ((isOperand(last) && isNumber(c + ""))) {
            return true;
        } else if (c == '(' && isOperand(last)) {
            return true;
        } else if (c == ')' && last != '(') {
            int co = 0, co2 = 0;
            for (char f : Number.getText().toString().toCharArray()) {
                if (f == '(') {
                    co++;
                }
                if (f == ')') {
                    co2++;
                }
            }
            return co > co2;
        } else if (isNumber(c + "") && last == '(') {
            return true;
        } else if (isOperand(c) && last == ')') {
            return true;
        } else {
            return false;
        }
    }

    public void btn(View v) {
        char s = v.getTag().toString().charAt(0);
        if (Number.getText().length() == 0) {
            Number.setText(Number.getText().toString() + s);
        } else {
            char lastChar = Number.getText().charAt(Number.getText().length() - 1);
            if (check(s, lastChar)) {
                Number.setText(Number.getText().toString() + s);
            }
        }
    }

    public void back(View v) {
        if (!Number.getText().equals("")) {
            Number.setText(Number.getText().toString().substring(0, Number.getText().length() - 1));
        }
    }

    public void clear(View v) {
        Number.setText("");
        res.setText("");
        suf = new Suffix();
        ev = new Evaluation();
    }

    public void result(View v) {
        int co = 0, co2 = 0;
        for (char c : Number.getText().toString().toCharArray()) {

            if (c == '(') {
                co++;
            }
            if (c == ')') {
                co2++;
            }
        }
        if (co == co2) {
            suf = new Suffix();
            ev = new Evaluation();
            suf.setInput(Number.getText().toString());

            k = new ArrayList<>();

            String[] t = suf.getInput().split("(?=[-+*/()])|(?<=[^-+*/][-+*/])|(?<=[()])");

            k.addAll(Arrays.asList(t));
            suf.s.push("#");
            suf.Suffix(k);
            res.setText(ev.Evaluatepostfix(suf.out, suf.k) + "");
        } else {
            res.setText("Error");
        }

    }
}
