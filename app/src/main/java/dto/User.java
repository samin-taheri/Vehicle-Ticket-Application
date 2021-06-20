package dto;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
public String name;
    public String birthday;
    public String passport;
    public String phone;
    public String address;

    public User(String name, String birthday, String phone, String passport, String address) {
        this.name = name;
        this.birthday = birthday;
        this.passport = passport;
        this.phone = phone;
        this.address = address;
    }

    public User(String name) {
        this.name = name;
    }
    public User(){

    }
}
