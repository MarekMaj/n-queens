package nqueens.gui;

import nqueens.domain.Board;

import javax.swing.*;
import java.awt.*;

public class ChessboardPanel extends JPanel{

    public ChessboardPanel(Board board, Dimension boardDimension) {
        initialize(boardDimension, board.getSize());
        paintChessboard(board.getSize());
        paintQueens(board);
    }

    private void initialize(Dimension boardDimension, int size) {
        this.setLayout(new GridLayout(size, size));
        this.setPreferredSize(boardDimension);
        this.setBounds(0, 0, boardDimension.width, boardDimension.height);
    }

    private void paintChessboard(int size) {
        for (int row=0; row<size; row++)
            for (int column=0; column<size; column++){
                this.add(createSquarePanel(size, row*size + column));
            }
    }

    private JPanel createSquarePanel(int size, int position) {
        JPanel square = new JPanel(new BorderLayout());
        boolean rowIsEven = (((position / size) % 2) == 0);
        boolean columnIsEven = (((position % size) % 2) == 0);

        if (rowIsEven)
            square.setBackground( columnIsEven ? Color.GRAY : Color.WHITE );
        else
            square.setBackground( columnIsEven ? Color.WHITE : Color.GRAY );

        return square;
    }

    private void paintQueens(Board board) {
        for (int row=0; row<board.getSize(); row++){
            for (int column=0; column<board.getSize(); column++){
                if (board.isCellContainQueen(column,row)){
                    JLabel queenLabel = createLabelWithQueen();
                    JPanel panel = (JPanel) this.getComponent(row*board.getSize() + column);
                    panel.add(queenLabel);
                }
            }
        }
    }

    private JLabel createLabelWithQueen() {
        return new JLabel(){
                @Override
                public void paintComponent (Graphics g) {
                    super.paintComponent (g);
                    try {
                        ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource("blackQueen.gif"));
                        g.drawImage (imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                };
            };
    }
}