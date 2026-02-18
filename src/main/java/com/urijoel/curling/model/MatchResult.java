/**
 *
 * @author jowyd
 */
package com.urijoel.curling.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "match_results")
public class MatchResult {

    @Id
    private String id;

    @DBRef
    private Reservation reservation;

    @DBRef
    private User winner;

    private String score;
    private String comments;
    private LocalDateTime recordedAt;

    public MatchResult() {
        this.recordedAt = LocalDateTime.now();
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public User getWinner() { return winner; }
    public void setWinner(User winner) { this.winner = winner; }

    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}
