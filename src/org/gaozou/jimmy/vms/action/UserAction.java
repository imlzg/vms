package org.gaozou.jimmy.vms.action;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.base.Upload;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.jimmy.vms.manager.UserManager;
import org.gaozou.jimmy.vms.manager.OptionsManager;
import org.gaozou.kevin.rbac.Authorization;
import org.gaozou.kevin.utility.HTTPUtil;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;
import java.util.Set;
import java.io.File;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class UserAction extends Action<User> implements Upload {
    private UserManager userManager;
    private User user;
    private User register;
    private User login;
    private User admin;

    private Boolean keep = false;

    private List<User>   users;
    private List<String> roles;
    private Set<String>  roleSet;


    private File   upload;
    private String uploadContentType;
    private String uploadFileName;






    public UserManager getUserManager() {
        return userManager;
    }
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public User getRegister() {
        return register;
    }
    public void setRegister(User register) {
        this.register = register;
    }

    public User getLogin() {
        return login;
    }
    public void setLogin(User login) {
        this.login = login;
    }

    public User getAdmin() {
        return admin;
    }
    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Boolean getKeep() {
        return keep;
    }
    public void setKeep(Boolean keep) {
        this.keep = keep;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }
    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }




    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }







    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(user,      "id"))   user = userManager.get(user.getId());
        else if (! Helper.isEmpty(user, "name")) user = userManager.get(user.getName());
    }


    @SkipValidation
    public String signUp() {
        redirect = referer;
        return SUCCESS;
    }

    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="register.idc", message="You must enter a value for email.", trim=true, shortCircuit=true),
                    @RequiredStringValidator(fieldName="register.passwd", message="You must enter a value for password.", shortCircuit=true),
                    @RequiredStringValidator(fieldName="register.name", message="You must enter a value for name.", trim=true, shortCircuit=true)
                    },
            emails={
                    @EmailValidator(fieldName="register.idc", message="You must enter a valid value for email.")
                    }
    )
    public String register() {
        userManager.register(register);
        login = register;
        return login();
    }

    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="login.idc", key="mail.blank", message="You must enter a value for email", trim=true, shortCircuit=true),
                    @RequiredStringValidator(fieldName="login.passwd", message="You must enter a value for password.", shortCircuit=true)
                    },
            emails={
                    @EmailValidator(fieldName="login.idc", message="You must enter a valid value for email.")
                    }
    )
    public String login() {
        if (keep) {
            int liveTime = 52 * 7 * 24 * 60 * 60;
            getResponse().addCookie(HTTPUtil.createCookie(options.getCookie(), userManager.genCookie(login), liveTime, "/")); //, Helper.DOMAIN_NAME
        }

        getSession().setAttribute("userId", login.getId());

        if (StringUtil.isEmpty(redirect)) redirect = referer;
        return SUCCESS;
    }
    public String logout() {
        redirect = referer;

        getResponse().addCookie(HTTPUtil.createCookie(options.getCookie(), "", 0, "/")); //, Helper.DOMAIN_NAME
        getSession().removeAttribute("userId");

        return SUCCESS;
    }
    public String profile() {
        return SUCCESS;
    }
    public String saveProfile() {
//        if (null != getUpload()) {
//            uploadIcon();
//            user.setIcon("/icon/u-" + Helper.getUserId(getRequest()) + ".jpg");
//        }
        userManager.save(user);

        return SUCCESS;
    }
    public String uploadIcon() {
/*//        String icon = getServletContext().getRealPath("/icon") + "/u-" + Helper.getUserId(getRequest()) + ".jpg";
        String icon = PropertiesUtil.getString("dir.pic", Setting.PROPERTIES, "") + "/icon/u-" + oper.getId() + ".jpg";
        ImageUtil.thumbnail(getUpload(), new File(icon), 48, 48);*/


        return SUCCESS;
    }


    public String list() {
        users = userManager.get();
        return SUCCESS;
    }

    // todo: ajax
    public String isEmailUsed() {
        if (userManager.isEmailUsed(user.getIdc())){
            return getText("mail.used", "email is used");
        }
        return getText("mail.unused", "name can use");
    }
    public String isNameUsed() {
        if (userManager.isNameUsed(user.getName())){
            return getText("name.used", "name is used");
        }
        return getText("name.unused", "name can use");
    }

    ///
    public String manage() {
        page = userManager.page(page);
        return SUCCESS;
    }
    public String update() {
        redirect = referer;
        roleSet = Authorization.getAuthzRoles();
        return SUCCESS;
    }
    public String persist() {
        user.setRoles(StringUtil.concat(roles, String.valueOf(StringUtil.COMMA), null));
        userManager.save(user);
        return SUCCESS;
    }
    public String remove() {
        userManager.remove(user);
        return SUCCESS;
    }





/*
    public String manageSignUp() {
        return SUCCESS;
    }
    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="login.idc", key="mail.blank", message="You must enter a value for email", trim=true, shortCircuit=true),
                    @RequiredStringValidator(fieldName="login.passwd", message="You must enter a value for password.", shortCircuit=true)
                    },
            emails={
                    @EmailValidator(fieldName="login.idc", message="You must enter a valid value for email.")
                    }
    )
    public String manageLogin() {

        int liveTime = 52 * 7 * 24 * 60 * 60;
//        getResponse().addCookie(HTTPUtil.createCookie(Helper.COOKIE_KEY, userManager.genCookie(login), liveTime, "/")); //, Helper.DOMAIN_NAME

        getSession().setAttribute("userId", login.getId());

        return SUCCESS;
    }
    public String manageLogout() {
//        getResponse().addCookie(HTTPUtil.createCookie(Helper.COOKIE_KEY, "", 0, "/")); //, Helper.DOMAIN_NAME

        getSession().removeAttribute("userId");

        return SUCCESS;
    }*/






    public void validateSignUp() {
        Helper.makeStringNull(user);
    }
    public void validateRegister() {
        if (userManager.isEmailUsed(register.getIdc())) {
            addActionError(getText("mail.used", "email is used"));
        }
        if (userManager.isNameUsed(register.getName())) {
            addActionError(getText("name.used", "name is used"));
        }
    }
    public void validateLogin() {
        login = userManager.login(login);
        if (null == login) {
            addActionError("can not login, please check your email or password.");
        }
    }


/*    public void validateManageLogin() {
        login = userManager.login(login);
        if (null == login) {
            addActionMessage("can not login, please check your name or password.");
        }
    }*/




    // override to auth
    public Boolean doAuthz(String action) {
        if ("/profile".equalsIgnoreCase(action) || "/saveprofile".equalsIgnoreCase(action) || "/uploadIcon".equalsIgnoreCase(action)) {
            if (!isAnonymous() && !Helper.isTransient(user) && oper.equals(user)) return true;
        }
        return false;
    }
}
