/**
 *
 * @author jowyd
 */
package com.urijoel.curling.repository;

import com.urijoel.curling.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    
    @Query("{ '$or': [ { 'player1.id': ?0 }, { 'player2.id': ?0 } ] }")
    List<Reservation> findByUserId(String userId);
}
