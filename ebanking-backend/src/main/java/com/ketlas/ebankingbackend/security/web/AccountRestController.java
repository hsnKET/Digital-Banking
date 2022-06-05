package com.ketlas.ebankingbackend.security.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketlas.ebankingbackend.security.JWTUtil;
import com.ketlas.ebankingbackend.security.entities.AppRole;
import com.ketlas.ebankingbackend.security.entities.AppUser;
import com.ketlas.ebankingbackend.security.entities.RoleUserForm;
import com.ketlas.ebankingbackend.security.service.SecurityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="http://localhost:4200")
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

    @CrossOrigin("*")
    @PostMapping(path="/login")
    public void login(@RequestBody AppUser appUser){
    }


    @PostMapping(path="/findUser/{token}")
    public AppUser getUserByToken(@PathVariable("token") String token,
                               HttpServletRequest request){
        AppUser appUser = null;
        String auhToken = request.getHeader(JWTUtil.AUTH_HEADER);
        if(auhToken!=null && auhToken.startsWith(JWTUtil.PREFIX)) {
            String jwt = auhToken.substring(JWTUtil.PREFIX.length());
            Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            String username = decodedJWT.getSubject();
            appUser = accountService.loadUserByUsername(username);
        }
        return appUser;

    }
    @GetMapping(path="/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String auhToken=request.getHeader(JWTUtil.AUTH_HEADER);
        if(auhToken!=null && auhToken.startsWith(JWTUtil.PREFIX)) {
            try {
                String jwt = auhToken.substring(JWTUtil.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getAppRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw e;
            }
        }
        else{
            throw new RuntimeException("Error:Rfersh token required!");
        }

    }

    @GetMapping(path="/profile")
    public AppUser profile(Principal principal){
        return accountService.loadUserByUsername(principal.getName());
    }

}






