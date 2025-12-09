package com.application.vocealuga.service;

import com.application.vocealuga.dto.ReservaDto;

public interface ReservaService {
    void saveReserva(ReservaDto reservaDto);
    void deleteReserva();
    void updateReserva();
}
