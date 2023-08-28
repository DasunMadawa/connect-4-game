package lk.ijse.dep.service;

public class Player {
    protected Board board;


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    Player(Board board){
        this.board = board;
    }

    public void movePiece(int indexOf) {

    }

}
