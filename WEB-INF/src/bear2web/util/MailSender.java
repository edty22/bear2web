package bear2web.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	private String title = "Todays Stock Information";

	private List mails;

	private String msg;



	public void sendMail() throws Exception {

		try {

			ResourceBundle rb = ResourceBundle.getBundle("priceAdmin");
			String mailServer = rb.getString("MAIL_SERVER");
		    String fromMailAddr = rb.getString("FROM_MAIL_ADDR");
		    String fromMailUserName = rb.getString("FROM_MAIL_USER_NAME");

		    for (Iterator it = mails.iterator(); it.hasNext();) {

		    	String addr = (String) it.next();

		    	Properties props = System.getProperties();
				props.put("mail.smtp.host" ,mailServer);
				Session session=Session.getDefaultInstance(props,null);
				MimeMessage mimeMessage=new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(fromMailAddr ,fromMailUserName ,"iso-2022-jp"));
				mimeMessage.setRecipients(Message.RecipientType.TO, addr);
				mimeMessage.setSubject(title ,"iso-2022-jp");
				mimeMessage.setText(msg ,"iso-2022-jp");
				mimeMessage.setHeader("Content-Type","text/plain");
				mimeMessage.setSentDate(new Date());
				Transport.send(mimeMessage);

		    }




		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public List getMails() {
		return mails;
	}

	public void setMails(List mails) {
		this.mails = mails;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


}
