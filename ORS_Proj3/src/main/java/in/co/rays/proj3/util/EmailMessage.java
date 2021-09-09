package in.co.rays.proj3.util;

public class EmailMessage {
	/** Contains comma separated TO addresses. */
private String to=null;

/** Sender addresses. */
private String from=null;

/** contains comma separated bcc address. */
private String bcc=null;

/** contains comma separated cc address. */
private String cc=null;

/** Contains message subject. */
private String subject=null;

/** contains message. */
private String message=null;

/** contains message type:Type of message whether it is Html or text, default is Text. */
private int messageType = TEXT_MSG;

/** The Constant HTML_MSG. */
public static final int HTML_MSG = 1;

/** The Constant TEXT_MSG. */
public static final int TEXT_MSG = 2;

/**
 * Gets the to.
 *
 * @return the to
 */
public String getTo() {
	return to;
}

/**
 * Sets the to.
 *
 * @param to the new to
 */
public void setTo(String to) {
	this.to = to;
}

/**
 * Gets the from.
 *
 * @return the from
 */
public String getFrom() {
	return from;
}

/**
 * Sets the from.
 *
 * @param from the new from
 */
public void setFrom(String from) {
	this.from = from;
}

/**
 * Gets the bcc.
 *
 * @return the bcc
 */
public String getBcc() {
	return bcc;
}

/**
 * Sets the bcc.
 *
 * @param bcc the new bcc
 */
public void setBcc(String bcc) {
	this.bcc = bcc;
}

/**
 * Gets the cc.
 *
 * @return the cc
 */
public String getCc() {
	return cc;
}

/**
 * Sets the cc.
 *
 * @param cc the new cc
 */
public void setCc(String cc) {
	this.cc = cc;
}

/**
 * Gets the subject.
 *
 * @return the subject
 */
public String getSubject() {
	return subject;
}

/**
 * Sets the subject.
 *
 * @param subject the new subject
 */
public void setSubject(String subject) {
	this.subject = subject;
}

/**
 * Gets the message.
 *
 * @return the message
 */
public String getMessage() {
	return message;
}

/**
 * Sets the message.
 *
 * @param message the new message
 */
public void setMessage(String message) {
	this.message = message;
}

/**
 * Gets the message type.
 *
 * @return the message type
 */
public int getMessageType() {
	return messageType;
}

/**
 * Sets the message type.
 *
 * @param messageType the new message type
 */
public void setMessageType(int messageType) {
	this.messageType = messageType;
}

/**
 * Gets the html msg.
 *
 * @return the html msg
 */
public static int getHtmlMsg() {
	return HTML_MSG;
}

/**
 * Gets the text msg.
 *
 * @return the text msg
 */
public static int getTextMsg() {
	return TEXT_MSG;
}


}
