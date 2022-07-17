package com.javaee.jpa.resources;

import com.javaee.jpa.entity.Department;
import com.javaee.jpa.repository.DepartmentRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class DepartmentResource {

    @EJB
    private DepartmentRepository repository;

    @GET
    @Path("departments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> findAllDepartments() {
        List<Department> Departments = repository.findAllDepartments();
        return Departments;
    }

    @POST
    @Path("department")
    @Produces(MediaType.APPLICATION_JSON)
    public Department createDepartment(Department department) {
        repository.createDepartment(department);
        return department;
    }

    @PUT
    @Path("department")
    @Produces(MediaType.APPLICATION_JSON)
    public Department updateDepartment(Department department) {
        repository.updateDepartment(department);
        return department;
    }

    @GET
    @Path("department/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Department findDepartmentById(@PathParam("id") Long id) {
        return repository.findDepartmentById(id);
    }

    @DELETE
    @Path("department/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteDepartmentById(@PathParam("id") Long id) {
        repository.deleteDepartmentById(id);
    }

}
