package net.lopezbobeda.rap.examples.mail.views;

import net.lopezbobeda.rap.examples.mail.model.Email;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.rap.rwt.widgets.DialogCallback;
import org.eclipse.rap.rwt.widgets.DialogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

/**
 * Represents an email message view.
 * @author mundacho
 *
 */
public class MessageView {

	private Composite view;

	// private Email email;

	public MessageView(final Composite parent, final Email pEmail) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);

		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		top.setLayoutData(gd);

		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL,
				GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);

		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(
				JFaceResources.DEFAULT_FONT);

		Label l = new Label(banner, SWT.WRAP);
		l.setText("Subject:");
		l.setFont(boldFont);
		l = new Label(banner, SWT.WRAP);
		l.setText(pEmail.getSubject());

		l = new Label(banner, SWT.WRAP);
		l.setText("From:");
		l.setFont(boldFont);

		final Link link = new Link(banner, SWT.NONE);
		link.setText("<a>" + pEmail.getSender() + "</a>");
		link.addSelectionListener(new SelectionAdapter() {
			/**
			 * Serial id.
			 */
			private static final long serialVersionUID = -2734992566910878564L;

			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(parent.getShell(),
						SWT.YES);
				messageBox
						.setMessage("Not Implemented\n"
								+ "Imagine the address book or a new message being created now.");
				DialogUtil.open(messageBox, new DialogCallback() {
					/**
					 * Serial id.
					 */
					private static final long serialVersionUID = -8172492596304353383L;

					public void dialogClosed(int returnCode) {
						System.out.println("Yessss!");
					}
				});
			}
		});

		l = new Label(banner, SWT.WRAP);
		l.setText("Date:");
		l.setFont(boldFont);
		l = new Label(banner, SWT.WRAP);
		l.setText("10:34 am");
		// message contents
		Text text = new Text(top, SWT.MULTI | SWT.WRAP);
		text.setText(pEmail.getMessage());
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		view = top;
	}

	public Composite getView() {
		return view;
	}

}
