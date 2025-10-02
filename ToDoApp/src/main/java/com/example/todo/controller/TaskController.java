package com.example.todo.controller;

import com.example.todo.dto.ApiResponse;
import com.example.todo.entity.Task;
import com.example.todo.service.servicesInterfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createTaskForUser/{id}")
    public ResponseEntity<ApiResponse<Set<Task>>> createTaskForUser(@PathVariable Long id,
                                                                    @RequestBody Task task) {
        Set<Task> tasks = taskService.createTaskForUser(id, task);
        return ResponseEntity.ok(new ApiResponse<>(true, "Task created for you", tasks));
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
        return ResponseEntity.ok(new ApiResponse<>(true, "tasks retrived successfully", taskService.getAllTasks()));
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "task retrived successfully", task));
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(new ApiResponse<>(true, "task updated successfully", updatedTask));
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<ApiResponse<Task>> deleteTask(@PathVariable Long id) {
        Task deletedTask = taskService.deleteTask(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "task deleted successfully", deletedTask));
    }

    @GetMapping("/getTasksByUserId/{id}")
    public ResponseEntity<ApiResponse<Set<Task>>> getTasksByUserId(@PathVariable Long id) {
        Set<Task> tasks = taskService.getTasksByUserId(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "tasks retrived successfully for user", tasks));
    }
}
