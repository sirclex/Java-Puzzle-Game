/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * User interface
 *
 * @author letha
 */
public class View extends javax.swing.JFrame {

    private Controller control;
    private Timer elapse;
    private long time;
    private int moveCount;
    private boolean canPlay;
    private boolean firstTime;

    /**
     * Constructor
     */
    public View() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Puzzle Game");
        control = new Controller(this);
        moveCount = 0;
        firstTime = true;
        canPlay = false;
        initTimer();
    }

    /**
     * Initialize the counting time thread
     */
    public void initTimer() {
        time = 0;
        elapse = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapseListener(e);
            }
        });
    }

    public void elapseListener(ActionEvent ev) {
        time += 1;
        this.lbElapse.setText(time + " sec");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLayout = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxSize = new javax.swing.JComboBox<>();
        lbElapse = new javax.swing.JLabel();
        lbMove = new javax.swing.JLabel();
        btNewGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnLayout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnLayoutLayout = new javax.swing.GroupLayout(pnLayout);
        pnLayout.setLayout(pnLayoutLayout);
        pnLayoutLayout.setHorizontalGroup(
            pnLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        pnLayoutLayout.setVerticalGroup(
            pnLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        jLabel1.setText("Move count:");

        jLabel3.setText("Elapsed:");

        jLabel2.setText("Size:");

        cbxSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" }));

        lbElapse.setText("0 sec");

        lbMove.setText("0");

        btNewGame.setText("New Game");
        btNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbElapse)
                            .addComponent(lbMove)))
                    .addComponent(btNewGame)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(cbxSize, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbMove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbElapse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNewGame)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Handle event of button "New Game"
     *
     * @param evt <Code>java.awt.event.ActionEvent</Code>
     */
    private void btNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewGameActionPerformed
        // TODO add your handling code here:

        if (firstTime) {
            firstTime = false;
            canPlay = true;
            elapse.start();
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Do you want to start again?", "New Game", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (elapse.isRunning()) {
                    elapse.stop();
                }
                this.canPlay = true;

                time = 0;
                lbElapse.setText("0 sec");
                elapse.start();

                moveCount = 0;
                lbMove.setText(moveCount + "");

                pnLayout.removeAll();
                control = new Controller(this);
            }
        }

    }//GEN-LAST:event_btNewGameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    /**
     * Get the time elapsed value
     *
     * @return <Code>long</Code>
     */
    public long getTime() {
        return this.time;
    }

    /**
     * Set the time elapsed value
     * @param time <Code>long</Code>
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Get the Timer counting time
     * @return <Code>Timer</Code>
     */
    public Timer getElapse() {
        return this.elapse;
    }

    /**
     * Get the move count
     * @return <Code>int</Code>
     */
    public int getMoveCount() {
        return this.moveCount;
    }

    /**
     * Set the move count
     * @param move <Code>int</Code>
     */
    public void setMoveCount(int move) {
        this.moveCount = move;
    }

    /**
     * Check if this is first time run the game
     * @return <Code>boolean</Code>
     */
    public boolean isFirstTime() {
        return this.firstTime;
    }

    public boolean canPlay() {
        return this.canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public JComboBox<String> getComboBox() {
        return this.cbxSize;
    }

    public JLabel getLabelElapse() {
        return this.lbElapse;
    }

    public JLabel getLabelMove() {
        return this.lbMove;
    }

    public JPanel getPanel() {
        return this.pnLayout;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNewGame;
    protected javax.swing.JComboBox<String> cbxSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel lbElapse;
    protected javax.swing.JLabel lbMove;
    protected javax.swing.JPanel pnLayout;
    // End of variables declaration//GEN-END:variables
}
