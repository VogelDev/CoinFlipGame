package com.onequest.coingame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameGui extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Board board;
    private Player player;
    private Move move;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameGui frame = new GameGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GameGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        board = new Board();
        player = new Player();

        JButton btnNewButton = new JButton(board.get(0).orient);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(0);
                    btnNewButton.setText(board.get(0).orient);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            e.getMessage(), // message
                            "Error", // title
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }

            }
        });
        contentPane.add(btnNewButton, BorderLayout.WEST);

        JButton btnNewButton_1 = new JButton(board.get(1).orient);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(1);
                    btnNewButton_1.setText(board.get(1).orient);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            e.getMessage(), // message
                            "Error", // title
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }
            }
        });
        contentPane.add(btnNewButton_1, BorderLayout.CENTER);

        JButton btnNewButton_2 = new JButton(board.get(2).orient);

        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(2);
                    btnNewButton_2.setText(board.get(2).orient);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            e.getMessage(), // message
                            "Error", // title
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }
            }
        });
        contentPane.add(btnNewButton_2, BorderLayout.EAST);

        JButton btnNewButton_3 = new JButton("Computer Play");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Board tmp;
                while (!board.isOver()) {
                    int choice = player.takeTurn(board);
                    try {
                        tmp = new Board(board.get(0).getFacing(), board.get(1).getFacing(), board.get(2).getFacing());
                        board.flip(choice);

                        JOptionPane.showMessageDialog(null, String.valueOf(choice + 1));
                        move = new Move(tmp, choice, player.getCurrentSet().moveCount, true);
                        player.getCurrentSet().add(move);
                        //System.out.println(player.getCurrentSet());
                    } catch (Exception e) {
                        //System.out.println("failed");
                        //System.out.println(board + ", " + choice);
                        endGame(choice, false);
                        break;
                    }
                    if (board.isOver()) {
                        endGame(choice, true);
                        break;
                    }
                }
            }
        });
        contentPane.add(btnNewButton_3, BorderLayout.SOUTH);

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                board = new Board();
                btnNewButton.setText(board.get(0).orient);
                btnNewButton_1.setText(board.get(1).orient);
                btnNewButton_2.setText(board.get(2).orient);
            }
        });
        contentPane.add(btnNewGame, BorderLayout.NORTH);
    }
    
    private void endGame(int choice, boolean legal) {
        move = new Move(board, choice, player.getCurrentSet().moveCount, legal);
        player.getCurrentSet().add(move);
        player.getCurrentSet().setSuccess(legal);
        //System.out.println(move);
        if (legal){
            if (player.getCurrentSet().export())
                System.out.println("Exported");
            
            System.out.println(player.getCurrentSet().moveCount);
        }else{
            player.getIllegal().add(move);
            MoveSet.export(player.getIllegal());
        }

        player = new Player();
        board = new Board();
    }

}
