package com.urijoel.curling.controller;

/**
 *
 * @author Uri
 */
import com.urijoel.curling.dto.UserDTO;
import com.urijoel.curling.model.User;
import com.urijoel.curling.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //permite al usuario ver su propia ficha de aventurero
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile() {
        //extraemos el email del  token que viene en la peticion
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        // transformamos el usuario en dto
        UserDTO dto = new UserDTO(
            user.getId(), 
            user.getName(), 
            user.getEmail(), 
            user.getAge(), 
            user.getSex(), 
            user.getLevel(), 
            user.getRole()
        );

        return ResponseEntity.ok(dto);
    }

    // permite al usuario actualizar sus datos de perfil
    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO updates) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // actualizamos 
        user.setName(updates.getName());
        user.setAge(updates.getAge());
        user.setSex(updates.getSex());
        user.setLevel(updates.getLevel());

        userRepository.save(user);

        // devolve los datos actualizados paraangular
        return ResponseEntity.ok(updates);
    }
}