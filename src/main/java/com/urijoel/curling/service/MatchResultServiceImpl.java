package com.urijoel.curling.service;

import com.urijoel.curling.dto.StatsDTO;
import com.urijoel.curling.model.MatchResult;
import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.model.ReservationStatus;
import com.urijoel.curling.repository.MatchResultRepository;
import com.urijoel.curling.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
            reservation.setStatus(ReservationStatus.FINALIZADA);
            reservationRepository.save(reservation);
        }
        return saved;
    }

    @Override
    public List<MatchResult> getAllResults() {
        return matchRepository.findAll();
    }

    @Override
    public List<MatchResult> getResultsByUserId(String userId) {
        return matchRepository.findByParticipantId(userId);
    }

    @Override
    public List<MatchResult> getResultsByWinner(String userId) {
        return matchRepository.findByWinnerId(userId);
    }

    @Override
    public StatsDTO getUserStatistics(String userId) {
        List<MatchResult> allResults = matchRepository.findByParticipantId(userId);
        long played = allResults.size();
        long wins = matchRepository.findByWinnerId(userId).size();
        long losses = played - wins;
        double winRate = played > 0 ? Math.round((wins * 100.0 / played) * 10.0) / 10.0 : 0.0;
        return new StatsDTO(played, wins, losses, winRate);
    }
}
