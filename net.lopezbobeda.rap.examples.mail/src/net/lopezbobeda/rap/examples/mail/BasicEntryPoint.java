/*******************************************************************************
 * Copyright (c) 2014 Edmundo Lopez Bobeda.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 ******************************************************************************/

package net.lopezbobeda.rap.examples.mail;

import net.lopezbobeda.rap.examples.mail.views.MainView;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.widgets.Composite;


/**
 * Represents the entry point of the application. Configures the main view.
 * @author mundacho
 *
 */
public class BasicEntryPoint extends AbstractEntryPoint {

	static Composite pageComposite;
	static int pageNum = 0;

	
	
	
	@Override
	protected void createContents(final Composite shell) {

		new MainView(shell);
		getShell().setMaximized(true);
	}

}
