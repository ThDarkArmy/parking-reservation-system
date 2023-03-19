package tda.darkarmy.parkme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tda.darkarmy.parkme.model.User;

import javax.mail.MessagingException;


@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(User user, String mailBodyString) throws MessagingException {

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
        mimeMessageHelper.setFrom("tset4598t@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setSubject("Parking Slot Reservation Details");

        mimeMessageHelper.setText(mailBodyString, true);
        javaMailSender.send(mimeMessageHelper.getMimeMessage());
        System.out.println("Mail sent successfully");

    }

    private String generateMailBody(String verificationUrl, User  user){
        return "<h4>Hi " +user.getEmail()+", </h4><br>"+
                "<h5>Please click on below button to verify the user account</h5>"+
                "<a href="+verificationUrl+"><button style='color: 'blue''>Verify Account</button></a><br>"+
                "<h5>Warm Regards</h5>"+
                "<h5>Rateberg</h5>";
    }
}
