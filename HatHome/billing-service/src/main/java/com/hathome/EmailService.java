package com.hathome;

import com.hathome.adapter.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Acer on 18/4/2560.
 */

@Service
public class EmailService{

    @Value("${mail.smtp.starttls.enable}")
    private String smtpStarttls;

    @Value("${mail.smtp.host}")
    private String smtpHost;

    @Value("${mail.smtp.user}")
    private String smtpUser;

    @Value("${mail.smtp.password}")
    private String smtpPassword;

    @Value("${mail.smtp.port}")
    private String smtpPort;

    @Value("${mail.smtp.auth}")
    private String smtpAuth;

    public EmailService() {
    }

    public boolean sendEmailMessage(Bill bill) throws MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", smtpStarttls);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.user", smtpUser);
        props.put("mail.smtp.password", smtpPassword);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", smtpAuth);

        try {
            Session session = Session.getInstance(props,null);
            MimeMessage message = new MimeMessage(session);

            InternetAddress from = new InternetAddress("HatHome");
            message.setSubject("HatHome - Thank you for your shopping with us!");
            message.setFrom(from);
            //address of destination
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(bill.getUsername()));

            Multipart multipart = new MimeMultipart("alternative");
            BodyPart messageBodyPart = new MimeBodyPart();
            multipart.addBodyPart(messageBodyPart);
            String htmlMessage = translateBillToHtml(bill);
            messageBodyPart.setContent(htmlMessage, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpUser, smtpPassword);
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send mail success!!!");
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    private String translateBillToHtml(Bill bill){
        String message = "<div class=\"billing\">\n" +
                "    <section id=\"cart_items\">\n" +
                "      <div class=\"container\">\n" +
                "        <h2 class=\"title text-center\" text-centerstyle=\"margin-top:20px;\">Thank you for your shopping with us!</h2>\n" +
                "        <div class=\"bill-infomation\">\n" +
                "          <p align=\"left\" class=\"user-id\"><b>User ID: </b> " + bill.getUser_id() + " </p>\n" +
                "          <p align=\"left\" class=\"user-name\"><b>Name: </b>" + bill.getUsername() + " </p>\n" +
                "          <p align=\"left\" class=\"bill-address\"><b>Address: </b>" + bill.getAddress() + "</p>\n" +
                "          <p align=\"left\" class=\"bill-no\"><b>Bill No: </b>" + bill.getId() + "</p>\n" +
                "          <p align=\"left\" class=\"bill-date\"><b>Bill Date: </b>" + bill.getDate() + "</p>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"table-responsive cart_info \" style=\"width:100%\">\n" +
                "          <table class=\"table table-condensed\" style=\" border-collapse: collapse; width:100%\">\n" +
                "            <thead style=\"background-color: #fe980f; color: white; padding:8px; text-align: center; width:100%;\">\n" +
                "            <tr class=\"cart_menu\">\n" +
                "              <td class=\"image\"><b>Item</b></td>\n" +
                "              <td class=\"price\"><b>Price</b></td>\n" +
                "              <td class=\"quantity\"><b>Quantity</b></td>\n" +
                "              <td class=\"total\"><b>Total</b></td>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody style=\"padding:8px; text-align: center; width:100%;\">\n";

        for (Product product: bill.getCart().getProducts()) {
            message += "            <tr>\n" +
                    "              <td class=\"cart_description\">\n" +
                    "                <h4>" + product.getName() + "</h4>\n" +
                    "              </td>\n" +
                    "              <td class=\"cart_price\">\n" +
                    "                <p>฿" + product.getPrice() + "</p>\n" +
                    "              </td>\n" +
                    "              <td class=\"cart_quantity\">\n" +
                    "                <p>" + product.getAmount() + "</p>\n" +
                    "              </td>\n" +
                    "              <td class=\"cart_total\">\n" +
                    "                <p class=\"cart_total_price\">฿" + (product.getPrice()* product.getAmount()) + "</p>\n" +
                    "              </td>\n" +
                    "\n" +
                       "            </tr>\n" +
                    "\n";
        }

        message += "            <tr>\n" +
                "              <td colspan=\"3\">&nbsp;</td>\n" +
                "              <td colspan=\"1\">\n" +
                "                <table class=\"table table-condensed total-result\">\n" +
                "                  <tr>\n" +
                "                    <td>Cart Sub Total</td>\n" +
                "                    <td>฿" + bill.getCart_price() + "</td>\n" +
                "                  </tr>\n" +
                "                  <tr class=\"shipping-cost\">\n" +
                "                    <td>Shipping Cost</td>\n" +
                "                    <td>฿" + bill.getShipping_cost() + "</td>\n" +
                "                  </tr>\n" +
                "                  <tr>\n" +
                "                    <td style=\"font-size:20px;\">Total</td>\n" +
                "                    <td><span style=\"font-size:20px;\">" + (bill.getCart_price() + bill.getShipping_cost()) + "</span></td>\n" +
                "                  </tr>\n" +
                "\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "\n" +
                "          </table>\n" +
                "\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </section>\n" +
                "  </div>";
        return message;
    }
}

