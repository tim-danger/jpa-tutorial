package com.javaee.jpa.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="EMPLOYEE")
@XmlRootElement
public class Employee {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="SUPERVISOR_ID")
    protected Employee supervisor;

    @Column(name="FIRST_NAME")
    protected String firstName;

    @Column(name="LAST_NAME")
    protected String lastName;

    @Column(name="SSN")
    protected String ssn;

    @Column(name="SALARY")
    protected double salary;

    @Column(name="HIRE_DATE")
    @Convert(converter = DateConverter.class)
    protected String hireDate;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    protected Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="EMP_PROJ",
            joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="PROJ_ID", referencedColumnName="ID"))
    private List<Project> projects;

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
