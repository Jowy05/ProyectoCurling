package com.urijoel.curling.dto;

/**
 * @author jowyd
 */
import java.time.LocalDateTime;

public class ReservationResponseDTO {

    private String id;
    private LocalDateTime date;
    private Integer sheetNumber;
    private String type;
    private String status;
    
    private UserDTO player1;
    private UserDTO player2;

    public ReservationResponseDTO() {
    }

    // getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(Integer sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getPlayer1() {
        return player1;
    }

    public void setPlayer1(UserDTO player1) {
        this.player1 = player1;
    }

    public UserDTO getPlayer2() {
        return player2;
    }

    public void setPlayer2(UserDTO player2) {
        this.player2 = player2;
    }
}