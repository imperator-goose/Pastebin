package com.ruslan.pastebin.Pastebin.dao;

import com.ruslan.pastebin.Pastebin.entity.Comment;

import java.util.List;

public interface GenericRepository<T, ID>{
    public List<T> getAll();

    public void save(T object);
    public void update(T object);

    public T getById(ID id);

    public void deleteById(ID id);
}