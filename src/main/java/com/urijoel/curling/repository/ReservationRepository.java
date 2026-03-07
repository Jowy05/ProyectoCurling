/**
 *
 * @author jowyd
 */
package com.urijoel.curling.repository;

import com.urijoel.curling.model.Reservation;
import com.urijoel.curling.model.ReservationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    
    @Query("{ '$or': [ { 'player1.id': ?0 }, { 'player2.id': ?0 } ] }")
    List<Reservation> findByUserId(String userId);

    Optional<Reservation> findFirstByStatusAndDateAndSheetNumber(ReservationStatus status, LocalDateTime date, Integer sheetNumber);

    List<Reservation> findByDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}