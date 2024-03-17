package com.applestore.applestore.Controllers;

import com.applestore.applestore.DTOs.RegisterDto;
import com.applestore.applestore.Entities.UserEntity;
import com.applestore.applestore.Exception.UserNotFoundException;
import com.applestore.applestore.Services.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.boot.Banner;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class AuthController {
    private final UserService userService;
    private final JavaMailSender mailSender;

    public AuthController(UserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/Fragments/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "/Fragments/auth/register";
    }
    @GetMapping("/register/admin")
    public String getRegisterForAdminForm(Model model) {
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "/Fragments/auth/register-admin";
    }

    //
    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterDto user
            ,BindingResult result, Model model
    ) {
        System.out.println("User"+user);
//        UserDto existingUserEmail = userService.findByGmail(user.getGmail());
//        if (existingUserEmail != null && existingUserEmail.getGmail() != null && !existingUserEmail.getGmail().isEmpty()) {
//            return "redirect:/register?fail";
//        }
//        UserDto existingUserUsername = userService.findByUsername(user.getUsername());
//        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
//            return "redirect:/register?fail";
//        }
//        if (result.hasErrors()) {
//            model.addAttribute("user", user);
//            return "/Fragments/auth/register";
//        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
    @PostMapping("/register/admin")
    public String registerForAdmin(@ModelAttribute("user") RegisterDto user
            ,BindingResult result, Model model
    ){
        System.out.println("User"+user);
        userService.saveAdmin(user);
        return "redirect:/register/admin?success";
    }
    @GetMapping("/forgot-password")
    public String forgetPasswordForm(Model model) {
        model.addAttribute("pageTitle", "Forgot Password");
        return "/Fragments/auth/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgetPassword(HttpServletRequest req, Model model) {
        String gmail = req.getParameter("gmail");
        String token = RandomString.make(50);


        System.out.println("Gmail: "+gmail);
        System.out.println("Token: "+token);
        try{
            userService.updateResetPasswordToken(token, gmail);
            String rspwLink = getSiteURL(req) + "/reset-password?token="+token;
            System.out.println(rspwLink);
            sendMail(gmail, rspwLink);
            model.addAttribute("message", "We have sent link to reset password, please check your mail");
        }catch (UserNotFoundException   ex){
            System.out.println("Gmail: "+gmail+" Not found");
            model.addAttribute("error", ex.getMessage());
        }catch (UnsupportedEncodingException | MessagingException ex){
            model.addAttribute("error", "Error while sending mail");
        }

        return "/Fragments/auth/forgot-password";
    }

    private void sendMail(String gmail, String rspwLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("addmmin4567@gmail.com", "AppleStore");
        helper.setTo(gmail);
        String subject = "Reset your password";
        String content = "<p>Hello, </p>"
                +"<p>You have requested to reset your password.</p>"
                +"<p>Click the link below to chage your password.</p>"
                +"<p><b><a href=\""+rspwLink + "\">Change my password<a/></b></p>"
                +"<p>Ignore this email if you do remember your password or you have not ade the request.</p>";

        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }

    public static String getSiteURL(HttpServletRequest req){
        String siteURL = req.getRequestURL().toString();
        return siteURL.replace(req.getServletPath(),""); //remove /forgot-password in localhost/forgotpassword
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@Param(value = "token") String token, Model model){
        UserEntity userEntity = userService.getByResetPasswordToken(token);
        if(userEntity ==  null){
            model.addAttribute("title", "Reset your password");
            model.addAttribute("message", "Invalid Token");
            return "/Fragments/auth/message";
        }
        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Reset Your Password");
        return "/Fragments/auth/reset-password-form";
    }
    @PostMapping("/reset-password")
    public String resetPasswordForm(Model model, HttpServletRequest req){
        String token = req.getParameter("token");
        String newPassword = req.getParameter("password");
        UserEntity userEntity = userService.getByResetPasswordToken(token);
        if(userEntity ==  null){
            model.addAttribute("title", "Reset your password");
            model.addAttribute("message", "Invalid Token");

        }else{
            userService.updatePassword(userEntity, newPassword);
            model.addAttribute("message", "You have successfully changed your password");
        }
        return "/Fragments/auth/message";
    }
}
