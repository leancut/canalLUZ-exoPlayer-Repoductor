package com.example.canalluzhls.model

class Servidores {
    private var conexiones: String? = null
    private var nombre: String? = null
    private var url: String? = null
    private var ubicacion: String? = null
    private var estado: String? = null
    private var conexMax: String? = null


    fun Servidores() {}

    fun Servidores(
        conexiones: String?,
        nombre: String?,
        url: String?,
        ubicacion: String?,
        estado: String?,
        conexMax: String?
    ) {
        this.conexiones = conexiones
        this.nombre = nombre
        this.url = url
        this.ubicacion = ubicacion
        this.estado = estado
        this.conexMax = conexMax

    }

    fun getConexiones(): String? {
        return conexiones
    }

    fun setConexiones(conexiones: String?) {
        this.conexiones = conexiones
    }

    fun getNombre(): String? {
        return nombre
    }

    fun setNombre(nombre: String?) {
        this.nombre = nombre
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getUbicacion(): String? {
        return ubicacion
    }

    fun setUbicacion(ubicacion: String?) {
        this.ubicacion = ubicacion
    }

    fun getEstado(): String? {
        return estado
    }

    fun setEstado(estado: String?) {
        this.estado = estado
    }

    fun getConexMax(): String? {
        return conexMax
    }

    fun setConexMax(estado: String?) {
        this.conexMax = estado
    }

    override fun toString(): String {
        return "Servidor{" +
                "conexiones='" + conexiones + '\'' +
                ", nombre='" + nombre + '\'' +
                ", url='" + url + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", estado='" + estado + '\'' +
                '}'
    }

}