package pl.coderslab;

import java.util.List;

public interface Service<T> {
    List<T> getList();
    T getById(long id);
    T addNew(T t);
    T modify(T t, Long id);
    void delete(long id);
}
