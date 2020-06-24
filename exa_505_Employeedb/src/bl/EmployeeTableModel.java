/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gotped17
 */
public class EmployeeTableModel extends AbstractTableModel{
    private List<Employee> employees = new ArrayList<>();
    private List<Object[]> rowData = new ArrayList<>();
    private String[] columnNames = {"Name", "Gender", "Birthdate", "Hiredate"};
    
    public EmployeeTableModel(List<Employee> employees) throws SQLException{
        this.employees = employees;
        rowData.addAll(employees.stream()
                        .map(Employee::convertToTableData)
                        .collect(Collectors.toList()));
    }
    
    @Override
    public int getRowCount() {
        return rowData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        return rowData.get(rowIndex)[colIndex];
    }
    
    public String getColumnName(int colIndex){
        return columnNames[colIndex];
    }
    public void changeContent(List<Employee> employees){
        this.employees = employees;
        
        rowData = employees.stream()
                        .map(Employee::convertToTableData)
                        .collect(Collectors.toList());
        this.fireTableDataChanged();
    
    }
    
}
