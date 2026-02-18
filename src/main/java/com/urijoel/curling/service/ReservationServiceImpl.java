/**
 *
 * @author jowyd
 */
package com.urijoel.curling.service;

import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("reservationServiceImpl")
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
        if (reservation.getStatus() == null) {
            reservation.setStatus("PENDIENTE");
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
}
