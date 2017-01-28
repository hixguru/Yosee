package kr.yosee.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.List;

/**
 * Created by hwanik on 2017. 1. 28..
 */

public class DBHelper {
    private ParseQuery<ParseObject> query;
    private PublishSubject<List<ParseObject>> subject;

    public void setQuery(ParseQuery<ParseObject> query) {
        this.query = query;
        subject = PublishSubject.create();
    }

    public void whereEqualTo(String key, String value) {
        query.whereEqualTo(key, value);
    }

    public Subject<List<ParseObject>> getResult() {
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e != null) {
                    subject.onError(e);
                    return;
                }
                subject.onNext(list);
            }
        });
        return subject;
    }
}
