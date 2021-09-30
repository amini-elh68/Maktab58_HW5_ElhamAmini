package Maktab58_HW5_ElhamAmini.one.utils;

import Maktab58_HW5_ElhamAmini.one.entities.Employee;
import Maktab58_HW5_ElhamAmini.one.entities.Work;
import Maktab58_HW5_ElhamAmini.one.entities.WorkUnit;

import java.sql.SQLException;
import java.util.Scanner;

public class Command {

    private final Scanner scanner = new Scanner(System.in);
    private final Work work = new Work();

    public void executeApp() throws SQLException {
        work.createTables();
        while (true) {
            printCommand();
            System.out.println("please enter your command number:");
            int number = scanner.nextInt();
            executeCommand(number);
        }
    }

    private void printCommand() {
        System.out.println("********************************************");
        System.out.println("1) Add a work unit");
        System.out.println("2) Add an employee");
        System.out.println("3) Edit work unit name");
        System.out.println("4) Edit employee name");
        System.out.println("5) Print all work units");
        System.out.println("6) Print all employees of the work unit");
        System.out.println("********************************************");
    }

    private void executeCommand(int number) throws SQLException {
        int workUnitId;
        WorkUnit workUnit;
        Employee employee;
        switch (number) {
            case 1:
                WorkUnit unit = getWorkUnitData();
                work.addWorkUnit(unit);
                break;
            case 2:
                work.printAllWorkUnits();
                System.out.println("Please enter the unit ID:");
                workUnitId = scanner.nextInt();
                workUnit = work.getWorkUnit(workUnitId);
                if (workUnit == null) {
                    System.out.println("Work unit not found");
                    break;
                }
                employee = getEmployeeData();
                employee.setWorkUnit(workUnit);
                work.addEmployee(employee);
                break;
            case 3:
                work.printAllWorkUnits();
                System.out.println("Please enter the unit ID:");
                workUnitId = scanner.nextInt();
                workUnit = work.getWorkUnit(workUnitId);
                if (workUnit == null) {
                    System.out.println("Work unit not found");
                    break;
                }
                System.out.println("Please enter the name of the work unit:");
                String name = scanner.next();
                workUnit.setName(name);
                work.editWorkUnit(workUnit);
                break;
            case 4:
                System.out.println("Please enter the employee ID:");
                int id = scanner.nextInt();
                employee = work.getEmployee(id);
                if (employee == null) {
                    System.out.println("Employee not found");
                    break;
                }
                System.out.println("Please enter the employee first name:");
                String firstName = scanner.next();
                System.out.println("Please enter the employee last name:");
                String lastName = scanner.next();
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                work.editEmployee(employee);
                break;
            case 5:
                work.printAllWorkUnits();
                break;
            case 6:
                work.printAllWorkUnits();
                System.out.println("Please enter the unit ID:");
                workUnitId = scanner.nextInt();
                work.printWorkUnitEmployees(workUnitId);
                break;
        }
    }

    private WorkUnit getWorkUnitData() {
        System.out.println("Please enter the name of the work unit:");
        String name = scanner.next();
        System.out.println("Please enter the phone number of the work unit:");
        String phoneNumber = scanner.next();
        WorkUnit workUnit = new WorkUnit(name, phoneNumber);
        return workUnit;
    }

    private Employee getEmployeeData() {
        System.out.println("Please enter the employee first name:");
        String firstName = scanner.next();
        System.out.println("Please enter the employee last name:");
        String lastName = scanner.next();
        System.out.println("Please enter the employee personnel Id:");
        int personnelId = scanner.nextInt();
        System.out.println("Please enter the employee birth date:");
        String birthDate = scanner.next();
        Employee employee = new Employee(firstName, lastName, personnelId, birthDate);
        return employee;
    }
}
