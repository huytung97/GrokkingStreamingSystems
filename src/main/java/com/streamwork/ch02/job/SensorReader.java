package com.streamwork.ch02.job;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.streamwork.ch02.api.Event;
import com.streamwork.ch02.api.Source;

class SensorReader extends Source {
  // private final BufferedReader reader;
  private static final Set<String> vehicleTypes = VehicleFeesConfig.getAllVehicleTypes();
  private static final int minMillis = 1000;
  private static final int maxMillis = 5000;

  public SensorReader(String name) {
    super(name);
  }

  @Override
  public void getEvents(List<Event> eventCollector) {
	    List<String> vehicleTypesList = new ArrayList<>(vehicleTypes);
		String vehicle = vehicleTypesList.get(
			ThreadLocalRandom.current().nextInt(vehicleTypes.size())
		);
		eventCollector.add(new VehicleEvent(vehicle));
		System.out.println(""); // An empty line before logging new events
		System.out.println("SensorReader --> " + vehicle);
		
		// wait sometime before publishing a new event
		int sleepTime = ThreadLocalRandom.current().nextInt(minMillis, maxMillis + 1);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    
  }
}
