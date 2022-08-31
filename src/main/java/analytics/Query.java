package analytics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
public class Query extends Line {


    private Date dateFrom;
    private Date dateTo;


    @Override
    public void setDate(List<Date> dates) {
        setDateFrom(dates.get(0));
        if (dates.size() == 1)
            setDateTo(dates.get(0));
        else
            setDateTo(dates.get(1));
    }


}
