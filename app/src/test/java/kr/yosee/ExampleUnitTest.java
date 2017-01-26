package kr.yosee;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    enum Irrelevant {
        INSTANCE1,
        INSTANCE2,
        INSTANCE3,
    }

    @Test
    public void nullObservable() {
        Observable<Object> source = Observable.create((ObservableEmitter<Object> emitter) -> {
            System.out.println("Side-effect 1");
            emitter.onNext(Irrelevant.INSTANCE1);

            System.out.println("Side-effect 2");
            emitter.onNext(Irrelevant.INSTANCE2);

            System.out.println("Side-effect 3");
            emitter.onNext(Irrelevant.INSTANCE3);
        });
        source.subscribe(System.out::println, e -> { /* Ignored. */ });
    }
    /**
     * 결과값
     * Side-effect 1
     * INSTANCE1
     * Side-effect 2
     * INSTANCE2
     * Side-effect 3
     * INSTANCE3
     */
}
