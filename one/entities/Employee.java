package Maktab58_HW5_ElhamAmini.one.entities;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private int personnelId;
    private String birthDate;
    private WorkUnit workUnit;

    public Employee(String firstName, String lastName, int personnelId, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnelId = personnelId;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public WorkUnit getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(WorkUnit workUnit) {
        this.workUnit = workUnit;
    }

    public void printInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("id: ").append(getId()).append(", ");
        builder.append("first name: ").append(getFirstName()).append(", ");
        builder.append("last name: ").append(getLastName()).append(", ");
        builder.append("personnel Id: ").append(getPersonnelId()).append(", ");
        builder.append("birth date: ").append(getBirthDate());
        builder.append("}");
        System.out.println(builder);
    }
}

