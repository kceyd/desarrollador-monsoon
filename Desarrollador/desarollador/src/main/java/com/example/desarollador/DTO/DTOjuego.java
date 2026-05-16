package com.example.desarollador.DTO;

import lombok.Data;

@Data
public class DTOjuego {
    private Long id;
    private String titulo;
    private String genero;
    private String descripcion;
    private Double precio;
    private String desarrollador;

}
