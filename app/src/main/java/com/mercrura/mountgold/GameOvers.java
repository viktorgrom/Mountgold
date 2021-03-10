package com.mercrura.mountgold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOvers extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        int score = getIntent().getExtras().getInt("score");
        textView = findViewById(R.id.tvScore);
        textView.setText(" "+ score);
    }

    public void restart(View view) {
        Intent intent = new Intent(GameOvers.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

