package clientvideo.dao;

import java.io.Serializable;
import java.util.List;

public interface ClientDao<T, Id extends Serializable> {

	public void insert(T entity);

	public void update(T entity);

	public T findByUsername(String username);

	public void delete(T entity);

	public List<T> findAll();
}
