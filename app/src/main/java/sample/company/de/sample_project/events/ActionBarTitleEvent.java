package sample.company.de.sample_project.events;

import lombok.Data;

@Data
public class ActionBarTitleEvent {

    private final String title;

    public ActionBarTitleEvent(String title) {
        this.title = title;
    }

}
