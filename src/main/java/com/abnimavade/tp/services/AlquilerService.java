package com.abnimavade.tp.services;

import com.abnimavade.tp.dtos.AlquilerDTO;


import java.util.List;
import java.util.stream.Collectors;
import com.abnimavade.tp.models.Alquiler;
import com.abnimavade.tp.repositories.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AlquilerService{

    @Autowired
    private AlquilerRepository alquilerRepository;
    public List<AlquilerDTO> getAll() {
        List<Alquiler> alquileres = alquilerRepository.findAll();
        return alquileres.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public AlquilerDTO convertToDto(Alquiler alquiler)
    {
        AlquilerDTO alquilerDTO = new AlquilerDTO();
        // Id del alquiler
        alquilerDTO.setAlquilerId(alquiler.getAlquilerId());
        // Id del cliente
        alquilerDTO.setIdCliente(alquiler.getIdCliente());
        // Estado tarifa
        alquilerDTO.setEstadoTarifa(alquiler.getEstadoTarifa());
        // Estaciones de retiro y devolucion
        alquilerDTO.setEstacionRetiro(alquiler.getEstacionRetiro());
        alquilerDTO.setEstacionDevolucion(alquiler.getEstacionDevolucion());
        // Fecha de retiro y devolucion
        alquilerDTO.setFechaHoraRetiro(alquiler.getFechaHoraRetiro());
        alquilerDTO.setFechaHoraDevolucion(alquiler.getFechaHoraDevolucion());
        // Monto total e id tarifa
        alquilerDTO.setMonto(alquiler.getMonto());
        alquilerDTO.setIdTarifa(alquiler.getIdTarifa());
        return alquilerDTO;
    }

    public AlquilerDTO create(AlquilerDTO alquilerDTO) {
        Alquiler alquiler = new Alquiler();
        // Id del cliente
        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado tarifa
        alquiler.setEstadoTarifa(alquilerDTO.getEstadoTarifa());
        // Estaciones de retiro y devolucion
        alquiler.setEstacionRetiro(alquilerDTO.getEstacionRetiro());
        alquiler.setEstacionDevolucion(alquilerDTO.getEstacionDevolucion());
        // Fecha de retiro y devolucion
        alquiler.setFechaHoraRetiro(alquilerDTO.getFechaHoraRetiro());
        alquiler.setFechaHoraDevolucion(alquilerDTO.getFechaHoraDevolucion());
        // Monto total e id tarifa
        alquiler.setMonto(alquilerDTO.getMonto());
        alquiler.setIdTarifa(alquilerDTO.getIdTarifa());
        alquiler = alquilerRepository.save(alquiler);
        alquilerDTO.setAlquilerId(alquiler.getAlquilerId());
        return alquilerDTO;
    }

    public AlquilerDTO update(Long id,AlquilerDTO alquilerDTO) {
        Alquiler alquiler = alquilerRepository.findById(id).get();
        // Id del cliente
        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado tarifa
        alquiler.setEstadoTarifa(alquilerDTO.getEstadoTarifa());
        // Estaciones de retiro y devolucion
        alquiler.setEstacionRetiro(alquilerDTO.getEstacionRetiro());
        alquiler.setEstacionDevolucion(alquilerDTO.getEstacionDevolucion());
        // Fecha de retiro y devolucion
        alquiler.setFechaHoraRetiro(alquilerDTO.getFechaHoraRetiro());
        alquiler.setFechaHoraDevolucion(alquilerDTO.getFechaHoraDevolucion());
        // Monto total e id tarifa
        alquiler.setMonto(alquilerDTO.getMonto());
        alquiler.setIdTarifa(alquilerDTO.getIdTarifa());
        alquiler = alquilerRepository.save(alquiler);
        alquilerDTO.setAlquilerId(alquiler.getAlquilerId());
        return alquilerDTO;
    }

    public String delete(Long id) {
        alquilerRepository.deleteById(id);
        return "Alquiler deleted";
    }
}