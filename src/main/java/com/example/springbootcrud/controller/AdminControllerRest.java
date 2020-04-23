package com.example.springbootcrud.controller;

import com.example.springbootcrud.model.Role;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.RoleService;
import com.example.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class AdminControllerRest {
	
	private final UserService userService;
	private final RoleService roleService;
	
	@Autowired
	public AdminControllerRest(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@GetMapping(value = "/admin/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(userService.listUsers());
	}
	
	@GetMapping(value = "/admin/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(userService.getUserByid(id));
	}
	
	@PostMapping(value = "/admin/add")
	public ResponseEntity<User> addUser(@ModelAttribute User user, @RequestParam Long[] roleSet) {
		user.setRoleSet(roleService.getRole(roleSet));
		userService.add(user);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping(value = "/admin/delete/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
		return ResponseEntity.ok().body(true);
	}
	
	@PostMapping(value = "/admin/update")
	public ResponseEntity<User> updateUser(@ModelAttribute User user, @RequestParam Long[] roleSet) {
		user.setRoleSet(roleService.getRole(roleSet));
		userService.update(user);
		return ResponseEntity.ok().body(user);
	}
}
