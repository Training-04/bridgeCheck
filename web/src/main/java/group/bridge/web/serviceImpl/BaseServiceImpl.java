package group.bridge.web.serviceImpl;

import group.bridge.web.dao.BaseRepository;
import group.bridge.web.service.BaseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseServiceImpl<T,ID> implements BaseService<T,ID>, InitializingBean {
    protected BaseRepository<T,ID> repository;
    @PersistenceContext
    EntityManager entityManager;
    protected abstract void setRepository();
    @Override
    public boolean add(T t) {

        if(repository.save(t)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(T t) {
        if(repository.save(t)==null){
            return false;
        }
        return true;
    }


    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T get(ID id) {
        return repository.getOne(id);
    }
    @Override
    public List<T> getByPredicate(Specification<T> specification){
        return repository.findAll(specification);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        setRepository();
    }
}
