package com.example.crud.Repository;

import com.example.crud.Model.Data;
import java.util.UUID;

public interface IRepository {
    UUID insert(Data data);
    Data get(UUID id) throws Exception;
    Data update(Data newData) throws Exception;
    UUID delete(UUID id) throws Exception;
}
