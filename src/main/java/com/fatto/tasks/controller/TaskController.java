package com.fatto.tasks.controller;

import java.util.List;

import com.fatto.tasks.controller.dto.TaskFilter;
import com.fatto.tasks.entity.Task;
import com.fatto.tasks.service.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<TaskFilter> salvar(@RequestBody TaskFilter taskDto) {

        
		// convert DTO to entity
		Task postRequest = modelMapper.map(taskDto, Task.class);

		Task task = taskService.save(postRequest);

		// convert entity to DTO
		TaskFilter postResponse = modelMapper.map(task, TaskFilter.class);

		return ResponseEntity.status(200).body(postResponse);
    }

    @GetMapping
    public List<TaskFilter> listar() {
        return taskService.findAll().stream().map(task -> modelMapper.map(task, TaskFilter.class))
        .collect(Collectors.toList());

        // return ResponseEntity.status(200).body(taskService.findAll());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestBody Task task) {

        taskService.delete(task);
        return ResponseEntity.noContent().build();

    }

}
