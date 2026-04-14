package com.urijoel.curling.dto;

import java.time.LocalDateTime;

public class MatchResultResponseDTO {

    private String id;
    private String reservationId;
    private UserDTO winner;
    private String score;
    private String comments;
    private LocalDateTime recordedAt;

    public MatchResultResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public UserDTO getWinner() {
        return winner;
    }

    public void setWinner(UserDTO winner) {
        this.winner = winner;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
