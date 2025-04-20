package com.streamwork.ch02.job;

import java.util.Map;

import com.streamwork.ch02.api.Event;

public class AggregateResultEvent extends Event {
	private AggregatedResult ag;
	
	public AggregateResultEvent(AggregatedResult ag) {
		this.ag = ag;
	}
	
	@Override
	public AggregatedResult getData() {
		return ag;
	}

}
