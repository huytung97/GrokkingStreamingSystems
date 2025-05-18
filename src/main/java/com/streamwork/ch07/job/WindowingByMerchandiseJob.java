package com.streamwork.ch07.job;

import com.streamwork.ch07.api.FixedTimeWindowingStrategy;
import com.streamwork.ch07.api.Job;
import com.streamwork.ch07.engine.JobStarter;

public class WindowingByMerchandiseJob {
	private static final long FIXED_WINDOW_INTERVAL_MS = 5 * 1000;
	private static final long FIXED_WINDOW_WATERMARK_MS = 2 * 1000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Job job = new Job("Windowing by merchandise job");
		
		
		job.addSource(new TransactionSource("Transaction Source", 1, 9990))
			.withWindowing(new FixedTimeWindowingStrategy(FIXED_WINDOW_INTERVAL_MS, FIXED_WINDOW_WATERMARK_MS))
	        .applyOperator(new AnalyzerAvgTxnValMerchant("AvgValMerchandise", 2, new MerchantIdFieldsGrouping()))
	        // TODO: check why this operator is not applied
			.applyOperator(new PrintTransactionScoreEvent("Print Transaction Score", 1))
	    ;

		Logger.log("This is a streaming job that works with a windowed strategy and a windowed operator." +
	               "Input needs to be in this format: {amount},{merchandiseId}. For example: 42.00@3.");

		JobStarter starter = new JobStarter(job);
	    starter.start();
	}

}
