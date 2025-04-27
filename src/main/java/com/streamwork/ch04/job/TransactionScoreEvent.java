package com.streamwork.ch04.job;

import com.streamwork.ch04.api.Event;

/**
 * A simple transaction event used in the fraud detection job.
 */
public class TransactionScoreEvent implements Event {
  public final TransactionEvent transaction;
  public final float score;

  public TransactionScoreEvent(TransactionEvent transaction, float score) {
    this.transaction = transaction;
    this.score = score;
  }
  
  public TransactionScoreEvent(TransactionEvent transaction, float score, String callerDebug) {
	this.transaction = transaction;
	this.score = score;
	
	Logger.log("Object caller information: " + callerDebug + " -> \n");
  }
}
