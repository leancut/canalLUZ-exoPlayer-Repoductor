package com.example.canalluzhls.model;

public class Servidor {
    private String conexiones;
    private String nombre;
    private String url;
    private String ubicacion;
    private String estado;

    public Servidor() {}

    public Servidor(String conexiones, String nombre, String url, String ubicacion, String estado) {
        this.conexiones = conexiones;
        this.nombre = nombre;
        this.url = url;
        this.url = ubicacion;
        this.url = estado;
    }

    public String getConexiones() {
        return conexiones;
    }

    public void setConexiones(String conexiones) {
        this.conexiones = conexiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Servidor{" +
                "conexiones='" + conexiones + '\'' +
                ", nombre='" + nombre + '\'' +
                ", url='" + url + '\'' +
                ", url='" + ubicacion + '\'' +
                ", url='" + estado + '\'' +

                '}';
    }
}
