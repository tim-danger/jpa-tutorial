package com.javaee.jpa.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="PROJECT")
@XmlRootElement
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    protected Long id;

    @Column(name="DESCRIPTION")
    protected String description;

    @Column(name="START_DATE")
    @Convert(converter = DateConverter.class)
    protected String startDate;

    @Column(name="FINISH_DATE")
    @Convert(converter = DateConverter.class)
    protected String finishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
