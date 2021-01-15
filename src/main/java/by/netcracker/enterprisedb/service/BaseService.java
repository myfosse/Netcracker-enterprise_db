package by.netcracker.enterprisedb.service;

import java.util.List;

public interface BaseService<T> {

  T save(final T object);

  T update(final T object);

  T findById(final Long id);

  List<T> getAll();

  void deleteById(final Long id);
}
