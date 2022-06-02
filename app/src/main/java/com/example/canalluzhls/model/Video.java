package com.example.canalluzhls.model;

public class Video {

    private String nombre;
    private String reproducciones;
    private String anio;
    private String url;

    public Video() { }

    public Video(String nombre, String reproducciones, String anio, String url) {
        this.nombre = nombre;
        this.reproducciones = reproducciones;
        this.anio = anio;
        this.url = url;
    }


    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(String reproducciones) {
        this.reproducciones = reproducciones;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "anio='" + anio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", reproducciones='" + reproducciones + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
