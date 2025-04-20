package com.streamwork.ch02.job;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class VehicleFeesConfig {
  private static final Map<String, Integer> vehicleFees= new HashMap<>();
  
  static {
      vehicleFees.put("car", 2);
      vehicleFees.put("truck", 3);
      vehicleFees.put("van", 3);
      vehicleFees.put("minivan", 4);
  }
  
  public static int getFeeForVehicleType(String vehicleType) {
	  if (!vehicleFees.containsKey(vehicleType)) {
		  return 0;
	  }
	  
	  return vehicleFees.get(vehicleType);
  }
  
  public static Set<String> getAllVehicleTypes() {
	  return vehicleFees.keySet();
  }
}
