package com.urijoel.curling.service;

import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.model.ReservationStatus;
import com.urijoel.curling.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reservation> getAll() {
        return repository.findAll();
    }

    @Override
    public Reservation findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getPlayer2() == null) {
            Optional<Reservation> waitingGame = repository.findFirstByStatusAndDateAndSheetNumber(
                ReservationStatus.PENDIENTE,
                reservation.getDate(),
                reservation.getSheetNumber()
            );

            if (waitingGame.isPresent()) {
                Reservation existing = waitingGame.get();
                if (!existing.getPlayer1().getId().equals(reservation.getPlayer1().getId())) {
                    existing.setPlayer2(reservation.getPlayer1());
                    existing.setStatus(ReservationStatus.CONFIRMADA);
                    return repository.save(existing);
                }
            }
            reservation.setStatus(ReservationStatus.PENDIENTE);
        } else {
            reservation.setStatus(ReservationStatus.CONFIRMADA);
        }

        return repository.save(reservation);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Reservation> getHistory(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Reservation> getAvailabilityByDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return repository.findByDateBetween(startOfDay, endOfDay);
    }
}
