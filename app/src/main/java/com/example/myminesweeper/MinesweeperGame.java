package com.example.myminesweeper;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame {
    private MineGrid mineGrid;
    private boolean clearMode;
    private boolean isGameOver;
    
    public MinesweeperGame(int size, int numberOfBombs)  {
        this.clearMode = true;
        this.isGameOver = false;

        mineGrid = new MineGrid(size);
        mineGrid.generateGrid(numberOfBombs);
    }

    public void handleCellClick(Cell cell){
        if (!isGameOver) {
            if(clearMode) {
                clear(cell);
            }
        }

    }

    public void clear(Cell cell) {
        int index = getMineGrid().getCells().indexOf(cell);
        getMineGrid().getCells().get(index).setRevealed(true);

        if (cell.getValue() == Cell.BOMB) {
            boolean gameOver = true;
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
        } else if (cell.getValue() == Cell.BOMB) {
            isGameOver = true;
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


    public boolean isGameOver() {
        return isGameOver;
    }

    public MineGrid getMineGrid() {

        return mineGrid;
    }
}
