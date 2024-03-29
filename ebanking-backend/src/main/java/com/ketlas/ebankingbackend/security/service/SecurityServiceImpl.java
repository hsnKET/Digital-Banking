package com.ketlas.ebankingbackend.security.service;

import com.ketlas.ebankingbackend.security.entities.AppRole;
import com.ketlas.ebankingbackend.security.entities.AppUser;
import com.ketlas.ebankingbackend.security.repositories.AppRoleRepository;
import com.ketlas.ebankingbackend.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;



@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserReository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePwd) {
        if (!password.equals(rePwd)) throw new  RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserReository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole != null)
            throw new RuntimeException("Role "+roleName+" already exist!!!");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserReository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null)
            throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserReository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null)
            throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserReository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserReository.findAll();
    }
}
