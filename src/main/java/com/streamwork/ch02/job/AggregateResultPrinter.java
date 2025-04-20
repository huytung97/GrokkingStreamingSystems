package com.streamwork.ch02.job;

import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.List;

import com.streamwork.ch02.api.Event;
import com.streamwork.ch02.api.Operator;

public class AggregateResultPrinter extends Operator {
	public AggregateResultPrinter(String name) {
		super(name);
	}

	@Override
	public void apply(Event event, List<Event> eventCollector) {
		// TODO Auto-generated method stub
		AggregatedResult ag = ((AggregateResultEvent) event).getData();
		Map<String, Integer> countMap = ag.getCountMap();
		int totalFees = ag.getTotalFees();
		
		System.out.println("[AggregatedResultPrinter] VehicleCounter --> ");
	    printCountMap(countMap);
	    System.out.println("Total Fees: " + totalFees);
	}
	
	private void printCountMap(Map<String, Integer> countMap) {
	    List<String> vehicles = new ArrayList<>(countMap.keySet());
	    Collections.sort(vehicles);
	
	    for (String vehicle: vehicles) {
	      System.out.println("  " + vehicle + ": " + countMap.get(vehicle));
	    }
	}
}
