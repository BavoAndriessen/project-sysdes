package com.projectsysdes.containermanagement.domain;

import lombok.Getter;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public enum ContainerState {
    REGISTERED,
    ARRIVED,
    TRANSIT_NOT_APPROVED,
    TRANSIT_APPROVED,
    READY_TO_LOAD,
    LOADED,
    RELEASED;

    @Getter
    private static final Map<ContainerState, EnumSet<ContainerState>> allowedStateChanges = new EnumMap<>(ContainerState.class);
    static {
        allowedStateChanges.put(REGISTERED, EnumSet.of(ARRIVED));
        allowedStateChanges.put(ARRIVED, EnumSet.of(TRANSIT_NOT_APPROVED));
        allowedStateChanges.put(TRANSIT_NOT_APPROVED, EnumSet.of(TRANSIT_APPROVED));
        allowedStateChanges.put(TRANSIT_APPROVED, EnumSet.of(READY_TO_LOAD));
        allowedStateChanges.put(READY_TO_LOAD, EnumSet.of(LOADED));
        allowedStateChanges.put(LOADED, EnumSet.of(RELEASED));
    }
    @Getter
    private static final Map<ContainerState, Boolean> requiresNewLocation = new EnumMap<>(ContainerState.class);
    static {
        requiresNewLocation.put(REGISTERED, false);
        requiresNewLocation.put(ARRIVED, true);
        requiresNewLocation.put(TRANSIT_NOT_APPROVED, true);
        requiresNewLocation.put(TRANSIT_APPROVED, false);
        requiresNewLocation.put(READY_TO_LOAD, true);
        requiresNewLocation.put(LOADED, true);
        requiresNewLocation.put(RELEASED, false);
    }
}
