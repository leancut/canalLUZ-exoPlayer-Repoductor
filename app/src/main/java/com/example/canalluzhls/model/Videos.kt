package com.example.canalluzhls.model

class Videos {
    private var nombre: String? = null
    private var reproducciones: String? = null
    private var anio: String? = null
    private var url: String? = null

    fun Videos() {}

    fun Videos(nombre: String?, reproducciones: String?, anio: String?, url: String?) {
        this.nombre = nombre
        this.reproducciones = reproducciones
        this.anio = anio
        this.url = url
    }


    fun getAnio(): String? {
        return anio
    }

    fun setAnio(anio: String?) {
        this.anio = anio
    }

    fun getNombre(): String? {
        return nombre
    }

    fun setNombre(nombre: String?) {
        this.nombre = nombre
    }

    fun getReproducciones(): String? {
        return reproducciones
    }

    fun setReproducciones(reproducciones: String?) {
        this.reproducciones = reproducciones
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    override fun toString(): String {
        return "Video{" +
                "anio='" + anio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", reproducciones='" + reproducciones + '\'' +
                ", url='" + url + '\'' +
                '}'
    }
}