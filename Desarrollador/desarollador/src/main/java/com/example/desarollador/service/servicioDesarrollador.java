package com.example.desarollador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.desarollador.DTO.DTOjuego;
import com.example.desarollador.model.DesarrolladorGG;
import com.example.desarollador.repository.repoDesarrollador;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class servicioDesarrollador {
    @Autowired
    private repoDesarrollador desarrolladorRepository;
 
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8085/api/v0/juegos")
            .build();
 
    @SuppressWarnings("null")
    public void crearDesarrollador(DesarrolladorGG desarrollador) {
        desarrolladorRepository.save(desarrollador);
    }
 
    public List<DesarrolladorGG> obtenerDesarrolladores() {
        return desarrolladorRepository.findAll();
    }
 
    @SuppressWarnings("null")
    public DesarrolladorGG obtenerDesarrollador(Long id) {
        return desarrolladorRepository.findById(id).orElse(null);
    }
 
    @SuppressWarnings("null")
    public void actualizarDesarrollador(DesarrolladorGG desarrollador) {
        desarrolladorRepository.save(desarrollador);
    }
 
    @SuppressWarnings("null")
    public void eliminarDesarrollador(Long id) {
        desarrolladorRepository.deleteById(id);
    }
 
    public List<DTOjuego> obtenerTodosLosJuegos() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(DTOjuego.class)
                .collectList()
                .block();
    }
 
    public DTOjuego obtenerJuego(Long juegoId) {
        return webClient.get()
                .uri("/{id}", juegoId)
                .retrieve()
                .bodyToMono(DTOjuego.class)
                .block();
    }
 
}
   