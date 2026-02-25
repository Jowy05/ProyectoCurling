package com.urijoel.curling.dto;

/**
 * @author Uri
 */
import java.time.LocalDateTime;

public class ReservationRequestDTO {

    private LocalDateTime date;
    private Integer sheetNumber; //pistas
    private String type; //individual o pareja
    private String player1Id;
    private String player2Id; 

    public ReservationRequestDTO() {
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

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }
}