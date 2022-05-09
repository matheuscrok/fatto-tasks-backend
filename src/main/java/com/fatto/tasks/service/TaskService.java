package com.fatto.tasks.service;

import java.util.List;

import javax.transaction.Transactional;

import com.fatto.tasks.entity.Task;
import com.fatto.tasks.repository.TaskRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public Task save(Task task) {
        return this.repository.save(task);
    }

    public void delete(Long id) {

        this.repository.deleteById(id);
    }

  
}
