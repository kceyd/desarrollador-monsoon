package com.example.desarollador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desarollador.model.DesarrolladorGG;
import com.example.desarollador.repository.repoDesarrollador;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class servicioDesarrollador {
    @Autowired
    private repoDesarrollador desarrolladorRepository;

    public void crearDesarrollador(DesarrolladorGG desarrollador) {
        desarrolladorRepository.save(desarrollador);
    }

    public List<DesarrolladorGG> obtenerDesarrolladores() {
        return desarrolladorRepository.findAll();
    }

    public DesarrolladorGG obtenerDesarrollador(Long id) {
        return desarrolladorRepository.findById(id).orElse(null);
    }

    public void actualizarDesarrollador(DesarrolladorGG desarrollador) {
        desarrolladorRepository.save(desarrollador);
    }

    public void eliminarDesarrollador(Long id) {
        desarrolladorRepository.deleteById(id);
    }
}


