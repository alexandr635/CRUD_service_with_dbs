package com.example.crud.Service;

import com.example.crud.Model.Data;
import com.example.crud.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class DataService implements IDataService {
    private final IRepository iRepository;

    @Autowired
    public DataService(
            IRepository iRepository
    ){
        this.iRepository = iRepository;
    }

    @Override
    @Async
    public UUID Create(Data data) {
        data.id = UUID.randomUUID();
        data.time = new Timestamp(System.currentTimeMillis());
        var id = iRepository.insert(data);

        return id;
    }

    @Override
    public Data Read(UUID id) throws Exception {
        var data = iRepository.get(id);
        if (data == null)
            throw new ChangeSetPersister.NotFoundException();

        return data;
    }

    @Override
    public Data Update(Data newData) throws Exception {
        var currentData = iRepository.get(newData.id);
        if (currentData == null)
            throw new ChangeSetPersister.NotFoundException();

        newData.time = new Timestamp(System.currentTimeMillis());
        return iRepository.update(newData);
    }

    @Override
    public UUID Delete(UUID id) throws Exception {
        var currentData = iRepository.get(id);
        if (currentData == null)
            throw new ChangeSetPersister.NotFoundException();

        return iRepository.delete(id);
    }
}
