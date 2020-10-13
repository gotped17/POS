/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Employee;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {

    private final String[] columns = {"Pers_Nr", "Name", "Vorname",
        "Geb_Datum", "Salary", "Department No.", "Gender"};
    private List<Object[]> rowData;

    public EmployeeTableModel(List<Employee> employees) {
        rowData = employees
                .stream()
                .map(Employee::convertToTableData)
                .collect(Collectors.toList());
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public int getRowCount() {
        return rowData.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return rowData.get(row)[col];
    }

    public Object[] getSelectedRow(int row) {
        return rowData.get(row);
    }

    public void changeData(List<Employee> employees) {
        rowData = employees
                .stream()
                .map(Employee::convertToTableData)
                .collect(Collectors.toList());
        this.fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

}
