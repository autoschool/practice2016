package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 * changed by aasx (Kulikov Yurii)
 */
public class MyFirstTest {
    @Test
   public void userGoesSomeWhere() {

        /*когда мы вызываем user() мы же по сути создаем новый экземпляр класса userSteps, да?
        у меня почему то не получается сделать всю это серию null=>яндекс=>дом для одного экземпляра
        ошибка с методом goTo, точнее его выполнение "через точку",
        хотя для verifyLocation проблемы не наблюдается
        ниже в комментарии код, который не работает, не знаю почему*/

        /*user().verifyLocation(null)
                .goTo(Place.AT_YANDEX);*/

        //ну а дальше рабочий код :)

        user().goTo(null)
                .verifyLocation(null);
        user().goTo(Place.AT_YANDEX)
                .verifyLocation(Place.AT_YANDEX);
        user().goTo(Place.HOME)
                .verifyLocation(Place.HOME);


    }



}
