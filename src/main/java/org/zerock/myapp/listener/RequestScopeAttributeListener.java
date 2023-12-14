package org.zerock.myapp.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public final class RequestScopeAttributeListener implements ServletRequestAttributeListener {


	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
    	if(
			/* About Spring Framework */
			event.getName().startsWith("org.springframework") ||
			event.getName().startsWith("org.apache") ||
			event.getName().startsWith("javax.servlet") ||
			event.getName().endsWith(".FILTERED") ||

			/* About Spring Boot */
			event.getName().startsWith("__spring") ||
			event.getName().startsWith("thymeleaf") ||
			event.getName().startsWith("spring") ||

			/* About Stack Trace */
			event.getName().equals("trace") ||
			event.getName().equals("traceStat") ||

			/* About Jakarta Servlet/Jsp */
			event.getName().startsWith("jakarta.")
		) {
    		return;
    	} // if

		log.trace("---------------------------------------");
    	log.trace("* attributeRemoved(event) invoked.");
		log.trace("---------------------------------------");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: {}", name);
    	log.info("\t+ value: {}", value);
		log.info("\t+ type: {}", value.getClass().getName());
    } // attributeRemoved

	@Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
    	if(
			/* About Spring Framework */
			event.getName().startsWith("org.springframework") ||
			event.getName().startsWith("org.apache") ||
			event.getName().startsWith("javax.servlet") ||
			event.getName().endsWith(".FILTERED") ||

			/* About Spring Boot */
			event.getName().startsWith("__spring") ||
			event.getName().startsWith("thymeleaf") ||
			event.getName().startsWith("spring") ||

			/* About Stack Trace */
			event.getName().equals("trace") ||
			event.getName().equals("traceStat") ||

			/* About Jakarta Servlet/Jsp */
			event.getName().startsWith("jakarta.")
		) {
    		return;
    	} // if

		log.trace("---------------------------------------");
    	log.trace("* attributeAdded(event) invoked.");
		log.trace("---------------------------------------");
    	
    	String name = event.getName();
    	Object value = event.getValue();

		log.info("\t+ name: {}", name);
		log.info("\t+ value: {}", value);
		log.info("\t+ type: {}", value.getClass().getName());
    } // attributeAdded

	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
    	if(
			/* About Spring Framework */
			event.getName().startsWith("org.springframework") ||
			event.getName().startsWith("org.apache") ||
			event.getName().startsWith("javax.servlet") ||
			event.getName().endsWith(".FILTERED") ||

			/* About Spring Boot */
			event.getName().startsWith("__spring") ||
			event.getName().startsWith("thymeleaf") ||
			event.getName().startsWith("spring") ||

			/* About Stack Trace */
			event.getName().equals("trace") ||
			event.getName().equals("traceStat") ||

			/* About Jakarta Servlet/Jsp */
			event.getName().startsWith("jakarta.")
		) {
    		return;
    	} // if

		log.trace("---------------------------------------");
    	log.trace("* attributeReplaced(event) invoked.");
		log.trace("---------------------------------------");
    	
    	String name = event.getName();
    	Object value = event.getValue();

		log.info("\t+ name: {}", name);
		log.info("\t+ value: {}", value);
		log.info("\t+ type: {}", value.getClass().getName());
    } // attributeReplaced
	
} // end class
