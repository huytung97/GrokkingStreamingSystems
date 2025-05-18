package com.streamwork.ch07.job;

import com.streamwork.ch07.api.Event;
import com.streamwork.ch07.api.EventCollector;
import com.streamwork.ch07.api.GroupingStrategy;
import com.streamwork.ch07.api.Operator;

public class PrintTransactionScoreEvent extends Operator {
	private static final long serialVersionUID = -6047081853654689680L;
	private int instance;

	public PrintTransactionScoreEvent(String name, int parallelism, GroupingStrategy grouping) {
		super(name, parallelism, grouping);
	}
	
	public PrintTransactionScoreEvent(String name, int parallelism) {
		super(name, parallelism);
	}

	@Override
	public void setupInstance(int instance) {
		// TODO Auto-generated method stub
		this.instance = instance;
	}

	@Override
	public void apply(Event event, EventCollector eventCollector) {
		TransactionScoreEvent txnScore = (TransactionScoreEvent) event;
		Logger.log(String.format("Transaction %s, score: %.2f", 
				txnScore.transaction, txnScore.score));
	}

}
