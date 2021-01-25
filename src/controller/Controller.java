/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.View;

/**
 * Contain method to control user interface
 *
 * @author letha
 */
public class Controller {

    private View puzzle;
    private int size;
    private String[][] origin;
    private JButton[][] matrix;

    /**
     * Constructor
     *
     * @param View <Code>View</Code>
     */
    public Controller(View puzzle) {
        this.puzzle = puzzle;
        addButton();
    }

    /**
     * Set grid layout Add button set to the panel
     */
    public void addButton() {
        String[] sizex = puzzle.getComboBox().getSelectedItem().toString().split("x");
        size = Integer.parseInt(sizex[0] + "");

        puzzle.getPanel().setLayout(new GridLayout(size, size, 5, 5));
        puzzle.getPanel().setPreferredSize(new Dimension(60 * size, 60 * size));

        matrix = new JButton[size][size];
        origin = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton(i * size + j + 1 + "");
                matrix[i][j] = button;
                origin[i][j] = i * size + j + 1 + "";
                puzzle.getPanel().add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        move(button);
                    }
                });
            }
        }
        matrix[size - 1][size - 1].setText("");
        origin[size - 1][size - 1] = "";
        mixButton();
        puzzle.pack();
    }

    /**
     * Get position of empty button
     *
     * @return Point
     */
    public Point getEmpty() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getText().equals("")) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Get position of clicked button
     *
     * @param button <Code>JButton</Code>
     * @return <Code>Point</Code>
     */
    public Point getClickedButton(JButton button) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getText().equals(button.getText())) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Shuffle the button set
     */
    public void mixButton() {
        for (int i = 0; i < 1000; i++) {
            Point p = getEmpty();
            int x = p.x;
            int y = p.y;
            Random rand = new Random();
            int choice = rand.nextInt(4);
            switch (choice) {
                case 0: {
                    if (x > 0) {
                        String upper = matrix[x - 1][y].getText();
                        matrix[x][y].setText(upper);
                        matrix[x - 1][y].setText("");
                    }
                    break;
                }
                case 1: {
                    if (y < size - 1) {
                        String right = matrix[x][y + 1].getText();
                        matrix[x][y].setText(right);
                        matrix[x][y + 1].setText("");
                    }
                    break;
                }
                case 2: {
                    if (x < size - 1) {
                        String down = matrix[x + 1][y].getText();
                        matrix[x][y].setText(down);
                        matrix[x + 1][y].setText("");
                    }
                    break;
                }
                case 3: {
                    if (y > 0) {
                        String left = matrix[x][y - 1].getText();
                        matrix[x][y].setText(left);
                        matrix[x][y - 1].setText("");
                    }
                    break;
                }
            }
        }
    }

    /**
     * Check if button is adjacent to empty square
     *
     * @param button <Code>JButton</Code>
     * @return <Code>boolean</Code>
     */
    public boolean canMove(JButton button) {
        if (button.getText().equals("")) {
            return false;
        }

        Point buttonPos = getClickedButton(button);
        Point emptyBt = getEmpty();

        if (buttonPos.x == emptyBt.x && Math.abs(buttonPos.y - emptyBt.y) == 1) {
            return true;
        } else if (buttonPos.y == emptyBt.y && Math.abs(buttonPos.x - emptyBt.x) == 1) {
            return true;
        }
        return false;
    }

    /**
     * Check button can move and then move it
     *
     * @param button <Code>JButton</Code>
     */
    public void move(JButton button) {
        if (canMove(button) && !puzzle.isFirstTime() && puzzle.canPlay()) {
            Point emptyBt = getEmpty();
            String txt = button.getText();
            matrix[emptyBt.x][emptyBt.y].setText(txt);
            button.setText("");
            puzzle.setMoveCount(puzzle.getMoveCount() + 1);
            puzzle.getLabelMove().setText(puzzle.getMoveCount() + "");
            if (checkWon()) {
                puzzle.getElapse().stop();
                puzzle.setCanPlay(false);
                JOptionPane.showMessageDialog(puzzle, "You win! Congratulation!");
            }
        }
    }

    /**
     * Compare to original string set to check if user won or not
     *
     * @return <Code>boolean</Code>
     */
    public boolean checkWon() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!matrix[i][j].getText().equals(origin[i][j])) {
                    return false;
                }
            }
        }
        return true;

    }
}
