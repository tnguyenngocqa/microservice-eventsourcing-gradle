package com.ltfullstack.employeeservice.command.controller;

import com.ltfullstack.employeeservice.command.command.CreateEmployeeCommand;
import com.ltfullstack.employeeservice.command.command.DeleteEmployeeCommand;
import com.ltfullstack.employeeservice.command.command.UpdateEmployeeCommand;
import com.ltfullstack.employeeservice.command.model.CreateEmployeeModel;
import com.ltfullstack.employeeservice.command.model.UpdateEmployeeModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employees")
public class EmployeeController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model) {
        CreateEmployeeCommand command = CreateEmployeeCommand.builder()
                .id(UUID.randomUUID().toString())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .kin(model.getKin())
                .isDisciplined(false)
                .build();

        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String editBook(@RequestBody UpdateEmployeeModel model, @PathVariable String employeeId) {
        UpdateEmployeeCommand command = UpdateEmployeeCommand.builder()
                .id(employeeId)
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .kin(model.getKin())
                .isDisciplined(model.getIsDisciplined())
                .build();

        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteBook(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = DeleteEmployeeCommand.builder()
                .id(employeeId)
                .build();

        return commandGateway.sendAndWait(command);
    }
}
