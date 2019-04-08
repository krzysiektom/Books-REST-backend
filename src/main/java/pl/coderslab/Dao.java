package pl.coderslab;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
     
    T findById(long id);
     
    List<T> findAll();
     
    void save(T t);
     
    void remove(long id);
}
