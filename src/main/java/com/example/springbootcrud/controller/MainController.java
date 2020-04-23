package com.example.springbootcrud.controller;


import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.RoleService;
import com.example.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
	private final UserService userService;
	
	private final RoleService roleService;
	
	@Autowired
	public MainController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@GetMapping("/admin")
	public String findAll(Model model, @AuthenticationPrincipal User authUser) {
		List<User> users = userService.listUsers();
		model.addAttribute("users", users);
		model.addAttribute("roles", roleService.roleList());
		model.addAttribute("authUser", authUser);
		return "admin";
	}
}
