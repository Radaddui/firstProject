package org.zerock.myapp.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public final class SessionActivationListener implements HttpSessionActivationListener {


    // Notification that the session is about to be passivated.
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionWillPassivate(se) invoked.");
        log.trace("---------------------------------------");

        log.info("\t+ session: {}", se.getSession().getId());
    } // sessionWillPassivate

    // Notification that the session has just been activated.
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionDidActivate(se) invoked.");
        log.trace("---------------------------------------");

        log.info("\t+ session: {}", se.getSession().getId());
    } // sessionDidActivate

} // end class
