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

        JButton btnNewButton = new JButton(board.get(0).orient);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    board.flip(0);
                    btnNewButton.setText(board.get(0).orient);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, // position
                            "Error", // title
                            e.getMessage(), // message
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
                            "Error", // title
                            e.getMessage(), // message
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
                            "Error", // title
                            e.getMessage(), // message
                            JOptionPane.ERROR_MESSAGE // icon
                            );
                }
            }
        });
        contentPane.add(btnNewButton_2, BorderLayout.EAST);

        JButton btnNewButton_3 = new JButton("Computer Play");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
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

}
