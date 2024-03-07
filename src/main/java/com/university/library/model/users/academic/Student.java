package com.university.library.model.users.academic;

public class Student extends AcademicUser {

    private String department;

    public Student(String userId , String name , String emailId , String password , String mobileNumber , String address , String dateOfBirth , String gender , String universityId , String issueDate , String department) {
        super(userId, name , emailId , password , mobileNumber , address , dateOfBirth , gender , universityId , issueDate);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return getClass().getName() +"\n"+ super.toString();
    }
}
