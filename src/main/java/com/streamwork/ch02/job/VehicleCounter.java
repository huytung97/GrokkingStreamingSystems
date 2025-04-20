package com.streamwork.ch02.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.streamwork.ch02.api.Event;
import com.streamwork.ch02.api.Operator;

class VehicleCounter extends Operator {
  private final Map<String, Integer> countMap = new HashMap<String, Integer>();
  private int totalFees = 0;

  public VehicleCounter(String name) {  super(name);  }

  @Override
  public void apply(Event vehicleEvent, List<Event> eventCollector) {
    String vehicle = ((VehicleEvent)vehicleEvent).getData();
    Integer count = countMap.getOrDefault(vehicle, 0) + 1;
    countMap.put(vehicle, count);
    totalFees += VehicleFeesConfig.getFeeForVehicleType(vehicle);
    
    AggregatedResult ag = new AggregatedResult(totalFees, countMap);
    eventCollector.add(new AggregateResultEvent(ag));
  }
}
