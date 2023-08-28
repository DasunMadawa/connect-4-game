package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Board newBoard) {
        super(newBoard);
    }
    @Override
    public void movePiece(int indexOf) {
        if(board.isLegalMove(indexOf)){
            board.updateMove(indexOf , Piece.BLUE);
            board.getBoardUI().update(indexOf , true);

            Winner winner = board.findWinner();
            System.out.println(winner.getWinningPiece());
            if (!winner.getWinningPiece().equals(Piece.EMPTY)){
                board.getBoardUI().notifyWinner(winner);
            }else if (!board.existLegalMoves()){
                board.getBoardUI().notifyWinner(winner);
            }

        }
    }




}
