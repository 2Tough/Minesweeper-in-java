package com.example.myminesweeper;

public class MinesweeperGame {
    private MineGrid mineGrid;

    public MinesweeperGame(int size)  {
        mineGrid = new MineGrid(size);
    }
    public MineGrid getMineGrid() {
        return mineGrid;
    }
}
