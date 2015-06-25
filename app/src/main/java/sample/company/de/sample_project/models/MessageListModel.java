package sample.company.de.sample_project.models;


import lombok.Data;
import sample.company.de.sample_project.enums.FilterType;

@Data
public class MessageListModel {

    private final int id;
    private final String messageText;
    private final FilterType messageType;

    public MessageListModel(int id, String messageText, FilterType messageType) {
        this.id = id;
        this.messageText = messageText;
        this.messageType = messageType;
    }

}
