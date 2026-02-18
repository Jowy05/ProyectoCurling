/**
 *
 * @author jowyd
 */
package com.urijoel.curling.service;

import com.urijoel.curling.model.MatchResult;
import java.util.List;
import java.util.Map;

public interface MatchResultService {
    MatchResult saveResult(MatchResult result);
    List<MatchResult> getResultsByWinner(String userId);
    Map<String, Object> getUserStatistics(String userId);
}
