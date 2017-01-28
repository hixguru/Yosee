package kr.yosee.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import io.reactivex.subjects.PublishSubject;
import java.util.List;

/**
 * Created by hwanik on 2017. 1. 28..
 */

public class DBHelper {
    private ParseQuery<ParseObject> query;

    public DBHelper() {
    }

    public void setQuery(ParseQuery<ParseObject> query) {
        this.query = query;
    }

    public void whereEqualTo(String key, String value) {
        query.whereEqualTo(key, value);
    }

    public PublishSubject<List<ParseObject>> getResult() {
        PublishSubject<List<ParseObject>> result = PublishSubject.create();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e != null) {
                    result.onError(e);
                    return;
                }
                result.onNext(list);
            }
        });
        return result;
    }
}
