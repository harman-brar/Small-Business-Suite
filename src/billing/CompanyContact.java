package billing;

public class CompanyContact {
    private String name, phone;

    // EFFECTS: creates new contact
    public CompanyContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns phone
    public String getPhone() {
        return phone;
    }

}
