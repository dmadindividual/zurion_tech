package com.contactcatalog.model;

public class Contact {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String idNumber;
    private String dob;
    private String gender;
    private String organization;
    private String maskedName;
    private String maskedPhone;
    private String hashedPhone;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMaskedName() {
        return maskedName;
    }

    public void setMaskedName(String maskedName) {
        this.maskedName = maskedName;
    }

    public String getMaskedPhone() {
        return maskedPhone;
    }

    public void setMaskedPhone(String maskedPhone) {
        this.maskedPhone = maskedPhone;
    }

    public String getHashedPhone() {
        return hashedPhone;
    }

    public void setHashedPhone(String hashedPhone) {
        this.hashedPhone = hashedPhone;
    }
}
