package com.streamwork.ch03.job;

// import com.streamwork.ch03.api.FieldsGrouping;
// import com.streamwork.ch03.api.StringLengthGrouping;
import com.streamwork.ch03.api.FirstCharGrouping;
import com.streamwork.ch03.api.Job;
import com.streamwork.ch03.api.Stream;
import com.streamwork.ch03.engine.JobStarter;

public class ParallelizedVehicleCountJobExercise {

  public static void main(String[] args) {
    Job job = new Job("parallelized_vehicle_count");

    Stream bridgeStream = job.addSource(new SensorReader("sensor-reader", 2, 9990));
    bridgeStream.applyOperator(new VehicleCounter("vehicle-counter", 2, new FirstCharGrouping()));

    System.out.println("This is a streaming job that counts vehicles from the input stream " +
            "in real time. Please enter vehicle types like 'car' and 'truck' in the " +
            "input terminals and look at the output");
    JobStarter starter = new JobStarter(job);
    starter.start();
  }
}
