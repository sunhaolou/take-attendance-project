package edu.duke.ece651.project.team5.shared.dao;

import java.sql.*;
import java.util.Optional;

public interface Dao<T> {

    public Optional<T> get(Object id) throws SQLException;

    public void add(T t) throws SQLException;

    public void update(T t) throws SQLException;

    public void delete(T t) throws SQLException;

}
