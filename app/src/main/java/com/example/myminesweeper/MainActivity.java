package com.example.myminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCellClick(Cell cell) {
        Toast.makeText(getApplicationContext(), "Cell clicked", Toast.LENGTH_SHORT).show();
    }
}