package com.abnimavade.tp.services;

import com.abnimavade.tp.dtos.TarifaDTO;
import com.abnimavade.tp.models.Tarifa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abnimavade.tp.repositories.TarifaRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public List<TarifaDTO> getAll() {
        List<Tarifa> tarifas = tarifaRepository.findAll();
        return tarifas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TarifaDTO convertToDto(Tarifa tarifa) {
        TarifaDTO tarifaDTO = new TarifaDTO();
        // Id de la tarifa
        tarifaDTO.setTarifaId(tarifa.getTarifaId());
        // Tipo de tarifa
        tarifaDTO.setTipoTarifa(tarifa.getTipoTarifa());
        // Definicion
        tarifaDTO.setDefinicion(tarifa.getDefinicion());
        // Dia semana, dia mes, mes, año
        tarifaDTO.setDiaSemana(tarifa.getDiaSemana());
        tarifaDTO.setDiaMes(tarifa.getDiaMes());
        tarifaDTO.setMes(tarifa.getMes());
        tarifaDTO.setAnio(tarifa.getAnio());
        // Montos
        tarifaDTO.setMontoFijoAlquiler(tarifa.getMontoFijoAlquiler());
        tarifaDTO.setMontoMinutoFraccion(tarifa.getMontoMinutoFraccion());
        tarifaDTO.setMontoKm(tarifa.getMontoKm());
        tarifaDTO.setMontoHora(tarifa.getMontoHora());
        return tarifaDTO;
    }

    public TarifaDTO create(TarifaDTO tarifaDto) {
        Tarifa tarifa = new Tarifa();
        // Tipo de tarifa
        tarifa.setTipoTarifa(tarifaDto.getTipoTarifa());
        // Definicion
        tarifa.setDefinicion(tarifaDto.getDefinicion());
        // Dia semana, dia mes, mes, año
        tarifa.setDiaSemana(tarifaDto.getDiaSemana());
        tarifa.setDiaMes(tarifaDto.getDiaMes());
        tarifa.setMes(tarifaDto.getMes());
        tarifa.setAnio(tarifaDto.getAnio());
        // Montos
        tarifa.setMontoFijoAlquiler(tarifaDto.getMontoFijoAlquiler());
        tarifa.setMontoMinutoFraccion(tarifaDto.getMontoMinutoFraccion());
        tarifa.setMontoKm(tarifaDto.getMontoKm());
        tarifa.setMontoHora(tarifaDto.getMontoHora());
        tarifa = tarifaRepository.save(tarifa);
        tarifaDto.setTarifaId(tarifa.getTarifaId());
        return tarifaDto;
    }


    public TarifaDTO update(Long id, TarifaDTO tarifaDTO) {
        // Recibimos el id de la tarifa a modificar
        Tarifa tarifa = tarifaRepository.findById(id).get();
        // Tipo de tarifa
        tarifa.setTipoTarifa(tarifaDTO.getTipoTarifa());
        // Definicion
        tarifa.setDefinicion(tarifaDTO.getDefinicion());
        // Dia semana, dia mes, mes, año
        tarifa.setDiaSemana(tarifaDTO.getDiaSemana());
        tarifa.setDiaMes(tarifaDTO.getDiaMes());
        tarifa.setMes(tarifaDTO.getMes());
        tarifa.setAnio(tarifaDTO.getAnio());
        // Montos
        tarifa.setMontoFijoAlquiler(tarifaDTO.getMontoFijoAlquiler());
        tarifa.setMontoMinutoFraccion(tarifaDTO.getMontoMinutoFraccion());
        tarifa.setMontoKm(tarifaDTO.getMontoKm());
        tarifa.setMontoHora(tarifaDTO.getMontoHora());
        tarifa = tarifaRepository.save(tarifa);
        tarifaDTO.setTarifaId(tarifa.getTarifaId());
        return tarifaDTO;
    }

    public String delete(Long id) {
        tarifaRepository.deleteById(id);
        return "Tarifa deleted";
    }
}