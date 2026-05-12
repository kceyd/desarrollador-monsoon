package com.example.desarollador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desarollador.model.DesarrolladorGG;
import com.example.desarollador.service.servicioDesarrollador;
@RestController
@RequestMapping("/api/v0/desarrolladores")
public class ControlDesarrollador {


    @Autowired
    private servicioDesarrollador desarrolladorService;

    @GetMapping
    public List<DesarrolladorGG> obtenerDesarrolladores() {
        return desarrolladorService.obtenerDesarrolladores();
    }

    @PostMapping
    public void crearDesarrollador(@RequestBody DesarrolladorGG desarrollador) {
        desarrolladorService.crearDesarrollador(desarrollador);
    }

    @GetMapping("/{id}")
    public DesarrolladorGG obtenerDesarrollador(@PathVariable Long id) {
        return desarrolladorService.obtenerDesarrollador(id);
    }

    @PutMapping("/{id}")
    public void actualizarDesarrollador(@PathVariable Long id, @RequestBody DesarrolladorGG desarrollador) {
        desarrollador.setId(id);
        desarrolladorService.actualizarDesarrollador(desarrollador);
    }

    @DeleteMapping("/{id}")
    public void eliminarDesarrollador(@PathVariable Long id) {
        desarrolladorService.eliminarDesarrollador(id);
    }
}


