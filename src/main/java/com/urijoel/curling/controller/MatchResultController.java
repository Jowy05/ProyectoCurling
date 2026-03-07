
/**
 *
 * @author jowyd
 */
package com.urijoel.curling.controller;

import com.urijoel.curling.model.MatchResult;
import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.model.User;
import com.urijoel.curling.repository.UserRepository;
import com.urijoel.curling.service.MatchResultService;
import com.urijoel.curling.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
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
    public ResponseEntity<MatchResult> createResult(@RequestBody Map<String, String> request) {
        String reservationId = request.get("reservationId");
        String winnerId = request.get("winnerId");
        String score = request.get("score");
        String comments = request.get("comments");

        Reservation reservation = reservationService.findById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reserva no encontrada");
        }

        MatchResult result = new MatchResult();
        result.setReservation(reservation);
        result.setScore(score);
        result.setComments(comments);

        if (winnerId != null && !winnerId.isEmpty()) {
            User winner = userRepository.findById(winnerId).orElse(null);
            result.setWinner(winner);
        }

        MatchResult saved = matchResultService.saveResult(result);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/winner/{userId}")
    public ResponseEntity<List<MatchResult>> getResultsByWinner(@PathVariable String userId) {
        return ResponseEntity.ok(matchResultService.getResultsByWinner(userId));
    }

    @GetMapping("/stats/{userId}")
    public ResponseEntity<Map<String, Object>> getUserStatistics(@PathVariable String userId) {
        return ResponseEntity.ok(matchResultService.getUserStatistics(userId));
    }
}