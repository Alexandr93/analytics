package analytics;

import analytics.models.Query;
import analytics.models.Question;

import java.util.Date;

public class CheckContains {


    public int checkContains(Query query, Question question) {
        if (serviceEquals(query, question)) {
            return question.getWaitingTime();
        }
        return 0;
    }

    private boolean serviceEquals(Query query, Question question) {
        if (checkDateRange(query.getDateFrom(), query.getDateTo(), question.getEventDate())) {
            if (checkService(query, question) && checkQuestionType(query, question))
            {

                return true;
            }
        }
        return false;
    }

    private boolean checkDateRange(Date dateFrom, Date dateTo, Date eventDate) {
        return dateFrom.before(eventDate) && dateTo.after(eventDate) || (dateFrom.equals(eventDate)) || (dateTo.equals(eventDate));
    }

    private boolean checkService(Query query, Question question) {
        if (query.getServiceId().equals(question.getServiceId())
                || question.getServiceId().equals("0")
                || query.getServiceId().equals("*")) {
            return query.getVariationId().equals(question.getVariationId())
                    || question.getVariationId().equals("0")
                    || query.getVariationId().equals("0");
        }
        return false;
    }

    private boolean checkQuestionType(Query query, Question question) {
        if (query.getQuestionTypeId().equals(question.getQuestionTypeId())
                || query.getQuestionTypeId().equals("*")) {
            if (query.getCategoryId().equals(question.getCategoryId())
                    || question.getCategoryId().equals("0")
                    || query.getCategoryId().equals("0")) {
                return query.getSubCategoryId().equals(question.getSubCategoryId())
                        || question.getSubCategoryId().equals("0")
                        || query.getSubCategoryId().equals("0");
            }
        }

        return false;
    }

}
