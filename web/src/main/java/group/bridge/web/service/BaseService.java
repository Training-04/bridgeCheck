package group.bridge.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseService<T,ID> {
    boolean add(T t);

    boolean update(T t);

    void delete(T t);

    void deleteById(ID id);

    List<T> getAll();

    Page<T> getAll(Pageable pageable);

    T get(ID id);
<<<<<<< HEAD
//    只需要把specification作为参数传进去即可获得List.
=======

>>>>>>> 09be948137f6f6fcaeb8e523bf8ed45d2960b2b9
    List<T> getByPredicate(Specification<T> specification);

    Page<T> getByPredicate(Specification<T> specification, Pageable pageable);
}
