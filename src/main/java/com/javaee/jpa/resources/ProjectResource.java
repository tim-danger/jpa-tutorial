package com.javaee.jpa.resources;

import com.javaee.jpa.entity.Project;
import com.javaee.jpa.repository.ProjectRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class ProjectResource {

    @EJB
    private ProjectRepository repository;

    @GET
    @Path("projects")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> findAllProjects() {
        List<Project> Projects = repository.findAllProjects();
        return Projects;
    }

    @POST
    @Path("project")
    @Produces(MediaType.APPLICATION_JSON)
    public Project createProject(Project Project) {
        repository.createProject(Project);
        return Project;
    }

    @PUT
    @Path("project")
    @Produces(MediaType.APPLICATION_JSON)
    public Project updateProject(Project project) {
        repository.updateProject(project);
        return project;
    }

    @GET
    @Path("project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project findProjectById(@PathParam("id") Long id) {
        return repository.findProjectById(id);
    }

    @DELETE
    @Path("project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProjectById(@PathParam("id") Long id) {
        repository.deleteProjectById(id);
    }

}
