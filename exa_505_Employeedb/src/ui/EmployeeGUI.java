/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.Department;
import bl.EmployeeTableModel;
import db.DB_Access;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author gotped17
 */
public class EmployeeGUI extends javax.swing.JFrame {

    private DB_Access dba;
    private EmployeeTableModel etm;

    public EmployeeGUI() {
        try {
            dba = DB_Access.getInstance();
            List<Department> depts = dba.getDepartments();
            etm = new EmployeeTableModel(dba.getEmployeesFiltered(null, null, depts.get(0).getDeptName()));
            initComponents();
            
            for (Department dept : depts) {
                cbDept.addItem(dept.getDeptName());
            }

        } catch (SQLException ex) {
            System.out.println("Error retrieving data from database");
            ex.printStackTrace();
        }

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

        plFilter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDept = new javax.swing.JComboBox<>();
        cbBirthDate = new javax.swing.JCheckBox();
        tfBirth = new javax.swing.JTextField();
        btDots = new javax.swing.JButton();
        cbMale = new javax.swing.JCheckBox();
        cbFemale = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmployees = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        epmanager = new javax.swing.JEditorPane();
        btNext = new javax.swing.JButton();
        btPrev = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        plFilter.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));
        plFilter.setPreferredSize(new java.awt.Dimension(500, 250));
        plFilter.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Department:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 48);
        plFilter.add(jLabel1, gridBagConstraints);

        cbDept.setActionCommand("dept");
        cbDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeptChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        plFilter.add(cbDept, gridBagConstraints);

        cbBirthDate.setText("Birthdate before:");
        cbBirthDate.setActionCommand("activate_bd");
        cbBirthDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onActivateDate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 48);
        plFilter.add(cbBirthDate, gridBagConstraints);

        tfBirth.setText("Fr 01-01-2000");
        tfBirth.setEnabled(false);
        tfBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeptChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        plFilter.add(tfBirth, gridBagConstraints);

        btDots.setText("...");
        btDots.setActionCommand("datepicker");
        btDots.setEnabled(false);
        btDots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeptChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 6, 0);
        plFilter.add(btDots, gridBagConstraints);

        cbMale.setText("Male");
        cbMale.setActionCommand("male");
        cbMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCheckGender(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 48);
        plFilter.add(cbMale, gridBagConstraints);

        cbFemale.setText("Female");
        cbFemale.setActionCommand("female");
        cbFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCheckGender(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        plFilter.add(cbFemale, gridBagConstraints);

        getContentPane().add(plFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 240));

        tbEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbEmployees.setPreferredSize(null);
        tbEmployees.setModel(etm);
        jScrollPane1.setViewportView(tbEmployees);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 480, 440));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Management"));

        epmanager.setEnabled(false);
        jScrollPane2.setViewportView(epmanager);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 420, 230));

        btNext.setText(">");
        btNext.setActionCommand("next");
        btNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onPageSwitch(evt);
            }
        });
        getContentPane().add(btNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, -1, 30));

        btPrev.setText("<");
        btPrev.setActionCommand("prev");
        btPrev.setEnabled(false);
        btPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onPageSwitch(evt);
            }
        });
        getContentPane().add(btPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, -1, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onDeptChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDeptChange
        cbFemale.setSelected(false);
        cbMale.setSelected(false);
        try {
            String deptName = cbDept.getSelectedItem().toString();
            etm.changeContent(dba.getEmployeesFiltered(null, null, deptName));
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onDeptChange

    private void onPageSwitch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onPageSwitch
        String action = evt.getActionCommand();
        try {
            
            if (action.equals("next")) {

                btPrev.setEnabled(true);
                dba.changeOffset(100);
                etm.changeContent(dba.getEmployeesFiltered());

            } else {
                dba.changeOffset(-100);
                etm.changeContent(dba.getEmployeesFiltered());
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onPageSwitch

    private void onActivateDate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onActivateDate
        tfBirth.setEditable(cbBirthDate.isSelected());
    }//GEN-LAST:event_onActivateDate

    private void onCheckGender(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCheckGender
        // TODO add your handling code here:
    }//GEN-LAST:event_onCheckGender

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
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDots;
    private javax.swing.JButton btNext;
    private javax.swing.JButton btPrev;
    private javax.swing.JCheckBox cbBirthDate;
    private javax.swing.JComboBox<String> cbDept;
    private javax.swing.JCheckBox cbFemale;
    private javax.swing.JCheckBox cbMale;
    private javax.swing.JEditorPane epmanager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel plFilter;
    private javax.swing.JTable tbEmployees;
    private javax.swing.JTextField tfBirth;
    // End of variables declaration//GEN-END:variables
}
