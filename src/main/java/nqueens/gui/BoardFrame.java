package nqueens.gui;

import nqueens.domain.Board;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class BoardFrame extends JFrame {

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private Dimension boardDimension;

    public BoardFrame(Board board) {
        initialize(board);
        chessBoard = new ChessboardPanel(board, boardDimension);
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);

    }

    private void initialize(Board board) {
        setTitle("Szachownica dla problemu n-hetmanów");
        int size = 500 - (500 % board.getSize());
        boardDimension = new Dimension(size, size);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(boardDimension);
        getContentPane().add(layeredPane);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        pack();
        setResizable(false);
        setLocationRelativeTo( null );
        setVisible(true);
    }
}
