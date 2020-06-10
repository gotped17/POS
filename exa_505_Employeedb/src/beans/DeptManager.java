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
public class DeptManager {
    
    private Employee manager;
    private Department dept;
    private LocalDate fromDate;
    private LocalDate toDate;

    public DeptManager(Employee manager, Department dept, LocalDate fromDate, LocalDate toDate) {
        this.manager = manager;
        this.dept = dept;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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
