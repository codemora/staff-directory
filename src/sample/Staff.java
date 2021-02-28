package sample;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String religion;
    private String position;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfAppointment;

    public Staff(String firstName, String lastName) {
        this(firstName,lastName,null,"","","","","","",null);
    }
     public Staff(String firstName, String lastName, LocalDate dateOfBirth, String gender) {
        this(firstName,lastName,dateOfBirth,gender,"","","","","",null);
    }

    public Staff(String firstName, String lastName, LocalDate dateOfBirth, String gender, String religion, String position, String email, String phone, String address, LocalDate dateOfAppointment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.religion = religion;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfAppointment = dateOfAppointment;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public int getAge(){
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth,currentDate).getYears();
    }

    public String  getFullName(){
        return firstName+" "+lastName;
    }
}
