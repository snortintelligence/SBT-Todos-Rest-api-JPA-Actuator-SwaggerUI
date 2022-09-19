package com.snort.intelli.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snort.intelli.app.entites.Todos;
import com.snort.intelli.app.repository.TodosRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/todos")
@Slf4j
public class TodosController {

	// JPA repository
	@Autowired
	private TodosRepository todosRepository;

	@PostMapping("/create")
	public Todos createTask(@RequestBody Todos todos) {
		log.info("TodosController : createTask executed!");
		todos.setAssignedDate(new Date());
		return todosRepository.save(todos);
	}

	@GetMapping("/")
	public List<Todos> findAll() {
		log.info("TodosController : findAll executed!");
		return (List<Todos>) todosRepository.findAll();
	}

	@GetMapping("/bytitle")
	public List<Todos> findByTitle(@RequestParam("title") String title) {
		log.info("TodosController : findByTitle executed!");
		return (List<Todos>) todosRepository.findByTitleTodosInfo(title);
	}

	@GetMapping("/byassigned")
	public List<Todos> findByAssignedBy(@RequestParam("assignedBy") String assignedBy) {
		log.info("TodosController : findByAssignedBy executed!");
		return (List<Todos>) todosRepository.findAssignedByTodosInfo(assignedBy);
	}

	@GetMapping("/by/level/completed")
	public List<Todos> findByDifficultyLevelAndCompleted(@RequestParam("difficultyLevel") Integer difficultyLevel,
			@RequestParam("completed") String completed) {
		log.info("TodosController : findByAssignedBy executed!");
		return (List<Todos>) todosRepository.findByDifficultyLevelAndCompletedTodosInfo(difficultyLevel, completed);
	}

	@GetMapping("/{id}")
	public Todos findOneTodo(@PathVariable Long id) {
		log.info("TodosController : findOneTodo executed!");
		Optional<Todos> todos = todosRepository.findById(id);
		// return todos.get();
		// return todos.orElse(new Todos());

//		return todos.orElseThrow(() -> {
//			return new RuntimeException("No Todos found for given id : "+id);
//		});
		return todos.orElseGet(() -> {
			return new Todos();
		});
	}

	@DeleteMapping("/delete/{id}")
	public String deleteOneTodo(@PathVariable Long id) {
		log.info("TodosController : deleteOneTodo executed!");
		try {
			todosRepository.deleteById(id);
			return "Todos with id->" + id + " successfully delete!";
		} catch (Exception e) {
			return "Todos with id->" + id + " failed to delete!";
		}
	}

	@PutMapping("/update/{id}")
	public Todos updateOneTodo(@PathVariable Long id, @RequestBody Todos newTodo) {
		newTodo.setTaskId(id);
		newTodo.setUpdatedDate(new Date());
		if (todosRepository.existsById(id)) {
			return todosRepository.save(newTodo);
		}
		return new Todos();
	}

	@PatchMapping("/update/{id}")
	public Todos updateTodo(@PathVariable Long id, @RequestBody Todos newTodo) {
		newTodo.setTaskId(id);
		newTodo.setUpdatedDate(new Date());
		if (todosRepository.existsById(id)) {
			return todosRepository.save(newTodo);
		}
		return new Todos();
	}

}
