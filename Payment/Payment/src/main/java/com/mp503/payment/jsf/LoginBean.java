package com.mp503.payment.jsf;

import com.mp503.payment.ejb.SystemServiceLocal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mehmetparlak
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @EJB
    private SystemServiceLocal systemService;
    
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginBean() {

    }

    public LoginBean(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        try {
            request.login(this.username, this.password);
            String stamp = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss").format(Calendar.getInstance().getTime());
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed:" + e));
            return "error";
        }
        
        System.out.println(request.getRequestURI());
        return systemService.getUserGroupByName(username).toLowerCase();
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "/login?faces-redirect=true";
    }
    
}
