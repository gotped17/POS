/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.Student;
import database.DB_Access;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gottl
 */
public class NewStudentGUI extends javax.swing.JPanel {

    private DB_Access dba;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private SchoolAdminGUI saGui;
    private JFrame frame;
    
    public NewStudentGUI(SchoolAdminGUI saGui, JFrame frame) {
        try {
            initComponents();
            this.saGui = saGui;
            this.frame = frame;
            dba = DB_Access.getInstance();
            cbGrade.removeAllItems();
            dba.getAllClasses().keySet().forEach(grade -> {
                cbGrade.addItem(grade);
            });
        } catch (SQLException ex) {
            Logger.getLogger(NewStudentGUI.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfFirstname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfSurname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbGrade = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbGender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfDateOfBirth = new javax.swing.JTextField();
        btSubmit = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Neuen Schüler erstellen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(jLabel6, gridBagConstraints);

        jLabel1.setText("Vorname");
        jLabel1.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel1.setMinimumSize(new java.awt.Dimension(90, 22));
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(jLabel1, gridBagConstraints);

        tfFirstname.setToolTipText("Vorname");
        tfFirstname.setMaximumSize(new java.awt.Dimension(90, 22));
        tfFirstname.setMinimumSize(new java.awt.Dimension(90, 22));
        tfFirstname.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(tfFirstname, gridBagConstraints);

        jLabel2.setText("Nachname");
        jLabel2.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel2.setMinimumSize(new java.awt.Dimension(90, 22));
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(jLabel2, gridBagConstraints);

        tfSurname.setToolTipText("Vorname");
        tfSurname.setMaximumSize(new java.awt.Dimension(90, 22));
        tfSurname.setMinimumSize(new java.awt.Dimension(90, 22));
        tfSurname.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(tfSurname, gridBagConstraints);

        jLabel3.setText("Klasse");
        jLabel3.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel3.setMinimumSize(new java.awt.Dimension(90, 22));
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(jLabel3, gridBagConstraints);

        cbGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbGrade.setMaximumSize(new java.awt.Dimension(90, 22));
        cbGrade.setMinimumSize(new java.awt.Dimension(90, 22));
        cbGrade.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(cbGrade, gridBagConstraints);

        jLabel4.setText("Geschlecht");
        jLabel4.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel4.setMinimumSize(new java.awt.Dimension(90, 22));
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(jLabel4, gridBagConstraints);

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Männlich", "Weiblich", "Divers" }));
        cbGender.setMaximumSize(new java.awt.Dimension(90, 22));
        cbGender.setMinimumSize(new java.awt.Dimension(90, 22));
        cbGender.setName(""); // NOI18N
        cbGender.setOpaque(true);
        cbGender.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(cbGender, gridBagConstraints);

        jLabel5.setText("Geburtstag");
        jLabel5.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel5.setMinimumSize(new java.awt.Dimension(90, 22));
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(jLabel5, gridBagConstraints);

        tfDateOfBirth.setText("yyyy-MM-dd");
        tfDateOfBirth.setMaximumSize(new java.awt.Dimension(90, 22));
        tfDateOfBirth.setMinimumSize(new java.awt.Dimension(90, 22));
        tfDateOfBirth.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 7;
        add(tfDateOfBirth, gridBagConstraints);

        btSubmit.setText("Erstellen");
        btSubmit.setPreferredSize(new java.awt.Dimension(90, 22));
        btSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSubmit(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        add(btSubmit, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void onSubmit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSubmit
        String firstname = tfFirstname.getText();
        String surname = tfSurname.getText();
        String dateString = tfDateOfBirth.getText();
        String genderString = cbGender.getSelectedItem().toString();
        String grade = cbGrade.getSelectedItem().toString();
        try {
            if (firstname == null || surname == null || dateString == null || genderString == null || grade == null) {
                JOptionPane.showMessageDialog(this, "Es muss jedes Kästchen aufgefüllt sein!");
            } else {

                LocalDate dateOfBirth = LocalDate.parse(dateString, DTF);
                char gender = genderString.charAt(0);

                Student student = new Student(0, dba.getAllClasses().get(grade), 0, surname.toUpperCase(), firstname, gender, dateOfBirth);
                dba.insertNewStudent(student);
                JOptionPane.showMessageDialog(this, "Erstellen erfolgreich!");
                saGui.loadStudents();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Bitte ein gültiges Datum im Format yyyy-MM-dd eingeben!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Klassen");
        }
    }//GEN-LAST:event_onSubmit


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSubmit;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JComboBox<String> cbGrade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField tfDateOfBirth;
    private javax.swing.JTextField tfFirstname;
    private javax.swing.JTextField tfSurname;
    // End of variables declaration//GEN-END:variables
}
