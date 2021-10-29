package com.example.crud.Repository;

import com.example.crud.Model.Data;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Properties;
import java.util.UUID;

@Component("postgreRepo")
public class DataPostgresqlRepository implements IRepository {
    private Statement statement;

    public DataPostgresqlRepository(){
        try {
            Properties properties = new Properties();
            properties.load(DataPostgresqlRepository.class.getClassLoader().getResourceAsStream("application.properties"));

            String connectionString = properties.getProperty("postgresql.url");
            String username = properties.getProperty("postgresql.user");
            String password = properties.getProperty("postgresql.password");

            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(connectionString, username, password);
            statement = connection.createStatement();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Data get(UUID id) {
        try {
            String query = "SELECT * FROM testdata where id = '" + id + "'";
            var result = statement.executeQuery(query);
            result.next();

            Data dataObj = new Data();
            dataObj.id = UUID.fromString(result.getString("id"));
            dataObj.data = result.getString("data");
            dataObj.time = result.getTimestamp("time");

            return dataObj;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public UUID insert(Data data){
        try{
            String query = "INSERT INTO testdata(id, data, time) " +
                    "VALUES('" + data.id + "', '" + data.data + "', '" + data.time + "')";
            statement.executeUpdate(query);
            return data.id;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public Data update(Data data){
        try{
            String query = "UPDATE testdata SET " +
                    "data = '" + data.data + "'," +
                    "time = '" + data.time +
                    "' WHERE id = '" + data.id + "'";
            statement.executeUpdate(query);
            return data;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public UUID delete(UUID id){
        try{
            String query = "DELETE FROM testdata WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return id;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
