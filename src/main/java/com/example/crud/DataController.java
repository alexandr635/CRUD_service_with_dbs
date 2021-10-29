package com.example.crud;

import com.example.crud.Model.Data;
import com.example.crud.Service.IDataService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@Tag(name = "Data controller", description = "Controller for management data with mongo db")
public class DataController {
    private final IDataService iDataService;

    @Autowired
    public DataController(IDataService iDataService) {
        this.iDataService = iDataService;
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "500", description = "Fail request")
    })
    public Data Get(
            @Parameter(description = "Идентификатор данных") @PathVariable UUID id
    ) throws Exception {
        return iDataService.Read(id);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "500", description = "Fail request")
    })
    public UUID Create(
            @Parameter(description = "Данные в формате json") @RequestBody Data data
    ){
        return iDataService.Create(data);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "500", description = "Fail request")
    })
    public Data Update(
            @Parameter(description = "Данные в формате json") @RequestBody Data data
    ) throws Exception {
        return iDataService.Update(data);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "500", description = "Fail request")
    })
    public UUID Delete(
            @Parameter(description = "Идентификатор данных") @PathVariable UUID id
    ) throws Exception {
        return iDataService.Delete(id);
    }
}
