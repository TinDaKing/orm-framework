package com.example.designpattern.ormframework.mapper;

import java.util.List;

public interface IQuery {
    <T> List<T> executeQuery();
    <T> List<T> executeQueryWithoutRelationship();
    int executeNonQuery();
}
