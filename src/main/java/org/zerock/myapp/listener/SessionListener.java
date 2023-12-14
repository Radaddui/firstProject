package org.zerock.myapp.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public final class SessionListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionCreated(se) invoked.");
        log.trace("---------------------------------------");

        HttpSession httpSession = se.getSession();
        log.info("\t+ session: {}", httpSession.getId());
    } // sessionCreated

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionDestroyed(se) invoked.");
        log.trace("---------------------------------------");

        HttpSession httpSession = se.getSession();
        log.info("\t+ session: {}", httpSession.getId());
    } // sessionDestroyed

} // end class
