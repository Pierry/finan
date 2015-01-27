package pierrydev.com.br.finan.domain.contracts.repositories;

import java.util.List;

public interface RepositoryBaseImpl<T> {

    List<T> get(int pag);
    T getById(int id);
    T add(T item);
    boolean update(T item);
    boolean remove(int id);

}
