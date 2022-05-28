package com.ketlas.ebankingbackend.security.web;

import com.ketlas.ebankingbackend.security.entities.AppRole;
import com.ketlas.ebankingbackend.security.entities.AppUser;
import com.ketlas.ebankingbackend.security.entities.RoleUserForm;
import com.ketlas.ebankingbackend.security.service.SecurityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountRestController{
    private SecurityService accountService;
    public AccountRestController(SecurityService accountService){
        this.accountService = accountService;
    }
    @GetMapping(path="/users")
    public List<AppUser> appUsers(){
        return accountService.listUsers();
    }

    @PostMapping(path="/roles")
    @CrossOrigin("*")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.saveNewRole(appRole.getRoleName(),appRole.getDescription());
    }
    @PostMapping(path="/users")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.saveNewUser(appUser.getUsername(),appUser.getPassword(),appUser.getPassword());
    }

    @PostMapping(path="/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }



}





