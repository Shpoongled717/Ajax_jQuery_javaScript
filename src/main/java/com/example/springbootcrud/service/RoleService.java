package com.example.springbootcrud.service;

import com.example.springbootcrud.dao.RoleDao;
import com.example.springbootcrud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;


    public Set<Role> roleList() {
        return roleDao.roleList();
    }

    public Set<Role> getRole(Long ids[]) {
        return roleDao.getRoleById(ids);
    }

    public Role getRole(String name) {
        return roleDao.getRoleByName(name);
    }
}
