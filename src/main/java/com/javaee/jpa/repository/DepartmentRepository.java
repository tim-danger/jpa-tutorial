package com.javaee.jpa.repository;

import com.javaee.jpa.entity.Department;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class DepartmentRepository {

    @PersistenceContext(unitName = "jpa-tutorial")
    private EntityManager em;

    public List<Department> findAllDepartments() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> query = cb.createQuery(Department.class);

        if (query != null) {
            Root<Department> Department = query.from(Department.class);
            query.select(Department);

            TypedQuery<Department> q = em.createQuery(query);
            return q.getResultList();
        }

        return null;
    }

    public void createDepartment(Department Department) {
        em.persist(Department);
    }

    public void deleteDepartmentById(Long id) {
        Department empToDelete = em.find(Department.class, id);
        if (empToDelete != null) {
            em.remove(empToDelete);
        }
    }

    public Department findDepartmentById(Long id) {
        return em.find(Department.class, id);
    }

    public void updateDepartment(Department Department) {
        em.merge(Department);
    }
}
