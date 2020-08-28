/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.dao;

import java.util.List;

public interface BaseDAO<T,K> {

    public T obtener(K id);

    public List<T> obtenerTodos();

    public void crea(T c);

    public void actualiza (T c);

    public void borra(K id);
}
