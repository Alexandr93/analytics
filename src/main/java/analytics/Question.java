package analytics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Question extends Line {


    private Date eventDate;
    private int waitingTime;

    @Override
    public void setDate(List<Date> dates) {
        setEventDate(dates.get(0));
    }

}
