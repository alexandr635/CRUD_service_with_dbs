package com.example.crud.Service;

import com.example.crud.Model.Data;
import java.util.UUID;

public interface IDataService {
    UUID Create(Data data);
    Data Read(UUID id) throws Exception;
    Data Update(Data data) throws Exception;
    UUID Delete(UUID id) throws Exception;
}
