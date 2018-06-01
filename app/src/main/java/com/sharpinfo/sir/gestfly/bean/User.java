package com.sharpinfo.sir.gestfly.bean;



import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String id;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private String adresse;
    private String phone;
    private String zipCode;
    private String image;
    private boolean blocked;
    private Date lastLogin;
    private Date passwordRequestedAt;
    private int nbrConnection;
    private Role role = new Role();
    private Ville ville = new Ville();
    private Job job = new Job();

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String id, String password, String lastName, String firstName, String email, String adresse, String phone, String zipCode, String image, boolean blocked, Date lastLogin, Date passwordRequestedAt, int nbrConnection, Long roleId, Long villeId, Long jobId) {
        this.id = id;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.adresse = adresse;
        this.phone = phone;
        this.zipCode = zipCode;
        this.image = image;
        this.blocked = blocked;
        this.lastLogin = lastLogin;
        this.passwordRequestedAt = passwordRequestedAt;
        this.nbrConnection = nbrConnection;
        role.setId(roleId);
        ville.setId(villeId);
        job.setId(jobId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public int getNbrConnection() {
        return nbrConnection;
    }

    public void setNbrConnection(int nbrConnection) {
        this.nbrConnection = nbrConnection;
    }

    public Role getRole() {
        if(role == null)
            role = new Role();
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Ville getVille() {
        if(ville == null)
            ville = new Ville();
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Job getJob() {
        if(job == null)
            job = new Job();
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", phone='" + phone + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", image='" + image + '\'' +
                ", blocked=" + blocked +
                ", lastLogin=" + lastLogin +

                ", passwordRequestedAt=" + passwordRequestedAt +
                ", nbrConnection=" + nbrConnection +
                '}';
    }
}
