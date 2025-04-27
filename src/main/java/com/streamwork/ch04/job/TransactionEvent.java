package com.streamwork.ch04.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.streamwork.ch04.api.Event;

/**
 * A simple transaction event used in the fraud detection job.
 */
public class TransactionEvent implements Event {
  public final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  public final String transactionId;
  public final float amount;
  public final Date transactionTime;
  public final long merchandiseId;
  public final long userAccount;

  public TransactionEvent(String transactionId, float amount, Date transactionTime,
      long merchandiseId, long userAccount) {
    this.transactionId = transactionId;
    this.amount = amount;
    this.transactionTime = transactionTime;
    this.merchandiseId = merchandiseId;
    this.userAccount = userAccount;
  }
  
  public TransactionEvent(TransactionEvent e, String objectCaller) {
	  this.transactionId = e.transactionId;
	  
	  // this will create a new ID for event - for downstream components:
	  // - avg ticket analyzer x2
	  // - windowed proximity analyzer x2
	  // for other evaluator windowed transaction count analyzer x2 -> get id from original event
	  // this.transactionId = UUID.randomUUID().toString();
	  
      this.amount = e.amount;
      this.transactionTime = e.transactionTime;
      this.merchandiseId = e.merchandiseId;
      this.userAccount = e.userAccount;
      
      Logger.log("Object caller information: " + objectCaller + " -> \n");
  }

  @Override
  public String toString() {
    return String.format("[transaction:%s; amount:%f; transactionTime: %s; merchandise: %d, user: %d]",
        transactionId, amount, formatter.format(transactionTime), merchandiseId, userAccount);
  }

}
