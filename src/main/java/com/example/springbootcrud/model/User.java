package com.example.springbootcrud.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String username;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roleSet;
	
	public User() {
	}
	
	public User(String email, String password, String username) {
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	public User(String email, String password, String username, Set<Role> roleSet) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.roleSet = roleSet;
	}
	
	public User(Long id, String email, String password, String username, Set<Role> roleSet) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.roleSet = roleSet;
	}
	
	public Set<Role> getRoleSet() {
		return roleSet;
	}
	
	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoleSet();
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
