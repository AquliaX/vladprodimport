package org.vladimirskoe.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

public class Registration {

    @Email(message = "Email must be valid",
            regexp = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", flags = Flag.CASE_INSENSITIVE)
    @Size(min = 1, max = 100)
    private String email;

    @Size(min = 8,max = 32)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$",
            message = "Password must be between 8 and 32 characters;"
                    + "Password must contain at least 1 lowercase letter,"
                    + "1 uppercase letter, 1 number ")
    private String password;

    @NotBlank(message = "Passwords must match")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$")
    private String confirmPassword;

    @NotBlank(message = "Phone cannot be null")
    private String phone;

    @NotBlank(message = "Name cannot be null")
    private String name;

    private String surname;

    @NotBlank(message = "Organization cannot be null")
    private String organization;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();//check
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkPassword();//check
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    private void checkPassword() {
        if (this.password == null || this.confirmPassword == null) {
            return;
        } else if (!this.password.equals(confirmPassword)) {
            this.confirmPassword = null;
        }
    }
}
