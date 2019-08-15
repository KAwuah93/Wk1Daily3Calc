package com.example.wk1daily3calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private String TAG = "test";
    private ArrayList<Character> input = new ArrayList<>();
    private String totalAmt;

    private char[] operators ={ '+', '-', '/', '*'};

    //Views
    private TextView resultTextView;
    private TextView History;

    //Buttons to hide
    Button PI;
    Button INV;
    Button SQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.display);
        History = findViewById(R.id.hist);

        //
        PI = findViewById(R.id.PI);
        INV = findViewById(R.id.Inv);
        SQR = findViewById(R.id.Sqr);

        //check for landscape orientation
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            PI.setVisibility(View.VISIBLE);
            INV.setVisibility(View.VISIBLE);
            SQR.setVisibility(View.VISIBLE);
        } else {
            PI.setVisibility(View.GONE);
            INV.setVisibility(View.GONE);
            SQR.setVisibility(View.GONE);

        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        super.onConfigurationChanged(newConfig);

        int newOrientation = newConfig.orientation;

        if(newOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Do certain things when the user has switched to landscape.
            PI.setVisibility(View.VISIBLE);
            INV.setVisibility(View.VISIBLE);
            SQR.setVisibility(View.VISIBLE);
        } else {
            PI.setVisibility(View.GONE);
            INV.setVisibility(View.GONE);
            SQR.setVisibility(View.GONE);
        }


    }

    public void onClickNum(View view){
        switch (view.getId()){
            case R.id.PI:
                input.add('3');
                input.add('.');
                input.add('1');
                input.add('4');
                input.add('1');
                input.add('5');
                input.add('9');
                break;
            case R.id.Inv:
                Calc();
                Inv();
                update();
                //clear();
                input.add(0,'1');
                input.add(1,'/');
                break;
            case R.id.Sqr:
                Calc();
                Sqr();
                update();

            case R.id.AC:
                clear();
                Log.d(TAG, "onClickNum: for AC");
                break;
            case R.id.BCE:
                clear();
                Log.d(TAG, "onClickNum: for CE");
                break;
            case R.id.bDiv:
                input.add('/');
                Log.d(TAG, "onClickNum: for Div");
                break;
            case R.id.bMinus:
                input.add('-');
                Log.d(TAG, "onClickNum: for Minus");
                break;
            case R.id.bPlus:
                input.add('+');
                Log.d(TAG, "onClickNum: for Plus");
                break;
            case R.id.bPer:
                input.add('.');
                Log.d(TAG, "onClickNum: for Period");
                break;
            case R.id.b9:
                input.add('9');
                Log.d(TAG, "onClickNum: for b9");
                break;
            case R.id.b8:
                input.add('8');
                Log.d(TAG, "onClickNum: for b8");
                break;
            case R.id.b7:
                input.add('7');
                Log.d(TAG, "onClickNum: for b7");
                break;
            case R.id.b6:
                input.add('6');
                Log.d(TAG, "onClickNum: for b6");
                break;
            case R.id.b5:
                input.add('5');
                Log.d(TAG, "onClickNum: for b5");
                break;
            case R.id.b4:
                input.add('4');
                Log.d(TAG, "onClickNum: for b4");
                break;
            case R.id.b3:
                input.add('3');
                Log.d(TAG, "onClickNum: for b3");
                break;
            case R.id.b2:
                input.add('2');
                Log.d(TAG, "onClickNum: for b2");
                break;
            case R.id.b1:
                input.add('1');
                Log.d(TAG, "onClickNum: for b1");
                break;
            case R.id.b0:
                input.add('0');
                Log.d(TAG, "onClickNum: for b0");
                break;
            case R.id.bSum:
                Log.d(TAG, "onClickNum: for Sum");
                Calc();
                break;
            case R.id.bMulti:
                input.add('*');
                Log.d(TAG, "onClickNum: for Multi");
                break;
        }


        //we want to update the view after each button press
        update();


    }

    private void Sqr() {

        //String currentText = resultTextView.getText().toString();

        //flatten expression into string
        StringBuilder expression = new StringBuilder();
        for (Character s : input)
        {
            expression.append(s);
            expression.append("");
        }

        boolean valid = true;
        double result=0;
        if(valid){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            try{
                Log.d(TAG, "Calc: " + expression.toString());
                result = (double)engine.eval(expression.toString()) * (double)engine.eval(expression.toString());
            }catch(Exception e){
                Toast.makeText(this,"Please Check your formating!",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Calc: " + expression.toString());
            }
            //currentText = currentText +"\n"+ "="+(result);
        }
        totalAmt = Double.toString(result);

        //History = input.toString();
        resultTextView.setText(totalAmt);
    }

    private void Inv() {

        //String currentText = resultTextView.getText().toString();

        //flatten expression into string
        StringBuilder expression = new StringBuilder();
        for (Character s : input)
        {
            expression.append(s);
            expression.append("");
        }

        boolean valid = true;
        double result=0;
        if(valid){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            try{
                Log.d(TAG, "Calc: " + expression.toString());
                result = 1/(double)engine.eval(expression.toString());
            }catch(Exception e){
                Toast.makeText(this,"Please Check your formating!",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Calc: " + expression.toString());
            }
            //currentText = currentText +"\n"+ "="+(result);
        }
        totalAmt = Double.toString(result);

        //History = input.toString();
        resultTextView.setText(totalAmt);
    }

    public void testClk(){
        Log.d(TAG, "onClickNum: lmao");
    }

    public void Calc(){


        //String currentText = resultTextView.getText().toString();

        //flatten expression into string
        StringBuilder expression = new StringBuilder();
        for (Character s : input)
        {
            expression.append(s);
            expression.append("");
        }

        boolean valid = true;
        double result=0;
        if(valid){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            try{
                Log.d(TAG, "Calc: " + expression.toString());
                result = (double)engine.eval(expression.toString());
            }catch(Exception e){
                Toast.makeText(this,"Please Check your formating!",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Calc: " + expression.toString());
            }
            //currentText = currentText +"\n"+ "="+(result);
        }
        totalAmt = Double.toString(result);

        //History = input.toString();
        resultTextView.setText(totalAmt);

    }


    //visual modification coding
    public void clear(){
        input.clear();
    }

    //deletes the last entry by testing to see if it even has a length first and then removes last
    //if it does. similar to a bit of a homebrewed 'pop'
    public void bckspc(){
        if( input.size() > 0 )
            input.remove( input.size() - 1 );
    }

    public void update(){
        StringBuilder sb = new StringBuilder();
        for (Character s : input)
        {
            sb.append(s);
            sb.append("\t");
        }

        History.setText(sb);

    }
    //This is for the Visual
    public void updateCalc(){

    }


}
