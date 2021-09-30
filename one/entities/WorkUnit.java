package Maktab58_HW5_ElhamAmini.one.entities;

import Maktab58_HW5_ElhamAmini.one.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkUnit {
    private int id;
    private String name;
    private String phoneNumber;
    private Employee[] employees;

    public WorkUnit(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Employee[] getEmployees() throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees where work_unit_id=" + getId());
        resultSet.last();
        employees = new Employee[resultSet.getRow()];
        resultSet.beforeFirst();
        int index = 0;
        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getString("first_name"),
                    resultSet.getString("last_name"), resultSet.getInt("personnel_id"),
                    resultSet.getString("birth_date"));
            employee.setId(resultSet.getInt("id"));
            employees[index++] = employee;
        }
        resultSet.close();
        statement.close();
        JdbcUtil.closeConnection();
        return employees;
    }

    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new Employee[1];
            employees[0] = employee;
        } else {
            Employee[] temp = new Employee[employees.length + 1];
            int index = 0;
            for (Employee emp : employees) {
                temp[index++] = emp;
            }
            temp[index] = employee;
            employees = temp;
        }
    }

    public void printInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("id: ").append(getId()).append(", ");
        builder.append("name: ").append(getName()).append(", ");
        builder.append("phone number: ").append(getPhoneNumber());
        builder.append("}");
        System.out.println(builder);
    }
}
