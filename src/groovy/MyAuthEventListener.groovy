package reception.desk

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event. AuthenticationSuccessEvent

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 7/8/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
class MyAuthEventListener implements ApplicationListener<AuthenticationSuccessEvent>  {

    void onApplicationEvent(AuthenticationSuccessEvent event) {
        // handle the event

    }
}
