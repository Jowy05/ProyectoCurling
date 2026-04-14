package com.urijoel.curling.repository;

import com.urijoel.curling.model.MatchResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchResultRepository extends MongoRepository<MatchResult, String> {

    List<MatchResult> findByWinnerId(String winnerId);

    MatchResult findByReservationId(String reservationId);

    @Query("{ '$or': [ { 'reservation.player1._id': ?0 }, { 'reservation.player2._id': ?0 } ] }")
    List<MatchResult> findByParticipantId(String userId);
}
