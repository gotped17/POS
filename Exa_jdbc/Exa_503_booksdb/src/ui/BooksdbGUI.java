/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.WindowEvent;

/**
 *
 * @author Gottl
 */
public class BooksdbGUI extends javax.swing.JFrame {

    /**
     * Creates new form BooksdbGUI
     */
    public BooksdbGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgFilter = new javax.swing.ButtonGroup();
        plSearch = new javax.swing.JPanel();
        lbVerlag = new javax.swing.JLabel();
        lbGenre = new javax.swing.JLabel();
        cbVerlag = new javax.swing.JComboBox<>();
        cbGenre = new javax.swing.JComboBox<>();
        tfSearch = new javax.swing.JTextField();
        rbBook = new javax.swing.JRadioButton();
        rbAuthor = new javax.swing.JRadioButton();
        lbFill = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liBooks = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        epDetails = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        plSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Suchen"));
        plSearch.setLayout(new java.awt.GridBagLayout());

        lbVerlag.setText("Verlag:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 28);
        plSearch.add(lbVerlag, gridBagConstraints);

        lbGenre.setText("Genre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 28);
        plSearch.add(lbGenre, gridBagConstraints);

        cbVerlag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbVerlag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onVerlagSelected(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 70;
        plSearch.add(cbVerlag, gridBagConstraints);

        cbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGenreSelected(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 70;
        plSearch.add(cbGenre, gridBagConstraints);

        tfSearch.setText("Geben Sie einen Suchbegriff ein . . .");
        tfSearch.setToolTipText("");
        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onTextSearch(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        plSearch.add(tfSearch, gridBagConstraints);

        bgFilter.add(rbBook);
        rbBook.setText("Buch");
        rbBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFilterSelected(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(17, 0, 0, 0);
        plSearch.add(rbBook, gridBagConstraints);

        bgFilter.add(rbAuthor);
        rbAuthor.setText("Autor");
        rbAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFilterSelected(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(17, 0, 0, 0);
        plSearch.add(rbAuthor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        plSearch.add(lbFill, gridBagConstraints);

        getContentPane().add(plSearch, java.awt.BorderLayout.PAGE_START);

        liBooks.setBorder(javax.swing.BorderFactory.createTitledBorder("Bücher"));
        liBooks.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        liBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        liBooks.setPreferredSize(new java.awt.Dimension(200, 113));
        jScrollPane1.setViewportView(liBooks);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.LINE_START);

        epDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Buchdetails"));
        jScrollPane2.setViewportView(epDetails);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onVerlagSelected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onVerlagSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_onVerlagSelected

    private void onGenreSelected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onGenreSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_onGenreSelected

    private void onTextSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onTextSearch
        // TODO add your handling code here:
    }//GEN-LAST:event_onTextSearch

    private void onFilterSelected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFilterSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_onFilterSelected
    
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
            java.util.logging.Logger.getLogger(BooksdbGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksdbGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksdbGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksdbGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksdbGUI().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgFilter;
    private javax.swing.JComboBox<String> cbGenre;
    private javax.swing.JComboBox<String> cbVerlag;
    private javax.swing.JEditorPane epDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbFill;
    private javax.swing.JLabel lbGenre;
    private javax.swing.JLabel lbVerlag;
    private javax.swing.JList<String> liBooks;
    private javax.swing.JPanel plSearch;
    private javax.swing.JRadioButton rbAuthor;
    private javax.swing.JRadioButton rbBook;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
