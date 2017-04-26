package com.mp503.paymentsystem.jsf;



import com.mp503.paymentsystem.bean.SystemUserGroupService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


public class LoginController 
{
    @EJB
    private SystemUserGroupService systemUserGroupServiceBean;

    private String username;
    private String password;
    
    
    public LoginController() {}
    
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
        return systemUserGroupServiceBean.getGroupByUsername(username).toLowerCase();
    }
    
    //Log user out
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

    public SystemUserGroupService getSystemUserGroupServiceBean() {
        return systemUserGroupServiceBean;
    }

    public void setSystemUserGroupServiceBean(SystemUserGroupService systemUserGroupServiceBean) {
        this.systemUserGroupServiceBean = systemUserGroupServiceBean;
    }
    
    
    
}
