
package be.ugent.systemdesign.ligplaats.application.command;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnloadContainersCommand {
    private Integer berthId;

    public UnloadContainersCommand(Integer berthId){
        this.berthId = berthId;
    }
}
