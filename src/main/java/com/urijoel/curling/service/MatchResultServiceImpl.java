/**
 *
 * @author jowyd
 */
package com.urijoel.curling.service;

import com.urijoel.curling.model.MatchResult;
import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.repository.MatchResultRepository;
import com.urijoel.curling.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchResultServiceImpl implements MatchResultService {

    private final MatchResultRepository matchRepository;
    private final ReservationRepository reservationRepository;

    public MatchResultServiceImpl(MatchResultRepository matchRepository, ReservationRepository reservationRepository) {
        this.matchRepository = matchRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public MatchResult saveResult(MatchResult result) {
        MatchResult saved = matchRepository.save(result);

        Reservation reservation = result.getReservation();
        if (reservation != null) {
            reservation.setStatus("FINALIZADA");
            reservationRepository.save(reservation);
        }
        return saved;
    }

    @Override
    public List<MatchResult> getResultsByWinner(String userId) {
        return matchRepository.findByWinnerId(userId);
    }

    @Override
    public Map<String, Object> getUserStatistics(String userId) {
        List<MatchResult> wonMatches = matchRepository.findByWinnerId(userId);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("wins", wonMatches.size());
        
        return stats;
    }
}
