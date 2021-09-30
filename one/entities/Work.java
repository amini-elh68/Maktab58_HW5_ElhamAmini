package Maktab58_HW5_ElhamAmini.one.entities;

import Maktab58_HW5_ElhamAmini.one.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Work {

    private static int workIdSeq = 0;
    private static int employeeIdSeq = 0;

    public void addEmployee(Employee employee) throws SQLException {
        employee.setId(++employeeIdSeq);
        executeQuery(getInsertQuery(TableName.EMPLOYEE, employee));
        System.out.println("The employee was successfully registered");
    }

    public void addWorkUnit(WorkUnit workUnit) throws SQLException {
        workUnit.setId(++workIdSeq);
        executeQuery(getInsertQuery(TableName.WORK_UNIT, workUnit));
        System.out.println("The work unit was successfully registered");
    }

    public void editEmployee(Employee employee) throws SQLException {
        executeQuery(getUpdateQuery(TableName.EMPLOYEE, employee));
        System.out.println("Employee name was successfully edited");
    }

    public void editWorkUnit(WorkUnit workUnit) throws SQLException {
        executeQuery(getUpdateQuery(TableName.WORK_UNIT, workUnit));
        System.out.println("Work unit name edited");
    }

    private WorkUnit[] getAllWorkUnits() throws SQLException {
        WorkUnit[] workUnits;
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from work_units");
        resultSet.last();
        workUnits = new WorkUnit[resultSet.getRow()];
        resultSet.beforeFirst();
        int index = 0;
        while (resultSet.next()) {
            WorkUnit unit = new WorkUnit(resultSet.getString("name"), resultSet.getString("phone_number"));
            unit.setId(resultSet.getInt("id"));
            workUnits[index++] = unit;
        }
        resultSet.close();
        statement.close();
        JdbcUtil.closeConnection();
        return workUnits;
    }

    public void printAllWorkUnits() throws SQLException {

        for (WorkUnit unit : getAllWorkUnits()) {
            unit.printInfo();
        }
    }

    public void printWorkUnitEmployees(int workUnitId) throws SQLException {
        WorkUnit workUnit = getWorkUnit(workUnitId);
        for (Employee employee : workUnit.getEmployees()) {
            employee.printInfo();
        }
    }

    public WorkUnit getWorkUnit(int id) throws SQLException {
        WorkUnit unit;
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from work_units where id=" + id);
        if (!resultSet.next()) {
            return null;
        }
        unit = new WorkUnit(resultSet.getString("name"), resultSet.getString("phone_number"));
        unit.setId(resultSet.getInt("id"));
        resultSet.close();
        statement.close();
        JdbcUtil.closeConnection();
        return unit;
    }

    public Employee getEmployee(int id) throws SQLException {
        Employee employee;
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees where id=" + id);
        if (!resultSet.next()) {
            return null;
        }
        employee = new Employee(resultSet.getString("first_name"), resultSet.getString("last_name"),
                resultSet.getInt("personnel_id"), resultSet.getString("birth_date"));
        employee.setId(resultSet.getInt("id"));
        statement.close();
        JdbcUtil.closeConnection();
        return employee;
    }

    public void createTables() throws SQLException {
        executeQuery(getDropQuery(TableName.EMPLOYEE));
        executeQuery(getDropQuery(TableName.WORK_UNIT));
        executeQuery(getCreateQuery(TableName.WORK_UNIT));
        executeQuery(getCreateQuery(TableName.EMPLOYEE));
    }

    private void executeQuery(String query) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        JdbcUtil.closeConnection();
    }

    private String getInsertQuery(TableName tableName, Object data) {
        StringBuilder values = new StringBuilder();
        String query = "insert into # (#) values (#)";
        switch (tableName) {
            case EMPLOYEE:
                Employee employee = (Employee) data;
                query = query.replaceFirst("#", "employees");
                if (employee.getWorkUnit() == null)
                    query = query.replaceFirst("#", "id, first_name, last_name, personnel_id, birth_date");
                else
                    query = query.replaceFirst("#", "id, first_name, last_name, personnel_id, birth_date, work_unit_id");
                values.append(employee.getId()).append(", ");
                values.append("'").append(employee.getFirstName()).append("'").append(", ");
                values.append("'").append(employee.getLastName()).append("'").append(", ");
                values.append(employee.getPersonnelId()).append(", ");
                values.append("'").append(employee.getBirthDate()).append("'");
                if (employee.getWorkUnit() != null) {
                    values.append(", ").append(employee.getWorkUnit().getId());
                }
                break;
            case WORK_UNIT:
                WorkUnit workUnit = (WorkUnit) data;
                query = query.replaceFirst("#", "work_units");
                query = query.replaceFirst("#", "id, name, phone_number");
                values.append(workUnit.getId()).append(", ");
                values.append("'").append(workUnit.getName()).append("'").append(", ");
                values.append("'").append(workUnit.getPhoneNumber()).append("'");
                break;
        }
        query = query.replaceFirst("#", values.toString());
        return query;
    }

    private String getUpdateQuery(TableName tableName, Object data) {
        StringBuilder updateColumn = new StringBuilder();
        String query = "update # set # where id=#";
        switch (tableName) {
            case EMPLOYEE:
                Employee employee = (Employee) data;
                query = query.replaceFirst("#", "employees");
                updateColumn.append("first_name=").append("'").append(employee.getFirstName()).append("'").append(", ");
                updateColumn.append("last_name=").append("'").append(employee.getLastName()).append("'");
                query = query.replaceFirst("#", updateColumn.toString());
                query = query.replaceFirst("#", String.valueOf(employee.getId()));
                break;
            case WORK_UNIT:
                WorkUnit workUnit = (WorkUnit) data;
                query = query.replaceFirst("#", "work_units");
                updateColumn.append("name=").append("'").append(workUnit.getName()).append("'");
                query = query.replaceFirst("#", updateColumn.toString());
                query = query.replaceFirst("#", String.valueOf(workUnit.getId()));
                break;
        }
        return query;
    }

    private String getDropQuery(TableName tableName) {
        StringBuilder builder = new StringBuilder("DROP TABLE IF EXISTS ");
        switch (tableName) {
            case EMPLOYEE:
                builder.append("employees");
                break;
            case WORK_UNIT:
                builder.append("work_units");
                break;
        }
        builder.append(";");
        return builder.toString();
    }

    private String getCreateQuery(TableName tableName) {
        StringBuilder builder = new StringBuilder();
        switch (tableName) {
            case EMPLOYEE:
                builder.append("create table employees ").append("(id Integer not NULL,")
                        .append("first_name VARCHAR(255),").append("last_name VARCHAR(255),")
                        .append("personnel_id Integer not NULL,").append("birth_date VARCHAR(10),")
                        .append("work_unit_id Integer, ").append("PRIMARY KEY (id),")
                        .append("FOREIGN KEY (work_unit_id) REFERENCES work_units(id))");
                break;
            case WORK_UNIT:
                builder.append("create table work_units ").append("(id Integer not NULL,")
                        .append("name VARCHAR(255),").append("phone_number VARCHAR(255),")
                        .append("PRIMARY KEY (id))");
                break;
        }
        return builder.toString();
    }

    enum TableName {EMPLOYEE, WORK_UNIT}
}
