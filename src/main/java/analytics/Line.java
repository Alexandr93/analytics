package analytics;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public abstract class Line {

    private String messageType;
    private String serviceId="0";



    private String variationId ="0";
    private String questionTypeId="0";
    private String categoryId="0";
    private String subCategoryId="0";

    private String responseType;


    private int waitingTime;


    public abstract  void setDate(List<Date> dates);

}
