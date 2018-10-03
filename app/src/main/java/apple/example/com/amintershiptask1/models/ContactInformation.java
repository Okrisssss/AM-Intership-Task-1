package apple.example.com.amintershiptask1.models;

public class ContactInformation {


    private int id;
    private String name;
    private String duration;
    private String type;
    private String number;

    public ContactInformation(){}

    public ContactInformation(int id, String name, String duration, String type, String number) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}