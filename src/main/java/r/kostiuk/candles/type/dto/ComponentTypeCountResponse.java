package r.kostiuk.candles.type.dto;

import r.kostiuk.candles.type.ComponentType;

public record ComponentTypeCountResponse(ComponentTypeResponse type, long componentCount) {
    public ComponentTypeCountResponse(ComponentType type, long componentCount) {
        this(new ComponentTypeResponse(type.getId(), type.getName(), type.getMeasurement()), componentCount);
    }
}
