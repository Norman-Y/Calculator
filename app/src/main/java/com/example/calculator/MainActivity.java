package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取按钮
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMulti = findViewById(R.id.btnMulti);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnDot = findViewById(R.id.btnDot);
        Button btnEqual = findViewById(R.id.btnEqual);
        //获取文字框
        final TextView rcdWindow = findViewById(R.id.rcdWindow);
        final TextView inputWindow = findViewById(R.id.inputWindow);
        //输入数字
        final ArgumentSheet Caculator = new ArgumentSheet();

        btn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '0', inputWindow, rcdWindow);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '1', inputWindow, rcdWindow);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '2', inputWindow, rcdWindow);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '3', inputWindow, rcdWindow);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '4', inputWindow, rcdWindow);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '5', inputWindow, rcdWindow);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '6', inputWindow, rcdWindow);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '7', inputWindow, rcdWindow);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '8', inputWindow, rcdWindow);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '9', inputWindow, rcdWindow);
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '.', inputWindow, rcdWindow);
            }
        });
        //输入运算符
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '+', inputWindow, rcdWindow);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '-', inputWindow, rcdWindow);
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '*', inputWindow, rcdWindow);
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '/', inputWindow, rcdWindow);
            }
        });
        //输出结果
        btnEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateCacl(Caculator, '=', inputWindow, rcdWindow);
            }
        });
    }

    protected void refreshCacl(String[] cacl, TextView inputWindow) {
        inputWindow.setText(cacl[0]);
    }

    protected void refreshStrList(List<String> list, TextView rcdWindow) {
        ListIterator<String> ans = list.listIterator();
        String nowString = "";
        while (ans.hasNext()) {
            nowString += ans.next();
            nowString += '\n';
        }
        nowString = nowString.substring(0, nowString.length() - 1);
        rcdWindow.setText(nowString);
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public void generateCacl(ArgumentSheet a, char input, TextView inputWindow, TextView rcdWindow) {
        int caclEnd = a.cacl[0].length() - 1;
        if (input != '=') {
            //生成规则对应表
            Character[][] _cacl, _num1, _num2, _op;
            if (!a.Flag[0]) {
                _cacl = new Character[][]{
                        /*            非0数  .   0   运算符*/
                        /*非0数*/    {'+', '+', '+', '+'},
                        /*  .  */    {'+', 'x', '+', 'x'},
                        /*  0  */    {'x', '+', 'x', '+'},
                        /*  空 */    {'+', '0', '+', 'i'}};
                _num1 = new Character[][]{
                        {'+', '+', '+', 'i'},
                        {'+', 'x', '+', 'i'},
                        {'x', '+', 'x', 'i'},
                        {'+', '0', '+', 'i'}};
                _num2 = new Character[][]{
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'}};
                _op = new Character[][]{
                        {'i', 'i', 'i', '+'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', '+'},
                        {'i', 'i', 'i', 'i'}};
            } else {
                _cacl = new Character[][]{
                        /*              非0数 .   0   运算符*/
                        /*非0数*/       {'+', '+', '+', 'i'},
                        /*  .  */       {'+', 'x', '+', 'i'},
                        /*  0 */        {'x', '+', 'x', 'i'},
                        /*运算符*/      {'+', '0', '+', 'x'}};
                _num1 = new Character[][]{
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'}};
                _num2 = new Character[][]{
                        {'+', '+', '+', 'i'},
                        {'+', 'x', '+', '0'},
                        {'x', '+', 'x', 'i'},
                        {'+', '0', '+', 'i'}};
                _op = new Character[][]{
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'i'},
                        {'i', 'i', 'i', 'x'}};
            }
            //小数点已经使用时对规则表的修改
            if (a.Flag[1]) {
                for (int i = 0; i < 4; i++) {
                    _cacl[i][1] = 'i';
                    _num1[i][1] = 'i';
                }
            }
            //根据前面的数值和输入的数值查找要进行的动作
            int x = -1, y = -1;
            if (!a.Flag[0]) {
                if (a.cacl[0].isEmpty()) {
                    y = 3;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                } else if (a.cacl[0].charAt(caclEnd) == '.') {
                    y = 1;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                } else if (a.cacl[0].charAt(caclEnd) == '0') {
                    if (a.numString[0].contentEquals("0"))
                        y = 2;
                    else
                        y = 0;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                } else {
                    y = 0;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                }
                modifyChar(a.opChar, a.Flag, a.cacl, _op[y][x], input);
                modifyStr(a.cacl, 0, a.Flag, _cacl[y][x], input);
                modifyStr(a.numString, 0, a.Flag, _num1[y][x], input);
                modifyStr(a.numString, 1, a.Flag, _num2[y][x], input);
            } else {
                if (a.cacl[0].charAt(caclEnd) == '.') {
                    y = 1;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                } else if (a.cacl[0].charAt(caclEnd) == '0') {
                    if (a.numString[1].contentEquals("0"))
                        y = 2;
                    else
                        y = 0;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                } else if (Character.isDigit(a.cacl[0].charAt(caclEnd))) {
                    y = 0;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                } else {
                    y = 3;
                    if (Character.isDigit(input) & input != '0')
                        x = 0;
                    else if (input == '.') {
                        x = 1;
                        a.Flag[1] = true;
                    } else if (input == '0')
                        x = 2;
                    else
                        x = 3;
                }
                modifyChar(a.opChar, a.Flag, a.cacl, _op[y][x], input);
                modifyStr(a.cacl, 0, a.Flag, _cacl[y][x], input);
                modifyStr(a.numString, 0, a.Flag, _num1[y][x], input);
                modifyStr(a.numString, 1, a.Flag, _num2[y][x], input);
            }
            //刷新输入框
            refreshCacl(a.cacl, inputWindow);
        } else {
            if (a.cacl[0].matches("[0-9]+.?[0-9]*[\\+\\-\\*\\/][0-9]+.?[0-9]*")) {
                a.ansSheet.add(a.cacl[0]);
                Double num1 = Double.parseDouble(a.numString[0]);
                Double num2 = Double.parseDouble(a.numString[1]);
                double ans = 0;
                switch (a.opChar[0]) {
                    case '+':
                        ans = num1 + num2;
                        break;
                    case '-':
                        ans = num1 - num2;
                        break;
                    case '*':
                        ans = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            ans = num1 / num2;
                            break;
                        }
                }
                if (num2 == 0) {
                    a.ansSheet.add("Divisor cannot be zero!");
                    a = new ArgumentSheet();
                } else {
                    a.numString[0] = Double.toString(ans);
                    a.numString[1] = "";
                    if (((long) ans + 0.0) != ans) {
                        a.cacl[0] = Double.toString(ans);
                        a.Flag[1] = true;
                        a.ansSheet.add(Double.toString(ans));
                    } else {
                        a.cacl[0] = Long.toString((long) ans);
                        a.Flag[1] = false;
                        a.ansSheet.add(Long.toString((long) ans));
                    }
                    a.Flag[0] = false;
                }
                refreshCacl(a.cacl, inputWindow);
                refreshStrList(a.ansSheet, rcdWindow);
            }
        }
    }

    public void modifyStr(String[] str, int i, Boolean[] flag, Character check, char input) {
        int strEnd = str[i].length() - 1;
        switch (check) {
            case '+':
                str[i] += input;
                break;
            case 'x':
                //小数点后输入运算符的修正
                if (str[i].charAt(strEnd) == '.') {
                    flag[0] = true;
                    flag[1] = false;
                }
                str[i] = removeCharAt(str[i], strEnd);
                str[i] += input;
                break;
            case '0':
                str[i] += '0';
                str[i] += input;
                break;
        }
    }

    public void modifyChar(char[] chr, Boolean[] flag, String[] cacl, Character check, char input) {
        int caclEnd = cacl[0].length() - 1;
        switch (check) {
            case 'x':
                flag[0] = true;
                //小数点修正
                flag[1] = false;
                chr[0] = input;
                cacl[0] = removeCharAt(cacl[0], caclEnd);
                cacl[0] += input;
                break;
            case '+':
                flag[0] = true;
                //小数点修正
                flag[1] = false;
                chr[0] = input;
                break;
        }
    }
}

class ArgumentSheet {
    final String[] numString = {"", ""};
    final char[] opChar = {'\0'};
    final List<String> ansSheet = new ArrayList<>();
    final String[] cacl = {""};
    final Boolean[] Flag = {false, false};//第一个bool值为运算符标志，第二个为小数点标志
}