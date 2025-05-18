package com.streamwork.ch07.job;

import com.streamwork.ch07.api.Event;
import com.streamwork.ch07.api.FieldsGrouping;

public class MerchantIdFieldsGrouping implements FieldsGrouping {
	private static final long serialVersionUID = -4743988225701662112L;

	@Override
	public Object getKey(Event event) {
		// TODO Auto-generated method stub
		TransactionEvent e = (TransactionEvent) event;
		return e.merchandiseId;
	}

}
