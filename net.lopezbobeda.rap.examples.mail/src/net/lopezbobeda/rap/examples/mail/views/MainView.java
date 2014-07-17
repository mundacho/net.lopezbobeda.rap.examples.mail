package net.lopezbobeda.rap.examples.mail.views;

import java.io.IOException;
import java.io.InputStream;

import net.lopezbobeda.rap.examples.mail.model.Email;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.service.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

/**
 * Represents the main view of the mail application.
 * 
 * @author mundacho
 *
 */
public class MainView {

	static private String folderImageLocation;
	
	public String getFolderImageLocation() {
		return folderImageLocation;
	}

	private void registerImages() {
		try {
			folderImageLocation = registerImage("folder.gif");
		} catch (IOException exception) {
			throw new RuntimeException("Failed to register images", exception);
		}
	}

	private static String registerImage(String resourceName) throws IOException {
		ResourceManager resourceManager = RWT.getResourceManager();
		if (!resourceManager.isRegistered(resourceName)) {
			InputStream inputStream = MainView.class.getClassLoader()
					.getResourceAsStream(resourceName);
			if (inputStream == null) {
				throw new RuntimeException("Resource not found");
			}
			try {
				resourceManager.register(resourceName, inputStream);
			} finally {
				inputStream.close();
			}
		}
		return resourceManager.getLocation(resourceName);
	}

	public MainView(final Composite shell) {
		registerImages();
		final Composite parent = new SashForm(shell, SWT.HORIZONTAL | SWT.FILL);
		GridData theCompositeGridData = new GridData();
		theCompositeGridData.grabExcessHorizontalSpace = true;
		theCompositeGridData.grabExcessVerticalSpace = true;
		theCompositeGridData.horizontalAlignment = SWT.FILL;
		theCompositeGridData.verticalAlignment = SWT.FILL;
		parent.setLayoutData(theCompositeGridData);

		// GridLayout layout = new GridLayout(2, false);
		// parent.setLayout(layout);
		final TreeView label = new TreeView(parent);

		GridData labelGridData = new GridData();
		// labelGridData.verticalSpan = 2;
		labelGridData.grabExcessVerticalSpace = true;
		labelGridData.verticalAlignment = SWT.FILL;
		label.getViewer().getTree().setLayoutData(labelGridData);

		final Composite rightView = new Composite(parent, SWT.NONE);
		GridLayout rightViewLayout = new GridLayout(1, false);
		rightView.setLayout(rightViewLayout);

		TableViewer viewer = new TableViewer(rightView, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER
				| SWT.RESIZE);
		createColumns(viewer);

		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		Email[] emails = new Email[] {
				new Email("edmundo@lopezbobeda.net", "jean.val.jean@miserable.fr",
						"Urgent!!", "Message 1"),
				new Email("diego.de.la.vega@lopezbobeda.net",
						"jean.val.jean@miserable.fr", "Plus Urgent!!", "Message 2") };

		final Label placeHolder = new Label(rightView, SWT.NONE);
		placeHolder.setText("No message selected");

		table.setLayoutData(gridData);

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			Composite mailView = null;

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (!placeHolder.isDisposed()) {
					placeHolder.dispose();
				}
				if (mailView != null) {
					mailView.dispose();
				}
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				MessageView mv = new MessageView(rightView, (Email) selection
						.getFirstElement());
				mailView = mv.getView();
				rightView.layout();
			}
		});
		viewer.setInput(emails);
	}

	private void createColumns(TableViewer viewer) {
		TableViewerColumn colFrom = new TableViewerColumn(viewer, SWT.NONE);
		colFrom.getColumn().setWidth(200);
		colFrom.getColumn().setText("From");
		colFrom.setLabelProvider(new ColumnLabelProvider() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getText(Object element) {
				Email email = (Email) element;
				return email.getSender();
			}
		});
		TableViewerColumn colTo = new TableViewerColumn(viewer, SWT.NONE);
		colTo.getColumn().setWidth(200);
		colTo.getColumn().setText("To");
		colTo.setLabelProvider(new ColumnLabelProvider() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getText(Object element) {
				Email email = (Email) element;
				return email.getTo();
			}
		});
		TableViewerColumn colSubject = new TableViewerColumn(viewer, SWT.NONE);
		colSubject.getColumn().setWidth(200);
		colSubject.getColumn().setText("Subject");
		colSubject.setLabelProvider(new ColumnLabelProvider() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getText(Object element) {
				Email email = (Email) element;
				return email.getSubject();
			}
		});
	}
}
