package ru.qatools.school.data;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * @author lanwen (Merkushev Kirill)
 */
public enum Place implements Matcher<Place> {
    HOME {
        public void describeTo(Description description) {

        }

        public boolean matches(Object o) {
            return false;
        }

        public void describeMismatch(Object o, Description description) {

        }

        public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

        }
    },
    AT_YANDEX {
        public void describeTo(Description description) {

        }

        public boolean matches(Object o) {
            return false;
        }

        public void describeMismatch(Object o, Description description) {

        }

        public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

        }
    }
}
