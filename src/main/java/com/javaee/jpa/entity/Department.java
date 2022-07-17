package com.javaee.jpa.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="DEPARTMENT")
@XmlRootElement
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    protected Long id;

    @Column(name="NAME")
    protected String name;

    @Column(name="DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MANAGER_ID")
    protected Employee manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
