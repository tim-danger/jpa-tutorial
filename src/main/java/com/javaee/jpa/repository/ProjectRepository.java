package com.javaee.jpa.repository;

import com.javaee.jpa.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ProjectRepository {

    @PersistenceContext(unitName = "jpa-tutorial")
    private EntityManager em;

    public List<Project> findAllProjects() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);

        if (query != null) {
            Root<Project> Project = query.from(Project.class);
            query.select(Project);

            TypedQuery<Project> q = em.createQuery(query);
            return q.getResultList();
        }

        return null;
    }

    public void createProject(Project Project) {
        em.persist(Project);
    }

    public void deleteProjectById(Long id) {
        Project empToDelete = em.find(Project.class, id);
        if (empToDelete != null) {
           em.remove(empToDelete);
        }
    }

    public Project findProjectById(Long id) {
        return em.find(Project.class, id);
    }

    public void updateProject(Project Project) {
        em.merge(Project);
    }
}
