package youth;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Members {




    public Integer id_no;
    public String surname;
    public String names;
    public Integer contact;
    public String gender;


    public String full_member;
    public String family;

    public String district;
    public String admission;
    public String commissioned;


    public String occupation;
    public String status;
    public String email;

    public Members(Integer id_no, String surname, String names, Integer contact, String gender,  String full_member,String family, String district, String admission, String commissioned) {
        this.id_no = id_no;
        this.surname = surname;
        this.names = names;
        this.contact = contact;
        this.gender = gender;
        this.family = family;
        this.full_member = full_member;
        this.district = district;
        this.admission = admission;
        this.commissioned = commissioned;
        this.occupation = occupation;
        this.status = status;
        this.email = email;
    }

    public Integer getId_no() {
        return id_no;
    }

    public void setId_no(Integer id_no) {
        this.id_no = id_no;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFull_member() {
        return full_member;
    }

    public void setFull_member(String full_member) {
        this.full_member = full_member;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getCommissioned() {
        return commissioned;
    }

    public void setCommissioned(String commissioned) {
        this.commissioned = commissioned;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
