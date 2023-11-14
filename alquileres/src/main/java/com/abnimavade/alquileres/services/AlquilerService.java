package com.abnimavade.alquileres.services;

import com.abnimavade.alquileres.dtos.AlquilerDTO;


import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.abnimavade.alquileres.models.Alquiler;
import com.abnimavade.alquileres.models.Tarifa;
import com.abnimavade.alquileres.repositories.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AlquilerService{

    @Autowired
    private AlquilerRepository alquilerRepository;
    private CotizacionesService cotizacionesService;
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
        alquilerDTO.setIdEstacionRetiro(alquiler.getIdEstacionRetiro());
        alquilerDTO.setIdEstacionDevolucion(alquiler.getIdEstacionDevolucion());
        // Fecha de retiro y devolucion
        alquilerDTO.setFechaHoraRetiro(alquiler.getFechaHoraRetiro());
        alquilerDTO.setFechaHoraDevolucion(alquiler.getFechaHoraDevolucion());
        // Monto total e id tarifa
        alquilerDTO.setMonto(alquiler.getMonto());
        alquilerDTO.setIdTarifa(alquiler.getIdTarifa());
        return alquilerDTO;
    }


    public AlquilerDTO update(Long id,AlquilerDTO alquilerDTO) {
        Alquiler alquiler = alquilerRepository.findById(id).get();
        // Id del cliente
        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado tarifa
        alquiler.setEstadoTarifa(alquilerDTO.getEstadoTarifa());
        // Estaciones de retiro y devolucion
        alquiler.setIdEstacionRetiro(alquilerDTO.getIdEstacionRetiro());
        alquiler.setIdEstacionDevolucion(alquilerDTO.getIdEstacionDevolucion());
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

    public AlquilerDTO createDesde(int estacionId, AlquilerDTO alquilerDTO) {

        // Este create pasa la estacion en el link y el otro en el body
        Alquiler alquiler = new Alquiler();
        // Id del cliente
        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado tarifa
        alquiler.setEstadoTarifa(alquilerDTO.getEstadoTarifa());
        // Estaciones de retiro y devolucion
        alquiler.setIdEstacionRetiro(estacionId);
        alquiler.setIdEstacionDevolucion(alquilerDTO.getIdEstacionDevolucion());
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

    public List<AlquilerDTO> getAlquileresTarifasMayores(double monto) {
        List<Alquiler> alquileres = alquilerRepository.findAll();
        return alquileres.stream().filter(alquiler -> alquiler.getMonto() > monto).map(this::convertToDto).collect(Collectors.toList());
    }

    public AlquilerDTO finalizarAlquiler(long id, String moneda, AlquilerDTO alquilerDTO, int estacionId, Date fechaHoraDevolucion) {
        // El 2 simboliza que el alquiler esta finalizado
        long estadoTarifa = 2;
        Alquiler alquiler = alquilerRepository.findById(id).get();
        // Id del cliente
        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado tarifa
        alquiler.setEstadoTarifa(estadoTarifa);
        // Estaciones de retiro y devolucion
        alquiler.setIdEstacionRetiro(alquilerDTO.getIdEstacionRetiro());
        alquiler.setIdEstacionDevolucion(estacionId);
        // Fecha de retiro y devolucion
        alquiler.setFechaHoraRetiro(alquilerDTO.getFechaHoraRetiro());
        alquiler.setFechaHoraDevolucion(fechaHoraDevolucion);
        // Monto total e id tarifa
        double montoMedio = calcularMonto(alquilerDTO.getFechaHoraRetiro(),fechaHoraDevolucion,alquilerDTO);
        double tarifaFinal = calcularTarifa(estacionId, alquilerDTO.getIdEstacionRetiro(), montoMedio, alquilerDTO);
        double tarifaConvertida = cotizacionesService.convertirMoneda(moneda, tarifaFinal);
        alquiler.setMonto(tarifaConvertida);
        alquiler.setIdTarifa(alquilerDTO.getIdTarifa());
        alquiler = alquilerRepository.save(alquiler);
        alquilerDTO.setAlquilerId(alquiler.getAlquilerId());
        return alquilerDTO;
     
    }

    private double calcularMonto(Date fechaHoraRetiro, Date fechaHoraDevolucion, AlquilerDTO alquilerDTO) {
        double montoFijo,montoMedio = 0;
        montoFijo = alquilerDTO.getIdTarifa().getMontoFijoAlquiler();

        int difMinutos = (int) TimeUnit.MILLISECONDS.toMinutes(fechaHoraDevolucion.getTime() - fechaHoraRetiro.getTime());
        if ( difMinutos<= 30) {
           montoMedio = montoFijo + (alquilerDTO.getIdTarifa().getMontoMinutoFraccion() * difMinutos);
        }
        else
        {
            int difHoras = (int) TimeUnit.MILLISECONDS.toHours(fechaHoraDevolucion.getTime() - fechaHoraRetiro.getTime());
            montoMedio = montoFijo + (alquilerDTO.getIdTarifa().getMontoHora() * difHoras);
        }

       return montoMedio;

    }


    private double calcularTarifa(int idEstacionDevolucion, int idEstacionRetiro, double montoMedio, AlquilerDTO alquilerDTO){

        // Necesito las latitudes y longitudes de las estaciones para calcular las distancias entre ellas
        EstacionService estacionService = new EstacionService();
        Double distancia = estacionService.getDistanciaEstaciones(idEstacionDevolucion,idEstacionRetiro);

        double tarifaFinal = ((distancia/1000) * alquilerDTO.getIdTarifa().getMontoKm()) + montoMedio;

        return tarifaFinal;
    }

    public boolean existsById(Long id) {
        return alquilerRepository.existsById(id);
    }
}