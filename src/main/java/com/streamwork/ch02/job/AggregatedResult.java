package com.streamwork.ch02.job;

import java.util.Map;

public class AggregatedResult {
  private int totalFees;
  private Map<String, Integer> countMap;
  
  public AggregatedResult(int totalFees, Map<String, Integer> countMap) {
	this.totalFees = totalFees;
	this.countMap = countMap;
  }
  
  public int getTotalFees() {
	return totalFees;
  }
  
  public Map<String, Integer> getCountMap() {
	return countMap;
  } 
}
