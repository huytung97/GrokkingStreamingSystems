package com.streamwork.ch07.job;

import com.streamwork.ch07.api.Event;
import com.streamwork.ch07.api.EventCollector;
import com.streamwork.ch07.api.EventWindow;
import com.streamwork.ch07.api.GroupingStrategy;
import com.streamwork.ch07.api.WindowOperator;

public class AnalyzerAvgTxnValMerchant extends WindowOperator {
	private int instance;
	
	public AnalyzerAvgTxnValMerchant(String name, int parallelism, GroupingStrategy grouping) {
		super(name, parallelism, grouping);
	}

	@Override
	public void apply(EventWindow window, EventCollector eventCollector) {
		Logger.log(String.format("%d transactions are received between %d and %d\n",
		        window.getEvents().size(), window.getStartTime(), window.getEndTime()));
		
		int numEvents = window.getEvents().size();
		float totalAmount = 0;
		
	    for (Event event: window.getEvents()) {
	      Logger.log(String.format("Event: %s\n", event));
	      totalAmount += ((TransactionEvent) event).amount;
	    }
	    
	    Logger.log(String.format("Average score: %.2f\n", totalAmount / numEvents));
	    
	    float avgAmount = totalAmount / numEvents;
	    float currentWindowScore = 0;
	    if (avgAmount > 5) {
	    	currentWindowScore = 1;
	    }
	    
	    for (Event event : window.getEvents()) {
	    	eventCollector.add(
    			new TransactionScoreEvent((TransactionEvent) event, currentWindowScore)
    			);
	    }
	}

	@Override
	public void setupInstance(int instance) {
		this.instance = instance;
	}

}
