package dto;

public class Service {
    public String email;
    public String name;
    public String phone;
    public String topic;
    public String company;
    public String subject;
    public String description;


    public Service(String email, String name, String phone, String topic, String company, String subject, String description) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.topic = topic;
        this.company = company;
        this.subject = subject;
        this.description = description;
    }
}
