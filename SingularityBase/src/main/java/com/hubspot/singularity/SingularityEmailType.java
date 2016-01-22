package com.hubspot.singularity;

public enum SingularityEmailType {
  TASK_LOST, TASK_KILLED, TASK_FINISHED_SCHEDULED, TASK_FINISHED_LONG_RUNNING, TASK_FINISHED_ON_DEMAND, TASK_FINISHED_RUN_ONCE, TASK_FAILED, TASK_SCHEDULED_OVERDUE_TO_FINISH,
  TASK_KILLED_DECOMISSIONED, TASK_KILLED_UNHEALTHY, REQUEST_IN_COOLDOWN, SINGULARITY_ABORTING, REQUEST_REMOVED, REQUEST_PAUSED, REQUEST_UNPAUSED, REQUEST_SCALED, TASK_FAILED_DECOMISSIONED
}