package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board {
    Piece pieces[][];
    BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[Board.NUM_OF_COLS][Board.NUM_OF_ROWS];

        for (int i = 0 ; i < NUM_OF_COLS ; i++ ){
            for (int j = 0 ; j < NUM_OF_ROWS ; j++ ){
                pieces[i][j] = Piece.EMPTY;
            }
        }

    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0 ; i < NUM_OF_ROWS ; i++ ){
            if (pieces[col][i].equals(Piece.EMPTY)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        if (findNextAvailableSpot(col) == -1){
            return false;
        }
        return true;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0 ; i < NUM_OF_COLS ; i++ ){
            for (int j = 0 ; j < NUM_OF_ROWS ; j++ ){
                if (pieces[i][j].equals(Piece.EMPTY)){
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        L1:for (int i = 0; i < NUM_OF_COLS; i++) {
            int count = 0;

            L2:for (int j = 0; j < (NUM_OF_ROWS - 1); j++) {
                if (pieces[i][j].equals(pieces[i][j + 1]) && !pieces[i][j].equals(Piece.EMPTY)) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 3) {
                    return new Winner(pieces[i][j], i, i, (j - 2), (j+1));
                }

            }
        }

        L3:for (int i = 0; i < NUM_OF_ROWS; i++) {
            int count = 0;

            L4:for (int j = 0; j < (NUM_OF_COLS - 1); j++) {
                if (pieces[j][i].equals(pieces[j+1][i]) && !pieces[j][i].equals(Piece.EMPTY)) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 3) {
                    return new Winner(pieces[j][i], (j - 2), (j+1), i, i);
                }

            }
        }
        return new Winner(Piece.EMPTY);
    }

}
