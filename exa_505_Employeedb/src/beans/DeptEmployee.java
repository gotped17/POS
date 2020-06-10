/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;

/**
 *
 * @author gotped17
 */
public class DeptEmployee {
    
    private Employee employee;
    private Department dept;
    private LocalDate fromDate;
    private LocalDate toDate;

    public DeptEmployee(Employee employee, Department dept, LocalDate fromDate, LocalDate toDate) {
        this.employee = employee;
        this.dept = dept;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
    
    
}
