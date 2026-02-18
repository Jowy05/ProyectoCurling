/**
 *
 * @author jowyd
 */
package com.urijoel.curling.service;

import com.urijoel.curling.model.Reservation;
import java.util.List;

public interface ReservationService {
    
    List<Reservation> getAll();
    
    Reservation findById(String id);
    
    Reservation save(Reservation reservation);
    
    void delete(String id);
    
    List<Reservation> getHistory(String userId);
}
