package models;

            public class User {
                private String username;
                private String password;
                private String email;
                private String firstName;
                private String lastName;
                private String phoneNumber;
                private String address;
                private String city;
                private String state;
                private String zipCode;

                public User() {
                }

                public User(String username, String password, String email, String firstName, String lastName,
                           String phoneNumber, String address, String city, String state, String zipCode) {
                    this.username = username;
                    this.password = password;
                    this.email = email;
                    this.firstName = firstName;
                    this.lastName = lastName;
                    this.phoneNumber = phoneNumber;
                    this.address = address;
                    this.city = city;
                    this.state = state;
                    this.zipCode = zipCode;
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

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
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

                public String getPhoneNumber() {
                    return phoneNumber;
                }

                public void setPhoneNumber(String phoneNumber) {
                    this.phoneNumber = phoneNumber;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getZipCode() {
                    return zipCode;
                }

                public void setZipCode(String zipCode) {
                    this.zipCode = zipCode;
                }

                @Override
                public String toString() {
                    return "User{" +
                            "username='" + username + '\'' +
                            ", password='" + password + '\'' +
                            ", email='" + email + '\'' +
                            ", firstName='" + firstName + '\'' +
                            ", lastName='" + lastName + '\'' +
                            ", phoneNumber='" + phoneNumber + '\'' +
                            ", address='" + address + '\'' +
                            ", city='" + city + '\'' +
                            ", state='" + state + '\'' +
                            ", zipCode='" + zipCode + '\'' +
                            '}';
                }

                public boolean validate() {
                    return username != null && !username.isEmpty() &&
                            password != null && !password.isEmpty() &&
                            email != null && !email.isEmpty() &&
                            firstName != null && !firstName.isEmpty() &&
                            lastName != null && !lastName.isEmpty() &&
                            phoneNumber != null && !phoneNumber.isEmpty() &&
                            address != null && !address.isEmpty() &&
                            city != null && !city.isEmpty() &&
                            state != null && !state.isEmpty() &&
                            zipCode != null && !zipCode.isEmpty();
                }

                public boolean isValidEmail() {
                    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                    return email != null && email.matches(emailRegex);
                }

                public boolean isValidPhoneNumber() {
                    String phoneRegex = "^\\+?[0-9]{10,15}$";
                    return phoneNumber != null && phoneNumber.matches(phoneRegex);
                }

                public boolean isValidZipCode() {
                    String zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$";
                    return zipCode != null && zipCode.matches(zipRegex);
                }

                public boolean isValidFirstName() {
                    String nameRegex = "^[a-zA-Z]{2,}$";
                    return firstName != null && firstName.matches(nameRegex);
                }

                public boolean isValidLastName() {
                    String nameRegex = "^[a-zA-Z]{2,}$";
                    return lastName != null && lastName.matches(nameRegex);
                }

                public boolean isValidAddress() {
                    String addressRegex = "^[a-zA-Z0-9\\s,.-]{5,}$";
                    return address != null && address.matches(addressRegex);
                }

                public boolean isValidCity() {
                    String cityRegex = "^[a-zA-Z\\s]{2,}$";
                    return city != null && city.matches(cityRegex);
                }

                public boolean isValidState() {
                    String stateRegex = "^[a-zA-Z\\s]{2,}$";
                    return state != null && state.matches(stateRegex);
                }

                public boolean isValid() {
                    return validate() && isValidEmail() && isValidPhoneNumber() &&
                            isValidFirstName() && isValidLastName() && isValidAddress() &&
                            isValidCity() && isValidState() && isValidZipCode();
                }
            }