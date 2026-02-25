package com.urijoel.curling.controller;

/**
 * @author Uri
 */
import com.urijoel.curling.dto.AuthResponseDTO;
import com.urijoel.curling.dto.LoginRequestDTO;
import com.urijoel.curling.dto.RegisterRequestDTO;
import com.urijoel.curling.model.User;
import com.urijoel.curling.service.UserService;
import com.urijoel.curling.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        //creamos el usuario de base
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());
        user.setSex(request.getSex());
        user.setLevel(request.getLevel());
        
        userService.registerUser(user);
        
        //generamos el token directamente tras registrar
        String token = jwtService.generateToken(userService.loadUserByUsername(user.getEmail()));
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        //validamos credenciales
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        // si va bien, devolveme el token
        String token = jwtService.generateToken(userService.loadUserByUsername(request.getEmail()));
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}