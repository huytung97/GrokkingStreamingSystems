package com.streamwork.ch04.job;

import com.streamwork.ch04.api.Event;
import com.streamwork.ch04.api.EventCollector;
import com.streamwork.ch04.api.GroupingStrategy;
import com.streamwork.ch04.api.Operator;


public class OperatorDummyOp1 extends Operator {
	private static final long serialVersionUID = -6382266261349772451L;
	private int instance;

	public OperatorDummyOp1(String name, int parallelism, GroupingStrategy grouping) {
		super(name, parallelism, grouping);
	}

	@Override
	public void setupInstance(int instance) {
		this.instance = instance;
	}

	@Override
	public void apply(Event event, EventCollector eventCollector) {
		TransactionEvent e = (TransactionEvent) event;
		String debugClass = this.getClass().getSimpleName();
		eventCollector.add(
				new TransactionEvent(e, debugClass));
	}

}
