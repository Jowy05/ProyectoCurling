package com.urijoel.curling.service;

import com.urijoel.curling.dto.StatsDTO;
import com.urijoel.curling.model.MatchResult;
import java.util.List;

public interface MatchResultService {
    MatchResult saveResult(MatchResult result);
    List<MatchResult> getAllResults();
    List<MatchResult> getResultsByUserId(String userId);
    List<MatchResult> getResultsByWinner(String userId);
    StatsDTO getUserStatistics(String userId);
}
