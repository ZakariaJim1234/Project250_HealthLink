package com.example.healthlink;

public class DataHolder {
    private String id; // Unique identifier
    private String name;
    private String email;
    private String location;
    private String hotline;

    public DataHolder() {
        // Default constructor required for Firebase
    }

    public DataHolder(String id, String name, String email, String location, String hotline) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.hotline = hotline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
}
