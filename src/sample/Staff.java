package sample;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Staff {
    private static final String TABLE_NAME="staffs";
    private static final String COL_ID="id";
    private static final String COL_FIRST_NAME="firstName";
    private static final String COL_LAST_NAME="lastName";
    private static final String COL_DATE_OF_BIRTH="dateOfBirth";
    private static final String COL_GENDER="gender";
    private static final String COL_RELIGION="religion";
    private static final String COL_POSITION="position";
    private static final String COL_EMAIL="email";
    private static final String COL_PHONE="phone";
    private static final String COL_ADDRESS="address";
    private static final String COL_DATE_OF_APPOINTMENT="dateOfAppointment";

    private static String queryString;
    private static ResultSet results;

    private static final DB<Staff> DB_ADAPTER=new DB<>();

    private int id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String religion;
    private String position;
    private String email;
    private String phone;
    private String address;
    private String dateOfAppointment;

    public Staff(){
        this("","",LocalDate.now(),"","","","","","",LocalDate.now());
    }

    public Staff(String firstName, String lastName) {
        this(firstName,lastName,LocalDate.now(),"","","","","","",LocalDate.now());
    }
     public Staff(String firstName, String lastName, LocalDate dateOfBirth, String gender) {
        this(firstName,lastName,dateOfBirth,gender,"","","","","",LocalDate.now());
    }

    public Staff(String firstName, String lastName, LocalDate dateOfBirth, String gender, String religion, String position, String email, String phone, String address, LocalDate dateOfAppointment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth.toString();
        this.gender = gender;
        this.religion = religion;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfAppointment = dateOfAppointment.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
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
        return LocalDate.parse(dateOfBirth);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth.toString();
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
        return LocalDate.parse(dateOfAppointment);
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment.toString();
    }

    public int getAge(){
        LocalDate currentDate = LocalDate.now();
        return Period.between(getDateOfBirth(),currentDate).getYears();
    }

    public String  getFullName(){
        return firstName+" "+lastName;
    }

    public boolean create(){
        queryString = "INSERT INTO "+TABLE_NAME+" ("+
                COL_FIRST_NAME+", "+COL_LAST_NAME+", "+COL_DATE_OF_BIRTH+", "+
                COL_GENDER+", "+COL_RELIGION+", "+COL_POSITION+", "+COL_EMAIL+", "+
                COL_PHONE+", "+COL_ADDRESS+", "+COL_DATE_OF_APPOINTMENT+" "+") VALUES ('"+
                firstName+"','"+lastName+"','"+dateOfBirth+"','"+gender+"','"+
                religion+"','"+position+"','"+email+"','"+phone+"','"+
                address+"','"+dateOfAppointment+"')";
        return DB_ADAPTER.run(queryString);
    }

    public boolean update(){
        queryString = "UPDATE "+TABLE_NAME+" SET "+
                COL_FIRST_NAME+"='"+firstName+"', "+COL_LAST_NAME+"='"+lastName+"', "
                +COL_DATE_OF_BIRTH+"='"+dateOfBirth+"', "+COL_GENDER+"='"+gender+"', "+
                COL_RELIGION+"='"+religion+"', "+COL_POSITION+"='"+position+"', "+
                COL_EMAIL+"='"+email+"', "+COL_PHONE+"='"+phone+"', "+COL_ADDRESS+"='"+address+
                "', "+COL_DATE_OF_APPOINTMENT+"='"+dateOfAppointment+"' WHERE id="+id;
        return DB_ADAPTER.run(queryString);
    }

    public boolean save(){
        return (id==0)? create():update();
    }

    public boolean delete(){
        queryString = "DELETE FROM "+TABLE_NAME+" WHERE id="+id;
        return DB_ADAPTER.run(queryString);
    }

    public static Staff find(int id){
        queryString = "SELECT * FROM "+TABLE_NAME+" WHERE id="+id;
        return DB_ADAPTER.find(queryString);
    }

    public static List<Staff> getAll(){
        queryString = "SELECT * FROM "+TABLE_NAME;
        return DB_ADAPTER.query(queryString);
    }
}
