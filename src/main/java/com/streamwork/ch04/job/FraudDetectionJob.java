package com.streamwork.ch04.job;

import com.streamwork.ch04.api.Job;
import com.streamwork.ch04.api.Stream;
import com.streamwork.ch04.api.Streams;
import com.streamwork.ch04.engine.JobStarter;

public class FraudDetectionJob {

  public static void main(String[] args) {
    Job job = new Job("fraud_detection_job");

    // Create a stream from a source.
    Stream transactionOut = job.addSource(new TransactionSource("transaction source", 1, 9990));
    // One stream can have multiple channels. Different operator can be hooked up
    // to different channels to receive different events. When no channel is selected,
    // the default channel will be used.
    Stream evalResultDummy1 = transactionOut.applyOperator(new OperatorDummyOp1("DummyOperator1",
        	2, new UserAccountFieldsGrouping()));
    
    // block 1
    Stream evalResults1 = evalResultDummy1.applyOperator(new AvgTicketAnalyzer("avg ticket analyzer",
        2, new UserAccountFieldsGrouping()));
    Stream evalResults2 = evalResultDummy1.applyOperator(new WindowedProximityAnalyzer("windowed proximity analyzer",
        2, new UserAccountFieldsGrouping()));
    
    // block 2
    Stream evalResults3 = transactionOut.applyOperator(new WindowedTransactionCountAnalyzer("windowed transaction count analyzer",
        2, new UserAccountFieldsGrouping()));
    

    ScoreStorage store = new ScoreStorage();
    Streams.of(evalResults1, evalResults2, evalResults3)
           .applyOperator(new ScoreAggregator("score aggregator", 2, new GroupByTransactionId(), store));

    Logger.log("This is a streaming job that detect suspicious transactions." +
               "Input needs to be in this format: {amount},{merchandiseId}. For example: 42.00@3." +
               "Merchandises N and N + 1 are 1 seconds walking distance away from each other.");
    JobStarter starter = new JobStarter(job);
    starter.start();
  }
}
