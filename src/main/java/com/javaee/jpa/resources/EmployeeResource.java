package com.javaee.jpa.resources;

import com.javaee.jpa.entity.Employee;
import com.javaee.jpa.repository.EmployeeRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class EmployeeResource {

    @EJB
    private EmployeeRepository repository;

    @GET
    @Path("employees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> findAllEmployees() {
        List<Employee> employees = repository.findAllEmployees();
        return employees;
    }

    @POST
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee createEmployee(Employee employee) {
        repository.createEmployee(employee);
        return employee;
    }

    @PUT
    @Path("employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee updateEmployee(Employee employee) {
        repository.updateEmployee(employee);
        return employee;
    }

    @GET
    @Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee findEmployeeById(@PathParam("id") Long id) {
        return repository.findEmployeeById(id);
    }

    @DELETE
    @Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEmployeeById(@PathParam("id") Long id) {
        repository.deleteEmployeeById(id);
    }

}
