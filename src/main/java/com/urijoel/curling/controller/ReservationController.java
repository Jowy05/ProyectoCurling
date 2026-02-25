package com.urijoel.curling.controller;

/**
 * @author jowyd
 */
import com.urijoel.curling.dto.ReservationRequestDTO;
import com.urijoel.curling.dto.ReservationResponseDTO;
import com.urijoel.curling.dto.UserDTO;
import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.model.User;
import com.urijoel.curling.repository.UserRepository;
import com.urijoel.curling.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserRepository userRepository;

    public ReservationController(ReservationService reservationService, UserRepository userRepository) {
        this.reservationService = reservationService;
        this.userRepository = userRepository;
    }

    // crea una reserva (individual o pareja) [cite: 87, 88]
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO request) {
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setSheetNumber(request.getSheetNumber());
        reservation.setType(request.getType());
        
        // buscamos al jugador 1 en la base de datos
        User p1 = userRepository.findById(request.getPlayer1Id())
                .orElseThrow(() -> new RuntimeException("jugador 1 no encontrado"));
        reservation.setPlayer1(p1);

        // si vienen dos jugadores, buscamos al segundo [cite: 89]
        if (request.getPlayer2Id() != null && !request.getPlayer2Id().isEmpty()) {
            User p2 = userRepository.findById(request.getPlayer2Id())
                    .orElseThrow(() -> new RuntimeException("jugador 2 no encontrado"));
            reservation.setPlayer2(p2);
        }

        Reservation saved = reservationService.save(reservation);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    // devuelve todas las reservas (util para administradores)
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAll() {
        List<ReservationResponseDTO> dtos = reservationService.getAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // consulta el historico de un usuario concreto [cite: 19, 93]
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getHistory(@PathVariable String userId) {
        List<ReservationResponseDTO> dtos = reservationService.getHistory(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // elimina una reserva por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // metodo interno para pasar de modelo a dto seguro [cite: 54]
    private ReservationResponseDTO convertToDTO(Reservation res) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(res.getId());
        dto.setDate(res.getDate());
        dto.setSheetNumber(res.getSheetNumber());
        dto.setType(res.getType());
        dto.setStatus(res.getStatus());
        
        if (res.getPlayer1() != null) {
            dto.setPlayer1(new UserDTO(res.getPlayer1().getId(), res.getPlayer1().getName(), res.getPlayer1().getEmail(), res.getPlayer1().getAge(), res.getPlayer1().getSex(), res.getPlayer1().getLevel(), res.getPlayer1().getRole()));
        }
        if (res.getPlayer2() != null) {
            dto.setPlayer2(new UserDTO(res.getPlayer2().getId(), res.getPlayer2().getName(), res.getPlayer2().getEmail(), res.getPlayer2().getAge(), res.getPlayer2().getSex(), res.getPlayer2().getLevel(), res.getPlayer2().getRole()));
        }
        return dto;
    }
}