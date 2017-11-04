package com.hiekn.demo.util;

import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.hiekn.demo.config.CommonResource;

public class SendEmailUtils {

	private static final String KC_EMAIL_ACCOUNT = "service@lengjing.io";

	private static final String KC_EMAIL_PASSWORD = "8pnKkrj9eQ";

	private static final String KC_EMAIL_SEREVRE = "smtp.ym.163.com";

	private static String invite_email_document = "";

	private static final String INVITE_URL = "http://analyze.lengjing.io";
	
	static{
		Reader reader = null;
		try {
			reader = new FileReader(CommonResource.INVITE_EMAIL_FILE_PATH);
			invite_email_document  = IOUtils.toString(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(reader);
		}
	}
	
	public static void sendEmail(String email,String icode) throws EmailException{
		HtmlEmail htmlEmail = new HtmlEmail ();
		htmlEmail.setHostName(KC_EMAIL_SEREVRE);
		htmlEmail.setAuthentication(KC_EMAIL_ACCOUNT,KC_EMAIL_PASSWORD);
		htmlEmail.setFrom(KC_EMAIL_ACCOUNT);
		htmlEmail.addTo(email);
		htmlEmail.setCharset("UTF-8");
		htmlEmail.setSubject("棱镜试用邀请邮件");
		String url = INVITE_URL.concat("?icode=").concat(icode);
		String content = invite_email_document.replaceAll("\\$\\{invite_url\\}", url);
		htmlEmail.setHtmlMsg(content);
		htmlEmail.send();
	}
	
}




