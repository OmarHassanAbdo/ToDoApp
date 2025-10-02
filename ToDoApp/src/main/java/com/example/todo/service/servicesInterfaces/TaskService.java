package com.example.todo.service.servicesInterfaces;

import com.example.todo.entity.Task;

import java.util.List;
import java.util.Set;

public interface TaskService {

    public Set<Task> createTaskForUser(Long id , Task task);

    public List<Task> getAllTasks();

    public Task getTaskById(Long id);

    public Task updateTask(Long id , Task task);

    public Task deleteTask(Long id);

    public Set<Task> getTasksByUserId(Long id);
}
