package com.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.domain.Alquiler;
import com.dev.repository.AlquilerRepository;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Override
    public Alquiler crearAlquiler(Alquiler alquiler) {
    	alquiler.setFcAlquiler(LocalDateTime.now());
        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler obtenerAlquilerPorId(Long id) {
        return alquilerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Alquiler> obtenerTodosLosAlquileres() {
        return alquilerRepository.findAll();
    }

    @Override
    public Alquiler actualizarAlquiler(Alquiler alquiler) {
 
        return alquilerRepository.save(alquiler);
    }

    @Override
    public void eliminarAlquiler(Long id) {
        alquilerRepository.deleteById(id);
    }
}
