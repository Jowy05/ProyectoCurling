package com.urijoel.curling.controller;

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
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserRepository userRepository;

    public ReservationController(ReservationService reservationService, UserRepository userRepository) {
        this.reservationService = reservationService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO request) {
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setSheetNumber(request.getSheetNumber());

        User p1 = userRepository.findById(request.getPlayer1Id())
                .orElseThrow(() -> new RuntimeException("Jugador 1 no encontrado"));
        reservation.setPlayer1(p1);

        if (request.getPlayer2Id() != null && !request.getPlayer2Id().isEmpty()) {
            User p2 = userRepository.findById(request.getPlayer2Id())
                    .orElseThrow(() -> new RuntimeException("Jugador 2 no encontrado"));
            reservation.setPlayer2(p2);
        }

        Reservation saved = reservationService.save(reservation);
        return ResponseEntity.ok(toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAll() {
        List<ReservationResponseDTO> dtos = reservationService.getAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getHistory(@PathVariable String userId) {
        List<ReservationResponseDTO> dtos = reservationService.getHistory(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<ReservationResponseDTO>> getAvailability(@RequestParam String date) {
        List<ReservationResponseDTO> dtos = reservationService.getAvailabilityByDate(date).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ReservationResponseDTO toDTO(Reservation res) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(res.getId());
        dto.setDate(res.getDate());
        dto.setSheetNumber(res.getSheetNumber());
        dto.setStatus(res.getStatus());

        if (res.getPlayer1() != null) {
            dto.setPlayer1(new UserDTO(res.getPlayer1().getId(),
                    res.getPlayer1().getName(), res.getPlayer1().getEmail(),
                    res.getPlayer1().getAge(), res.getPlayer1().getSex(),
                    res.getPlayer1().getLevel(), res.getPlayer1().getRole()));
        }
        if (res.getPlayer2() != null) {
            dto.setPlayer2(new UserDTO(res.getPlayer2().getId(),
                    res.getPlayer2().getName(), res.getPlayer2().getEmail(),
                    res.getPlayer2().getAge(), res.getPlayer2().getSex(),
                    res.getPlayer2().getLevel(), res.getPlayer2().getRole()));
        }
        return dto;
    }
}