package com.bridgelabz;

import java.io.IOException;
import java.util.List;

public class EmployPayrollService {

    static List<EmployPayrollData> employList;

    public enum IOServices{
        CONSOLE_IO, FILE_IO
    }

    /**
     *Default constructor
     */
    public EmployPayrollService() {
    }

    /**
     * parameterized constructor
     * @param employList
     */
    public EmployPayrollService(List<EmployPayrollData> employList) {
        this.employList =employList;
    }
    //    create write method to print data back to console
    public boolean writeEmployPayrollData(IOServices ioServices){
        PayrollService payrollService = getEmployeePayrollObject(ioServices);
        try {
            return payrollService.writePayrollData(employList);
        } catch (IOException e) {
            System.out.println("catch block" + e);
        }
        return false;
    }
    public void readEmployPayrollData(IOServices ioServices) {
        PayrollService payrollService = getEmployeePayrollObject(ioServices);
        try {
            payrollService.readPayrollData();
        } catch (IOException e) {
            System.out.println("catch block" + e);
        }
    }
    public long countEntries(IOServices ioServices) {
        PayrollService payrollService = getEmployeePayrollObject(ioServices);
        try {
            return payrollService.countEntries();
        } catch (IOException e) {
            System.out.println("catch block" + e);
        }
        return 0;
    }
    private static PayrollService getEmployeePayrollObject(IOServices ioServices){
        PayrollService payrollService = null;
        if(IOServices.FILE_IO.equals(ioServices))
            payrollService = new FileIOPayrollService();
        else if(IOServices.CONSOLE_IO.equals(ioServices))
            payrollService = new ConsoleIOPayrollService();
        return payrollService;
    }

    //    method to read the input from user through console
    public static void main(String[] args) {
        EmployPayrollService employPayrollService = new EmployPayrollService();
        employPayrollService.readEmployPayrollData(IOServices.CONSOLE_IO);
        employPayrollService.writeEmployPayrollData(IOServices.CONSOLE_IO);
        long count= employPayrollService.countEntries(IOServices.CONSOLE_IO);
//        Show FileIO count no of entries.
        System.out.println(count);
    }
}