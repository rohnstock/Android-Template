package sample.company.de.sample_project.events;

import lombok.Data;
import sample.company.de.sample_project.enums.FilterType;

@Data
public class FilterListEvent {

    private final FilterType filterType;

    public FilterListEvent(FilterType filter_type) {
        this.filterType = filter_type;
    }

}


