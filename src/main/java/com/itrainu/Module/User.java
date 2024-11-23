package com.itrainu.Module;

import java.util.Date;

public class User {
    private String name;
    private String department;
    private String password;
    private String mobileNo;
    private Date dob;
    private String gender;
    private String lastName;
    private String lock;
    private int unsuccessfulLogin;
    private long roleId;
    private String createdBy;
    private String confirmPassword;
	private String Email;

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
        return unsuccessfulLogin;
    }

    public void setUnsuccessfulLogin(int unsuccessfulLogin) {
        this.unsuccessfulLogin = unsuccessfulLogin;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

	public void setEmail(String email) {
		this.Email = email;
		
	}
	public String getEmail() {
		return Email;
	}
}
