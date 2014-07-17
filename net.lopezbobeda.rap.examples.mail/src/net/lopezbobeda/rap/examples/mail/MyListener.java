/*******************************************************************************
 * Copyright (c) 2014 Edmundo Lopez Bobeda.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 ******************************************************************************/

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
