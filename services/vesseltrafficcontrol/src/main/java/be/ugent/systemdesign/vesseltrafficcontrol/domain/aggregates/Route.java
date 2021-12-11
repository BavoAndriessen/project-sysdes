package be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Route {

    private final Integer routeId;
    private final Size sizeCompatiblity;

    // Simple formula to calculate the cost of using this route
    private Integer cost;
    private List<Integer> route;
    private Integer routeLength;
    private Integer capacity;

    public Route(Integer id, List<Integer> route, Size sizeCompatiblity, Integer routeLength, Integer capacity) {
        this.routeId = id;
        this.route = route;
        this.sizeCompatiblity = sizeCompatiblity;
        this.routeLength = routeLength;
        this.capacity = capacity;
        calculateCost();
    }

    public void updateRoute(List<Integer> newRoute, Integer newLength) {
        this.route = newRoute;
        this.routeLength = newLength;
        this.calculateCost();
    }

    private void calculateCost(){
        this.cost = 0;
    }
}
