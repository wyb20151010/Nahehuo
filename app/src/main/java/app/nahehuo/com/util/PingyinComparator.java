package app.nahehuo.com.util;

import app.nahehuo.com.bean.PersonPartner;
import java.util.Comparator;

/**
 * Created by wyb on 2016/2/16.
 */
public class PingyinComparator implements Comparator<PersonPartner> {

    @Override public int compare(PersonPartner l, PersonPartner r) {
        /*if (l.getPingyintou().equals("#")) {
            return -1;
        }
        else if (r.getPingyintou().equals("#")) {
            return 1;
        }
        else {*/
            return l.getPingyintou().compareTo(r.getPingyintou());
        /*}*/
    }


    @Override public boolean equals(Object object) {
        return false;
    }
}
