package com.example.crud.Repository;

import com.example.crud.Model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component("mongoRepo")
public class DataMongoRepository implements IRepository {
    private final IMongoRepository iMongoRepository;

    @Autowired
    public DataMongoRepository(IMongoRepository iMongoRepository) {
        this.iMongoRepository = iMongoRepository;
    }

    @Override
    public UUID insert(Data data) {
        var t = iMongoRepository.save(data);
        return data.id;
    }

    @Override
    public Data get(UUID id) throws Exception {
        return iMongoRepository.findById(id).get();
    }

    @Override
    public Data update(Data newData) throws Exception {
        return iMongoRepository.save(newData);
    }

    @Override
    public UUID delete(UUID id) throws Exception {
        iMongoRepository.deleteById(id);
        return id;
    }
}
