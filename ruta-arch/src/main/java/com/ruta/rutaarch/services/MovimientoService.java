package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Movimiento;
import java.util.List;

public interface MovimientoService {
    Movimiento saveMovimiento(Movimiento movimiento);
    Movimiento getMovimientoById(Long id);
    List<Movimiento> getAllMovimientos();
    Movimiento updateMovimiento(Movimiento movimiento);
    void deleteMovimiento(Long id);
}
