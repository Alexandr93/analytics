package analytics.parser;

import analytics.models.Line;
import analytics.models.Query;
import analytics.models.Question;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ParseLineImpl implements ParseLine{

    private Line question;
    private final String DATE_FORMAT = "d.MM.yyyy";
    private  final String DATE_SEPARATOR ="-";
    private  final String VERSION_SEPARATOR ="\\.";
    private  final String WORD_SEPARATOR ="\s";
    public Line parse(String lineString) {


        if (lineString.charAt(0) == 'D') {
            question = new Query();

        } else
        {
            question = new Question();
        }


        String[] propArray = lineString.split(WORD_SEPARATOR);
        question.setMessageType(propArray[0]);
        parseServiceType(propArray[1]);
        parseQuestionType(propArray[2]);
        question.setResponseType(propArray[3]);
        setDate(propArray[4]);
        if (propArray[0].equals("C"))
            question.setWaitingTime(Integer.parseInt(propArray[5]));

        return question;
    }



    public void parseServiceType(String service) {

        String[] a = service.split(VERSION_SEPARATOR);
        if (a.length >= 1) {
            question.setServiceId(a[0]);
        }
        if (a.length == 2) {
            question.setVariationId(a[1]);
        }

    }

    public void parseQuestionType(String questionType) {

        String[] a = questionType.split(VERSION_SEPARATOR);
        if (a.length >= 1) {
            question.setQuestionTypeId(a[0]);
        }
        if (a.length >= 2) {
            question.setCategoryId(a[1]);
        }
        if (a.length == 3) {
            question.setSubCategoryId(a[2]);
        }


    }

    public Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        Date date;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        return date;
    }


    public void setDate(String stringDate) {
        String[] dates = stringDate.split(DATE_SEPARATOR);
        List<Date> date = Arrays.stream(dates).map(this::parseDate).toList();
        question.setDate(date);

    }


}
