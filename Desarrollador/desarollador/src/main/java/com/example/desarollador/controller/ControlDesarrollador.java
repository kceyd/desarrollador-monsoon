package com.example.desarollador.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import com.example.desarollador.assembler.DesarrolladorAssembler;
import com.example.desarollador.DTO.DTOdesarrollador;
import com.example.desarollador.model.DesarrolladorGG;
import com.example.desarollador.service.servicioDesarrollador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v0/desarrolladores")
public class ControlDesarrollador {

    @Autowired
    private servicioDesarrollador desarrolladorService;

    @Autowired
    private DesarrolladorAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<DTOdesarrollador>> obtenerDesarrolladores() {
        // El servicio trae las entidades del "interior", el assembler las vuelve "exterior" con links
        List<EntityModel<DTOdesarrollador>> desarrolladores = desarrolladorService.obtenerDesarrolladores()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(desarrolladores,
                linkTo(methodOn(ControlDesarrollador.class).obtenerDesarrolladores()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<DTOdesarrollador> obtenerDesarrollador(@PathVariable Long id) {
        DesarrolladorGG desarrollador = desarrolladorService.obtenerDesarrollador(id);
        if (desarrollador == null) {
            return null; // O lanzar una excepción 404
        }
        return assembler.toModel(desarrollador);
    }

    @PostMapping
    public void crearDesarrollador(@RequestBody DesarrolladorGG desarrollador) {
        desarrolladorService.crearDesarrollador(desarrollador);
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