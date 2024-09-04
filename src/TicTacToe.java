import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.StyledEditorKit;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameover = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.BLACK);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.BLACK);
                tile.setForeground(Color.RED);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameover) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkwinner();
                            if (!gameover) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + " 'S turn.");

                            }


                        }
                    }

                });

            }
        }
    }

    public void checkwinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;
            if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setwinner(board[r][i]);
                }
                gameover = true;
                return;
            }
        }
        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            ;
            if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setwinner(board[i][c]);
                }
                gameover = true;
                return;
            }
        }
        //daigonaly
        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setwinner(board[i][i]);
            }
            gameover = true;
            return;

        }
        //Anti-Daigonally
        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != "") {
            setwinner(board[0][2]);
            setwinner(board[1][1]);
            setwinner(board[2][0]);
            gameover = true;
            return;
        }
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameover = true;
        }

    }

    public void setwinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.GRAY);
        textLabel.setText(currentPlayer + "is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.cyan);
        tile.setBackground(Color.BLACK);
        textLabel.setText("Draw!");
    }
}


