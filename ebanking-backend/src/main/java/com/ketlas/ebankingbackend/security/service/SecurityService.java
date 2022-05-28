package com.ketlas.ebankingbackend.security.service;


import com.ketlas.ebankingbackend.security.entities.AppRole;
import com.ketlas.ebankingbackend.security.entities.AppUser;

import java.util.List;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePwd);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);

    List<AppUser> listUsers();
}
