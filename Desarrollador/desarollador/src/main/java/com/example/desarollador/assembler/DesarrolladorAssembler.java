package com.example.desarollador.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.desarollador.DTO.DTOdesarrollador;
import com.example.desarollador.controller.ControlDesarrollador;
import com.example.desarollador.model.DesarrolladorGG;

@Component
public class DesarrolladorAssembler implements RepresentationModelAssembler<DesarrolladorGG, EntityModel<DTOdesarrollador>>  {
        @Override
        public EntityModel<DTOdesarrollador> toModel(DesarrolladorGG desarrolladorInterior) {
    
        DTOdesarrollador dtoExterior = new DTOdesarrollador();
        dtoExterior.setId(desarrolladorInterior.getId());
        dtoExterior.setNombre(desarrolladorInterior.getNombre());
        
        return EntityModel.of(dtoExterior,
            linkTo(methodOn(ControlDesarrollador.class).obtenerDesarrollador(dtoExterior.getId())).withSelfRel(),
            linkTo(methodOn(ControlDesarrollador.class).obtenerDesarrolladores()).withRel("desarrolladores")
        );
    }
}
