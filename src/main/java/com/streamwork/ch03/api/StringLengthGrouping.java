package com.streamwork.ch03.api;

import java.io.Serializable;

public class StringLengthGrouping implements GroupingStrategy, Serializable {
	private static final long serialVersionUID = -2235331373325484744L;
	
	protected Object getEvent(Event event) {
		return event.getData();
	}

	@Override
	public int getInstance(Event event, int parallelism) {
		Object eventData = getEvent(event);
		String eventDataValue = eventData.toString();
		
		return eventDataValue.length() % parallelism;
	}

}
