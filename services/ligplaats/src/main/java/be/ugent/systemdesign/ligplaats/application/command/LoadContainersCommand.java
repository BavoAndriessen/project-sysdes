
package be.ugent.systemdesign.ligplaats.application.command;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadContainersCommand {
    private Integer berthId;

    public LoadContainersCommand(Integer berthId){
        this.berthId = berthId;
    }
}
