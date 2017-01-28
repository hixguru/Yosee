package kr.yosee.util;

import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by hwanik on 2017. 1. 28..
 */

public class QueryGenerator {

    private ParseQuery<ParseObject> query;

    public void initTable(String table) {
        query = ParseQuery.getQuery(table);
    }

    public void orderByDescending(String option) {
        query.orderByDescending(option);
    }

    public void whereEqualTo(String key, String value) {
        query.whereEqualTo(key, value);
    }

    public ParseQuery<ParseObject> getQuery() {
        return query;
    }
}
