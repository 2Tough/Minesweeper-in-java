package com.example.myminesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView smiley;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smiley = findViewById(R.id.activity_main_grid);
        smiley.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                game = new MinesweeperGame(10, 10);
                mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            }
        });

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        game = new MinesweeperGame(10, 10);
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(), this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
    }

    @Override
    public void onCellClick(Cell cell) {
        game.handleCellClick(cell);

        if(game.isGameOver()) {
            Toast.makeText(getApplicationContext(),"Game is Over", Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());


    }

    @Override
    public void cellClick(Cell cell) {

    }
}