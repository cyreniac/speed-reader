package com.dkalsan.speedreader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TEMPLATE_TEXT = "com.dkalsan.speedreader.TEMPLATE_TEXT";

    public void sendTemplate1(View view) {
        String text = getResources().getString(R.string.templateText1);
        sendTemplate(text);
    }

    public void sendTemplate2(View view) {
        String text = getResources().getString(R.string.templateText2);
        sendTemplate(text);
    }

    public void sendCustomTemplate(View view) {
        Button customTemplateButton = (Button) findViewById(R.id.customTemplateButton);
        final EditText customEditText = (EditText) findViewById(R.id.customEditText);

        customTemplateButton.setText(R.string.confirmation);
        customTemplateButton.animate().translationYBy(customTemplateButton.getHeight()).setDuration(500);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(customEditText, InputMethodManager.SHOW_IMPLICIT);

        customTemplateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTemplate(customEditText.getText().toString());
            }
        });
    }

    public void sendTemplate(String text) {
        if(text.isEmpty()) {
            Toast.makeText(this, R.string.blankTextWarning, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, DisplayTemplateTextActivity.class);
            intent.putExtra(TEMPLATE_TEXT, text);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}