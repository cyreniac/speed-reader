package com.dkalsan.speedreader;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.dkalsan.speedreader.views.CustomView;

public class DisplayTemplateTextActivity extends AppCompatActivity {

    private String words[];
    private int ix = 0;
    private CustomView customView;

    private void alignPivotToCentre(String nextWord, int pivot) {
        Paint mPaint = new Paint();
        mPaint.setTextSize(customView.getTextSize());
        int width = customView.getMeasuredWidth();
        float charWidths[] = new float[20];
        mPaint.getTextWidths(nextWord, charWidths);
        float padding = 0f;

        for(int i = 0; i < pivot; i++) {
            padding = padding + charWidths[i];
        }
        padding = padding + charWidths[pivot] / 2;

        customView.setPadding((int)(width / 2 - padding), customView.getPaddingTop(),
                                    customView.getPaddingRight(), customView.getPaddingBottom());
    }

    private void colorFormatNextWord(String nextWord, int pivot) {
        customView.setText(nextWord, TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) customView.getText();
        s.setSpan(new ForegroundColorSpan(0xFFFF0000), pivot, pivot + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private int findPivot(String word) {
        switch(word.length()) {
            case 0:
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
                return 1;
            case 6:
            case 7:
            case 8:
            case 9:
                return 2;
            case 10:
            case 11:
            case 12:
            case 13:
                return 3;
            default:
                return 4;
        }
    }

    private void drawEndScreen() {
        customView.animate().alpha(0f).setDuration(1700);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 1700);
    }

    private void start() {
        final Handler handler = new Handler();
        final int delay = 400;

        customView.setVisibility(View.VISIBLE);
        customView.animate().alpha(1f).setDuration(1700);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if(ix < words.length) {
                            nextWord();
                            handler.postDelayed(this, delay);
                        } else {
                            drawEndScreen();
                        }
                    }
                }, delay);
            }
        }, 1700);

    }

    private void nextWord() {
        String nextWord = words[ix];
        ix++;

        int pivot = findPivot(nextWord);
        alignPivotToCentre(nextWord, pivot);
        colorFormatNextWord(nextWord, pivot);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_template_text);

        Intent intent = getIntent();
        String templateText = intent.getStringExtra(MainActivity.TEMPLATE_TEXT);

        words = templateText.split(" ");
        customView = (CustomView) findViewById(R.id.customView);
        start();
    }
}

//TODO: Adjust text size according to the longest word when starting the app