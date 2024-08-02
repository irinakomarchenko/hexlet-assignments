package exercise;

class Address {
    // BEGIN
    @NotNull
    // END
    private String country;

    // BEGIN
    @NotNull
    @MinLength(minLength = 3)
    // END
    private String city;

    // BEGIN
    @MinLength(minLength = 3)
    // END
    private String street;

    // BEGIN
    @NotNull
    // END
    private String houseNumber;

    private String flatNumber;


    Address(String country, String city, String street, String houseNumber,
            String flatNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
    // Getters
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

}
