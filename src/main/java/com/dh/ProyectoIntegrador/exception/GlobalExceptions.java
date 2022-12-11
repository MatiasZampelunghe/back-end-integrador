package com.dh.ProyectoIntegrador.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions{

    private static final Logger LOGGER=Logger.getLogger(GlobalExceptions.class);

    @ExceptionHandler({com.dh.ProyectoIntegrador.exception.ResourceNotFoundException.class})
    public ResponseEntity<String> procesarResourceNotFoundException (com.dh.ProyectoIntegrador.exception.ResourceNotFoundException rnfe){
        LOGGER.error("El sistema detecto un problema, se registro el siguiente mensaje " + rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

    @ExceptionHandler({com.dh.ProyectoIntegrador.exception.BadRequestException.class})
    public ResponseEntity<String> procesarBadRequestException(BadRequestException bre){
        LOGGER.error("El sistema detecto un problema, se registro el siguiente mensaje " + bre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }

}
