package com.hubspot.singularity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.primitives.Longs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema(description = "Data collected to identify disasters")
public class SingularityDisasterDataPoint
  implements Comparable<SingularityDisasterDataPoint> {
  private final long timestamp;
  private final int numActiveTasks;
  private final int numPendingTasks;
  private final int numLateTasks;
  private final long avgTaskLagMillis;
  private final int numLostTasks;
  private final int numActiveSlaves;
  private final int numLostSlaves;

  @JsonCreator
  public SingularityDisasterDataPoint(
    @JsonProperty("timestamp") long timestamp,
    @JsonProperty("numActiveTasks") int numActiveTasks,
    @JsonProperty("numPendingTasks") int numPendingTasks,
    @JsonProperty("numLateTasks") int numLateTasks,
    @JsonProperty("avgTaskLagMillis") long avgTaskLagMillis,
    @JsonProperty("numLostTasks") int numLostTasks,
    @JsonProperty("numActiveSlaves") int numActiveSlaves,
    @JsonProperty("numLostSlaves") int numLostSlaves
  ) {
    this.timestamp = timestamp;
    this.numActiveTasks = numActiveTasks;
    this.numPendingTasks = numPendingTasks;
    this.numLateTasks = numLateTasks;
    this.avgTaskLagMillis = avgTaskLagMillis;
    this.numLostTasks = numLostTasks;
    this.numActiveSlaves = numActiveSlaves;
    this.numLostSlaves = numLostSlaves;
  }

  @Schema(description = "The time this data was collected")
  public long getTimestamp() {
    return timestamp;
  }

  @Schema(description = "A count of active tasks")
  public int getNumActiveTasks() {
    return numActiveTasks;
  }

  @Schema(description = "A count of pending tasks")
  public int getNumPendingTasks() {
    return numPendingTasks;
  }

  @Schema(description = "A count of late tasks")
  public int getNumLateTasks() {
    return numLateTasks;
  }

  @Schema(description = "The average task lag for all pending tasks")
  public long getAvgTaskLagMillis() {
    return avgTaskLagMillis;
  }

  @Schema(description = "A count of lost tasks")
  public int getNumLostTasks() {
    return numLostTasks;
  }

  @Schema(description = "A count of active agents")
  public int getNumActiveSlaves() {
    return numActiveSlaves;
  }

  @Schema(description = "A count of agents lost since the last data point was collected")
  public int getNumLostSlaves() {
    return numLostSlaves;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SingularityDisasterDataPoint that = (SingularityDisasterDataPoint) o;
    return (
      timestamp == that.timestamp &&
      numActiveTasks == that.numActiveTasks &&
      numPendingTasks == that.numPendingTasks &&
      numLateTasks == that.numLateTasks &&
      avgTaskLagMillis == that.avgTaskLagMillis &&
      numLostTasks == that.numLostTasks &&
      numActiveSlaves == that.numActiveSlaves &&
      numLostSlaves == that.numLostSlaves
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      timestamp,
      numActiveTasks,
      numPendingTasks,
      numLateTasks,
      avgTaskLagMillis,
      numLostTasks,
      numActiveSlaves,
      numLostSlaves
    );
  }

  @Override
  public String toString() {
    return (
      "SingularityDisasterDataPoint{" +
      "timestamp=" +
      timestamp +
      ", numActiveTasks=" +
      numActiveTasks +
      ", numPendingTasks=" +
      numPendingTasks +
      ", numLateTasks=" +
      numLateTasks +
      ", avgTaskLagMillis=" +
      avgTaskLagMillis +
      ", numLostTasks=" +
      numLostTasks +
      ", numActiveSlaves=" +
      numActiveSlaves +
      ", numLostSlaves=" +
      numLostSlaves +
      '}'
    );
  }

  @Override
  public int compareTo(SingularityDisasterDataPoint o) {
    return Longs.compare(o.getTimestamp(), this.timestamp);
  }
}
