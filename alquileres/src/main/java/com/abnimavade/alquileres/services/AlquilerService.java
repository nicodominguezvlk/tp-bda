package com.abnimavade.alquileres.services;

import com.abnimavade.alquileres.dtos.AlquilerDTO;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.abnimavade.alquileres.dtos.AlquilerInicioDTO;
import com.abnimavade.alquileres.models.Alquiler;
import com.abnimavade.alquileres.models.Tarifa;
import com.abnimavade.alquileres.repositories.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("alquilerService")

public class AlquilerService{


    private AlquilerRepository alquilerRepository;
    private CotizacionesService cotizacionesService;
    private TarifaService tarifaService;
    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository,CotizacionesService cotizacionesService, TarifaService tarifaService)
    {
        this.alquilerRepository = alquilerRepository;
        this.cotizacionesService = cotizacionesService;
        this.tarifaService = tarifaService;
    }

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
        alquilerDTO.setIdTarifa(alquiler.getTarifa().getTarifaId());
        return alquilerDTO;
    }


   /* public AlquilerDTO update(Long id,AlquilerDTO alquilerDTO) {
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
        alquiler.setTarifa(alquilerDTO.getTarifa());
        alquiler = alquilerRepository.save(alquiler);
        alquilerDTO.setAlquilerId(alquiler.getAlquilerId());
        return alquilerDTO;
    } */

    public String delete(Long id) {
        alquilerRepository.deleteById(id);
        return "Alquiler deleted";
    }

    public Alquiler createDesde(AlquilerInicioDTO alquilerDTO) {

        // Este create pasa la estacion en el link y el otro en el body
        Alquiler alquiler = new Alquiler();
        // Id del cliente
        alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado Alquiler
        alquiler.setEstadoTarifa(1);
        // Estaciones de retiro
        alquiler.setIdEstacionRetiro(alquilerDTO.getIdEstacionRetiro());
        // Estación de devolucion
        alquiler.setIdEstacionDevolucion(0);
        // Fecha de retiro
        alquiler.setFechaHoraRetiro(alquilerDTO.getFechaHoraRetiro());
        // Fecha de devolucion
        alquiler.setFechaHoraDevolucion(LocalDateTime.MIN);
        // Monto total e id tarifa
        Tarifa tarifa = this.tarifaService.getTarifaById(alquilerDTO.getIdTarifa());
        if (tarifa.getDefinicion().equals("S")) {
            // La tarifa es por día de la semana
            // Chequear que la fecha de retiro sea en el mismo dia de la semana que la tarifa
            if (alquilerDTO.getFechaHoraRetiro().getDayOfWeek().getValue() != tarifa.getDiaSemana()) {
                throw new RuntimeException("La fecha de retiro no coincide con la tarifa");
            }

        }
        if (tarifa.getDefinicion().equals("C")) {
            // La tarifa es definida por dia, mes y año
            // Chequear que la fecha de retiro sea en el mes, dia y anio de la tarifa
            if (alquilerDTO.getFechaHoraRetiro().getDayOfMonth() != tarifa.getDiaMes() ||
                alquilerDTO.getFechaHoraRetiro().getMonthValue() != tarifa.getMes()
                || alquilerDTO.getFechaHoraRetiro().getYear() != tarifa.getAnio()) {
                throw new RuntimeException("La fecha de retiro no coincide con la tarifa");
            }

        }
        alquiler.setMonto(0);
        alquiler.setTarifa(tarifa);
        alquiler = alquilerRepository.save(alquiler);
        return alquiler;
    }

    public List<AlquilerDTO> getAlquileresTarifasMayores(double monto) {
        List<Alquiler> alquileres = alquilerRepository.findAll();
        return alquileres.stream().filter(alquiler -> alquiler.getMonto() > monto).map(this::convertToDto).collect(Collectors.toList());
    }

    public AlquilerDTO finalizarAlquiler(long id, String moneda, AlquilerDTO alquilerDTO, int estacionId, LocalDateTime fechaHoraDevolucion) {
        // El 2 simboliza que el alquiler esta finalizado
        long estadoTarifa = 2;
        Alquiler alquiler = alquilerRepository.findById(id).get();
        // Id del cliente
        //alquiler.setIdCliente(alquilerDTO.getIdCliente());
        // Estado Alquiler
        alquiler.setEstadoTarifa(estadoTarifa);
        // Estaciones de retiro y devolucion
        //alquiler.setIdEstacionRetiro(alquilerDTO.getIdEstacionRetiro());
        alquiler.setIdEstacionDevolucion(estacionId);
        // Fecha de retiro y devolucion
        //alquiler.setFechaHoraRetiro(alquilerDTO.getFechaHoraRetiro());
        alquiler.setFechaHoraDevolucion(fechaHoraDevolucion);
        // Monto total e id tarifa
        double montoMedio = calcularMonto(alquilerDTO.getFechaHoraRetiro(),fechaHoraDevolucion,alquilerDTO);
        double tarifaFinal = calcularTarifa(estacionId, alquilerDTO.getIdEstacionRetiro(), montoMedio, alquilerDTO);
        double tarifaConvertida = cotizacionesService.convertirMoneda(moneda, tarifaFinal);
        alquiler.setMonto(tarifaConvertida);
        alquiler = alquilerRepository.save(alquiler);
        alquilerDTO.setAlquilerId(alquiler.getAlquilerId());
        return alquilerDTO;
     
    }

    private double calcularMonto(LocalDateTime fechaHoraRetiro, LocalDateTime fechaHoraDevolucion, AlquilerDTO alquilerDTO) {
        double montoFijo,montoMedio = 0;
        Tarifa tarifa = this.tarifaService.getTarifaById(alquilerDTO.getIdTarifa());
        montoFijo = tarifa.getMontoFijoAlquiler();
        Duration duracion = Duration.between(fechaHoraRetiro, fechaHoraDevolucion);
        long difMinutos = duracion.toMinutes();

        if ( difMinutos<= 30) {
           montoMedio = montoFijo + (tarifa.getMontoMinutoFraccion() * difMinutos);
        }
        else
        {

            long difHoras = duracion.toHours();
            montoMedio = montoFijo + (tarifa.getMontoHora() * difHoras);
        }

       return montoMedio;

    }


    private double calcularTarifa(int idEstacionDevolucion, int idEstacionRetiro, double montoMedio, AlquilerDTO alquilerDTO){
        Tarifa tarifa = this.tarifaService.getTarifaById(alquilerDTO.getIdTarifa());
        // Necesito las latitudes y longitudes de las estaciones para calcular las distancias entre ellas
        EstacionService estacionService = new EstacionService();
        Double distancia = estacionService.getDistanciaEstaciones(idEstacionDevolucion,idEstacionRetiro);

        double tarifaFinal = ((distancia/1000) * tarifa.getMontoKm()) + montoMedio;

        return tarifaFinal;
    }

    public boolean existsById(Long id) {
        return alquilerRepository.existsById(id);
    }
}