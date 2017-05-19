package bear2web.util;

import java.util.ArrayList;
import java.util.List;

public class TestMailSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("--- start ---");
		try {

			List mails = new ArrayList();
			mails.add("");

			String msg = "HOGE TEST";

			MailSender sender = new MailSender();
			sender.setMails(mails);
			sender.setMsg(msg);

			sender.sendMail();



		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- end ---");

	}

}
