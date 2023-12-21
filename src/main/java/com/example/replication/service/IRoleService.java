package com.example.replication.service;


import com.example.replication.entity.Role;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}