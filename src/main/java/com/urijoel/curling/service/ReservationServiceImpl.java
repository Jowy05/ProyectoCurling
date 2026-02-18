/**
 *
 * @author jowyd
 */
package com.urijoel.curling.service;

import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        if ("INDIVIDUAL".equalsIgnoreCase(reservation.getType())) {
            
            Optional<Reservation> waitingGame = repository.findFirstByTypeAndStatusAndDateAndSheetNumber(
                "INDIVIDUAL", 
                "PENDIENTE", 
                reservation.getDate(), 
                reservation.getSheetNumber()
            );

            if (waitingGame.isPresent()) {
                Reservation existing = waitingGame.get();
                
                if (!existing.getPlayer1().getId().equals(reservation.getPlayer1().getId())) {
                    
                    existing.setPlayer2(reservation.getPlayer1());
                    existing.setStatus("CONFIRMADA");
                    
                    return repository.save(existing);
                }
            }
            
            reservation.setStatus("PENDIENTE");
            reservation.setPlayer2(null);
            
        } else {
            reservation.setStatus("CONFIRMADA");
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