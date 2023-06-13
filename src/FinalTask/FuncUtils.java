package FinalTask;

import FinalTask.Action1;

import java.util.ArrayList;
import java.util.List;

public class FuncUtils {

    public static <X> void forEach(List<X> list, Action1<X> action1) {
        for (X item : list) {
            action1.apply(item);
        }
    }

    public static <X, Y> List<Y> map(List<X> list, Func1<X, Y> mapFunction) {
        ArrayList<Y> result = new ArrayList<>();
        for (X item : list) {
            result.add(mapFunction.apply(item));
        }
        return result;
    }

    public static <X> List<X> filter(List<X> list, Func1<X, Boolean> filterFunction) {
        ArrayList<X> result = new ArrayList<>();
        for (X item : list) {
            if (filterFunction.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <X> X find(List<X> list, Func1<X, Boolean> findFunction) {
        ArrayList<X> result = new ArrayList<>();
        for (X item : list) {
            if (findFunction.apply(item)) {
                return item;
            }
        }
        return null;
    }

}
