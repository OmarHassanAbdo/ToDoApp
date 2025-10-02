package com.example.todo.service;

import com.example.todo.entity.Task;
import com.example.todo.entity.User;
import com.example.todo.repository.TaskRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.servicesInterfaces.TaskService;
import com.example.todo.service.servicesInterfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Override
    public Set<Task> createTaskForUser(Long id, Task task) {
        User user = userService.getUserById(id);
        task.setUser(user);
        taskRepository.save(task);
        user = userService.getUserById(id);
        return user.getTasks();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task is not found")
        );
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if(!taskRepository.existsById(id)){
            throw new RuntimeException("Task is not found");
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public Task deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.deleteById(id);
        return task;
    }

    @Override
    public Set<Task> getTasksByUserId(Long id) {
        User user = userService.getUserById(id);
        return user.getTasks();
    }
}
