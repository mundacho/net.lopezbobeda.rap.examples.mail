/**
 * 
 */
package net.lopezbobeda.rap.examples.mail.model;

/**
 * @author mundacho
 *
 */
public class Email {
	private String sender;
	private String to;
	private String subject;
	private String message;
	
	public Email(String pSender, String pTo, String pSubject, String pMessage) {
		sender = pSender;
		to = pTo;
		subject = pSubject;
		message = pMessage;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
