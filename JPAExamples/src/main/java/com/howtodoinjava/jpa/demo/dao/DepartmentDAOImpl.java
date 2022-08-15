package com.howtodoinjava.jpa.demo.dao;
 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.howtodoinjava.jpa.demo.entity.DepartmentEntity;
 
@Repository
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO {
     
    @PersistenceContext
    private EntityManager manager;
 
  
    public List<DepartmentEntity> getAllDepartments() {
        List<DepartmentEntity> depts = manager.createQuery("Select a From DepartmentEntity a", DepartmentEntity.class).getResultList();
        return depts;
    }
 
   
    public DepartmentEntity getDepartmentById(Integer id) {
        return manager.find(DepartmentEntity.class, id);
    }
 
 
    public boolean addDepartment(DepartmentEntity dept) {
        try{
            manager.persist(dept);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
 
 
    public boolean removeDepartment(DepartmentEntity dept) {
        try{
            manager.remove(dept);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
 
  
    public boolean removeAllDepartments() {
        try{
            Query query = manager.createNativeQuery("DELETE FROM DEPARTMENT");
            query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}