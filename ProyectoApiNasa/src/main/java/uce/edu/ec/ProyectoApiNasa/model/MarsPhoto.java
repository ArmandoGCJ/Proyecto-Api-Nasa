package uce.edu.ec.ProyectoApiNasa.model;

public class MarsPhoto {
    private int id;
    private int sol;
    private Camera camera;
    private String img_src;
    private String earth_date;
    private Rover rover;

    public int getId() {
        return id;
    }

    public int getSol() {
        return sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getEarth_date() {
        return earth_date;
    }

    public Rover getRover() {
        return rover;
    }

    @Override
    public String toString() {
        return "MarsPhoto{" +
                "id=" + id +
                ", sol=" + sol +
                ", camera=" + camera +
                ", img_src='" + img_src + '\'' +
                ", earth_date='" + earth_date + '\'' +
                ", rover=" + rover +
                '}';
    }
}
