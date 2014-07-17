package net.lopezbobeda.rap.examples.mail;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.eclipse.rap.rwt.engine.RWTServletContextListener;

public class MyListener extends RWTServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
	    ServletContext servletContext = event.getServletContext();
	    servletContext.setAttribute(BasicApplication.RESOURCE_ROOT_LOCATION, "/tmp");
	    super.contextInitialized(event);
	}
}
