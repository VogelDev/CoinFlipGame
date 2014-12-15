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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
        setBounds(100, 100, 350, 175);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        board = new Board();
        player = new Player();

        JButton coin1 = new JButton(board.get(0).orient);
        JButton coin2 = new JButton(board.get(1).orient);
        JButton coin3 = new JButton(board.get(2).orient);
        coin1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(0);
                    update(coin1, coin2, coin3);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            e.getMessage(), // message
                            "Error", // title
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }

            }
        });

        coin2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(1);
                    update(coin1, coin2, coin3);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            e.getMessage(), // message
                            "Error", // title
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }
                
                if(board.isOver()){
                    JOptionPane.showMessageDialog(null, "You Win");                    
                }
            }
        });

        coin3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(2);
                    coin3.setText(board.get(2).orient);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            e.getMessage(), // message
                            "Error", // title
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }
            }
        });

        JButton btnCompPlay = new JButton("Computer Play");
        btnCompPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Board tmp;
                while (!board.isOver()) {
                    int choice = player.takeTurn(board);
                    JOptionPane.showMessageDialog(
                            null,
                            "I'm going to flip coin number: "
                                    + String.valueOf(choice + 1));
                    try {
                        tmp = new Board(board.get(0).getFacing(), board.get(1)
                                .getFacing(), board.get(2).getFacing());
                        board.flip(choice);
                        move = new Move(tmp, choice,
                                player.getCurrentSet().moveCount, true);
                        player.getCurrentSet().add(move);
                        update(coin1, coin2, coin3);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Failed, guess I can't flip that one.");
                        endGame(choice, false);
                        break;
                    }
                    if (board.isOver()) {
                        JOptionPane.showMessageDialog(null, "I won in: "
                                + player.currentSet.moveCount + " turns");
                        endGame(choice, true);
                        break;
                    }
                }
            }
        });

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                board = new Board();
                update(coin1, coin2, coin3);
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(coin1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewGame))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                            .addGap(6)
                            .addComponent(coin2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(coin3, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                        .addComponent(btnCompPlay))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(coin1, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(coin3, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                            .addComponent(coin2, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
                    .addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewGame)
                        .addComponent(btnCompPlay))
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void endGame(int choice, boolean legal) {
        move = new Move(board, choice, player.getCurrentSet().moveCount, legal);
        player.getCurrentSet().add(move);
        player.getCurrentSet().setSuccess(legal);
        // System.out.println(move);
        if (legal) {
            if (player.getCurrentSet().export())
                System.out.println("Exported");

            System.out.println(player.getCurrentSet().moveCount);
        } else {
            player.getIllegal().add(move);
            MoveSet.export(player.getIllegal());
        }

        player = new Player();
        board = new Board();
    }

    private void update(JButton btnNewButton, JButton btnNewButton_1,
            JButton btnNewButton_2) {

        btnNewButton.setText(board.get(0).orient);
        btnNewButton_1.setText(board.get(1).orient);
        btnNewButton_2.setText(board.get(2).orient);
    }

}
