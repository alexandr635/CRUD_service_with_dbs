package com.example.crud.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.UUID;

@Document
public class Data {
    @Id
    public UUID id;
    public String data;
    public Date time;
}
