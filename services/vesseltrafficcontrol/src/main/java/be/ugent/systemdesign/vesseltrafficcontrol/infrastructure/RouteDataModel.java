package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class RouteDataModel {

    @Id
    @Getter
    private Integer routeId;
    @Getter
    private Size sizeCompatibility;

    private Integer cost;
    private String route;
    private Integer routeLength;
    private Integer capacity;
    private Integer destination;

    public RouteDataModel(Integer id, String route, Size sizeCompatibility, Integer routeLength, Integer capacity, Integer dest) {
        this.routeId = id;
        this.route = route;
        this.sizeCompatibility = sizeCompatibility;
        this.routeLength = routeLength;
        this.capacity = capacity;
        this.destination = dest;
        this.calculateCost();
    }

    private void calculateCost(){
        this.cost = 0;
    }
}
