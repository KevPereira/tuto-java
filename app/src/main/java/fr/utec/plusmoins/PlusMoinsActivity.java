package fr.utec.plusmoins;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlusMoinsActivity extends AppCompatActivity {

    private int nValue = 0;
    private int nBaseValue, nBaseStep, nBaseMin, nBaseMax;
    private Button oButtonMinus;
    private Button oButtonPlus;
    private TextView oEditTextValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_moins);
        // Restore preferences
        SharedPreferences settings = getPreferences(0);
        nBaseValue = settings.getInt("nBaseValue", 0);
        nBaseStep = settings.getInt("nBaseStep", 1);
        nBaseMin = settings.getInt("nBaseMin", -10);
        nBaseMax = settings.getInt("nBaseMax", 10);
        oButtonMinus = (Button) findViewById(R.id.buttonMinus);
        oButtonPlus = (Button) findViewById(R.id.buttonPlus);
        oEditTextValue = (TextView) findViewById(R.id.editTextValue);
        // Set base value
        oEditTextValue.setText("" + nBaseValue);
        oButtonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getTextValue();
                if ((nValue - nBaseStep) >= nBaseMin) {
                    nValue -= nBaseStep;
                    updateView();
                }
            }
        });
        oButtonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getTextValue();
                if ((nValue + nBaseStep) <= nBaseMax) {
                    nValue += nBaseStep;
                    updateView();
                }
            }
        });
    }

    private void getTextValue() {
        int nTempValue;
        if(oEditTextValue.getText().toString().matches("\\d+(?:\\.\\d+)?")) {
            nTempValue = new Integer(oEditTextValue.getText().toString());
            if(nTempValue >= nBaseMin && nTempValue <= nBaseMax) {
                nValue = nTempValue;
            } else {
                updateView();
            }
        }
    }

    private void updateView(){
        oEditTextValue.setText(new Integer(nValue).toString());
    }
}
