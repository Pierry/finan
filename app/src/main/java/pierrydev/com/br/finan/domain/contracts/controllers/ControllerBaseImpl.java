package pierrydev.com.br.finan.domain.contracts.controllers;

import java.util.List;

public interface ControllerBaseImpl<T> {

    List<T> get(int pag);
    T getById(int id);
    T add(T item);
    boolean update(T item);
    boolean remove(int id);

}
