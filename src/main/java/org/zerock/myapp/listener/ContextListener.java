package org.zerock.myapp.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public final class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.trace("---------------------------------------");
        log.trace("* contextInitialized(sce) invoked.");
        log.trace("---------------------------------------");

        String contextPath = sce.getServletContext().getContextPath();
        log.info("\t+ contextPath: {}", contextPath);
    } // contextInitialized

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.trace("---------------------------------------");
        log.trace("* contextDestroyed(sce) invoked.");
        log.trace("---------------------------------------");

        String contextPath = sce.getServletContext().getContextPath();
        log.info("\t+ contextPath: {}", contextPath);
    } // contextDestroyed

} // end class
