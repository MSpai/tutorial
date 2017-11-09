package com.example.mpipini.tutorial.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mpipini.tutorial.R;

/**
 * Created by Maria Spai on 09/11/2017.
 */
public class SecondActivity extends AppCompatActivity {

    private TextView title;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_view);

        title = (TextView) findViewById(R.id.textViewS);
        if(getIntent().getStringExtra("name") != null)
            title.setText(getIntent().getStringExtra("name"));
        button = (Button) findViewById(R.id.buttonS);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog(R.style.DialogAnimation_2, "Up - Down Animation!");
            }
        });
    }

    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Animation Dialog");
        builder.setMessage(type);
        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }
}
