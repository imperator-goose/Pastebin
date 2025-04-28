package com.ruslan.pastebin.Pastebin.dao;

import com.ruslan.pastebin.Pastebin.entity.Comment;

import java.util.List;

public interface GenericRepository<T, ID>{
    public List<T> getAll();

    public T save(T object);
    public T update(T object);

    public T getById(ID id);

    public void deleteById(ID id);
}