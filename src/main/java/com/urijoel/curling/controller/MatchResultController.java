package com.urijoel.curling.controller;

import com.urijoel.curling.dto.MatchResultRequestDTO;
import com.urijoel.curling.dto.MatchResultResponseDTO;
import com.urijoel.curling.dto.StatsDTO;
import com.urijoel.curling.dto.UserDTO;
import com.urijoel.curling.model.MatchResult;
import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.model.User;
import com.urijoel.curling.repository.UserRepository;
import com.urijoel.curling.service.MatchResultService;
import com.urijoel.curling.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "http://localhost:4200")
public class MatchResultController {

    private final MatchResultService matchResultService;
    private final ReservationService reservationService;
    private final UserRepository userRepository;

    public MatchResultController(MatchResultService matchResultService, ReservationService reservationService, UserRepository userRepository) {
        this.matchResultService = matchResultService;
        this.reservationService = reservationService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<MatchResultResponseDTO> createResult(@RequestBody MatchResultRequestDTO request) {
        Reservation reservation = reservationService.findById(request.getReservationId());
        if (reservation == null) {
            throw new IllegalArgumentException("Reserva no encontrada");
        }

        MatchResult result = new MatchResult();
        result.setReservation(reservation);
        result.setScore(request.getScore());
        result.setComments(request.getComments());

        if (request.getWinnerId() != null && !request.getWinnerId().isEmpty()) {
            User winner = userRepository.findById(request.getWinnerId()).orElse(null);
            result.setWinner(winner);
        }

        MatchResult saved = matchResultService.saveResult(result);
        return ResponseEntity.ok(toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<MatchResultResponseDTO>> getAllResults() {
        List<MatchResultResponseDTO> dtos = matchResultService.getAllResults().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MatchResultResponseDTO>> getResultsByUser(@PathVariable String userId) {
        List<MatchResultResponseDTO> dtos = matchResultService.getResultsByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/winner/{userId}")
    public ResponseEntity<List<MatchResultResponseDTO>> getResultsByWinner(@PathVariable String userId) {
        List<MatchResultResponseDTO> dtos = matchResultService.getResultsByWinner(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/stats/{userId}")
    public ResponseEntity<StatsDTO> getUserStatistics(@PathVariable String userId) {
        return ResponseEntity.ok(matchResultService.getUserStatistics(userId));
    }

    private MatchResultResponseDTO toDTO(MatchResult result) {
        MatchResultResponseDTO dto = new MatchResultResponseDTO();
        dto.setId(result.getId());
        dto.setScore(result.getScore());
        dto.setComments(result.getComments());
        dto.setRecordedAt(result.getRecordedAt());

        if (result.getReservation() != null) {
            dto.setReservationId(result.getReservation().getId());
        }

        if (result.getWinner() != null) {
            User w = result.getWinner();
            dto.setWinner(new UserDTO(w.getId(), w.getName(), w.getEmail(), w.getAge(), w.getSex(), w.getLevel(), w.getRole()));
        }

        return dto;
    }
}