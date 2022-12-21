package com.example.myminesweeper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame {
    private MineGrid mineGrid;
    private boolean clearMode;
    private boolean flagMode;
    private boolean isGameOver;
    private boolean timeExpired;
    private int flagCount;
    private int numberOfBombs;
    
    public MinesweeperGame(int size, int numberOfBombs)  {
        this.clearMode = true;
        this.flagMode = false;
        this.isGameOver = false;
        this.timeExpired = false;
        this.flagCount = 0;
        this.numberOfBombs = numberOfBombs;

        mineGrid = new MineGrid(size);
        mineGrid.generateGrid(numberOfBombs);
    }

    public void handleCellClick(Cell cell){
        if (!isGameOver && !isGameWon() && !timeExpired) {
            if(clearMode) {
                clear(cell);
            } else if (flagMode) {
                flag(cell);
            }
        }

    }

    public void clear(Cell cell) {
        int index = getMineGrid().getCells().indexOf(cell);
        getMineGrid().getCells().get(index).setRevealed(true);

        if (cell.getValue() == Cell.BOMB) {
             isGameOver  = true;
        } else if (cell.getValue() == Cell.BLANK) {
            List<Cell> toClear = new ArrayList<>();
            List<Cell> toCheckAdjacents = new ArrayList<>();

            toCheckAdjacents.add(cell);

            while (toCheckAdjacents.size() > 0) {
                Cell c = toCheckAdjacents.get(0);
                int cellIndex = getMineGrid().getCells().indexOf(c);
                int[] cellPos = getMineGrid().toXY(cellIndex);
                for (Cell adjacent: getMineGrid().adjacentCells(cellPos[0], cellPos[1])) {
                    if (adjacent.getValue() == Cell.BLANK) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent)) {
                            toClear.add(adjacent);
                        }
                    }
                }
                toCheckAdjacents.remove(c);
                toClear.add(c);
            }

            for (Cell c: toClear) {
                c.setRevealed(true);
            }
        }
    }

    public boolean isGameWon() {
        int numbersUnrevealed = 0;
        for (Cell c: getMineGrid().getCells()) {
            if (c.getValue() != Cell.BOMB && c.getValue() != Cell.BLANK && !c.isRevealed()) {
                numbersUnrevealed++;
            }
        }

        if (numbersUnrevealed == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void toggleMode() {
        clearMode = !clearMode;
        flagMode = !flagMode;
    }

    public void flag(Cell cell) {

            cell.setFlagged(!cell.isFlagged());
            int count = 0;
            for (Cell c: getMineGrid().getCells()) {
                if (c.isFlagged()) {
                    count++;
                }
            }
            flagCount = count;


    }


    public void outOfTime() {
        timeExpired = true;
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isFlagMode() {
        return flagMode;
    }

    public boolean isClearMode() {
        return clearMode;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }
}
