package com.fatto.tasks.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.fatto.tasks.controller.dto.TaskFilter;
import com.fatto.tasks.controller.dto.TaskResponse;
import com.fatto.tasks.entity.Task;
import com.fatto.tasks.repository.TaskRepository;
import com.fatto.tasks.service.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<TaskResponse> salvar(@RequestBody TaskFilter taskDto) {

        // convert DTO to entity
        Task postRequest = modelMapper.map(taskDto, Task.class);

        Task task = taskService.save(postRequest);

        // convert entity to DTO
        TaskResponse postResponse = modelMapper.map(task, TaskResponse.class);

        return ResponseEntity.status(200).body(postResponse);
    }

    @GetMapping
    public List<TaskFilter> listar() {
        return taskService.findAll().stream().map(task -> modelMapper.map(task, TaskFilter.class))
                .collect(Collectors.toList());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deletar(@PathVariable Long id) {

        taskService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("{id}")
    Task update(@RequestBody Task newTask, @PathVariable Long id) {

        return taskRepository.findById(id).map(task -> {
            task.setNome(newTask.getNome());
            task.setCusto(newTask.getCusto());
            task.setDataLimite(newTask.getDataLimite());
            task.setOrdemApresentacao(newTask.getOrdemApresentacao());
            return taskRepository.save(task);
        })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return taskRepository.save(newTask);
                });
    }
}
