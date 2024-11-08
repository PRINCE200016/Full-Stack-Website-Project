package com.itrainu.Bean;

import java.util.Date;

/**
 * User JavaBean encapsulates User attributes.
 * 
 * @author Kirti Singh
 */
public class UserBean extends BaseBean {
    private static final long serialVersionUID = 1L;
	public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";

    private String name;
    private String department;
    private String password;
    private String mobileNo;
    private Date dob;
    private String gender;
    private String lastName;
    private String lock = INACTIVE;
    private int unsuccessfullLogin;
    private long roleId;
    private String confirmPassword;
    private String login;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public int getUnsuccessfulLogin() {
        return unsuccessfullLogin;
    }

    public void setUnsuccessfulLogin(int unsuccessfullLogin) {
        this.unsuccessfullLogin = unsuccessfullLogin;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static String getActive() {
        return ACTIVE;
    }

    public static String getInactive() {
        return INACTIVE;
    }

    @Override
    public String getKey() {
        return String.valueOf(id);
    }

    @Override
    public String getValue() {
        return name + " " + lastName;
    }

    @Override
    public String toString() {
        return "UserBean [password=" + password + ", dob=" + dob + "]";
    }

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}
}
