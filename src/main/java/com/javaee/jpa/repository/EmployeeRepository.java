package com.javaee.jpa.repository;

import com.javaee.jpa.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class EmployeeRepository {

    @PersistenceContext(unitName = "jpa-tutorial")
    private EntityManager em;

    public List<Employee> findAllEmployees() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);

        if (query != null) {
            Root<Employee> employee = query.from(Employee.class);
            query.select(employee);

            TypedQuery<Employee> q = em.createQuery(query);
            return q.getResultList();
        }

        return null;
    }

    public void createEmployee(Employee employee) {
        em.persist(employee);
    }

    public void deleteEmployeeById(Long id) {
        Employee empToDelete = em.find(Employee.class, id);
        if (empToDelete != null) {
           em.remove(empToDelete);
        }
    }

    public Employee findEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    public void updateEmployee(Employee employee) {
        em.merge(employee);
    }
}
