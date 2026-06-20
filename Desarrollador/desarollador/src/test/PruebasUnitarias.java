package com.example.desarollador;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.desarollador.controller.ControlDesarrollador;
import com.example.desarollador.model.DesarrolladorGG;
import com.example.desarollador.service.servicioDesarrollador;
import com.example.desarollador.assembler.DesarrolladorAssembler;

@WebMvcTest(ControlDesarrollador.class)

public class PruebasUnitarias {
@Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private servicioDesarrollador desarrolladorService;

    @MockitoBean
    private DesarrolladorAssembler assembler; // Lo necesita el controlador para HATEOAS

    @Test
    public void listarDesarrolladores_DeberiaRetornar200() throws Exception {
        // 1. Simular el comportamiento del servicio interno
        DesarrolladorGG dev = new DesarrolladorGG();
        dev.setId(1L);
        dev.setNombre("Kratos");

        when(desarrolladorService.obtenerDesarrolladores()).thenReturn(List.of(dev));

        // 2. Ejecutar la petición simulada al controlador exterior
        mockMvc.perform(get("/api/v0/desarrolladores"))
               .andExpect(status().isOk());
    }
}

