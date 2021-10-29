package com.example.crud.Repository;

import com.example.crud.Model.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IMongoRepository extends MongoRepository<Data, UUID> {
}
