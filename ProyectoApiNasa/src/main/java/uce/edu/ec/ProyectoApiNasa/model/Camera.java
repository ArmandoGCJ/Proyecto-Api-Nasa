package uce.edu.ec.ProyectoApiNasa.model;


public class Camera {
    private int id;
    private String name;
    private String roverId;
    private String fullName;

    public Camera() {
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

    public String getRoverId() {
        return roverId;
    }

    public void setRoverId(String roverId) {
        this.roverId = roverId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roverId='" + roverId + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
