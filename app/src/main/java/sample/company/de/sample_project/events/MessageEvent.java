package sample.company.de.sample_project.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode
@ToString
@Data
public class MessageEvent {

    private String message;
    private String type;

    public MessageEvent(String msg) {
        this.setMessage(msg);
    }

}
