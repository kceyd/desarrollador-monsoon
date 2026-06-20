package com.example.desarollador.DTO;

public class DTOdesarrollador {

    private Long id;
    private String nombre;

    // --- REVISA QUE EL GETTER ESTÉ EXACTAMENTE ASÍ ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}