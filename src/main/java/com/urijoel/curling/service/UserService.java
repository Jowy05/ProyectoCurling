/**
 *
 * @author jowyd
 */
package com.urijoel.curling.service;

import com.urijoel.curling.model.User;
import com.urijoel.curling.model.Role;
import com.urijoel.curling.repository.UserRepository;
import com.urijoel.curling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok nos crea el constructor con los "final" automáticamente
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Para encriptar contraseña

    /**
     * Registra un nuevo usuario cifrando su clave.
     */
    public User registerUser(User user) {
        // 1. Validar email único
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        // 2. Asignar ROL por defecto si no viene (USER)
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        // 3. Encriptar contraseña (Nunca guardar en texto plano)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 4. Guardar
        return userRepository.save(user);
    }

    // Método para buscar usuario (usado en login o perfil)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // Listar todos (para admin)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}