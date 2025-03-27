package com.scaler.productservicemod.ControllerAdvices;

import com.scaler.productservicemod.Dto.ArithmaticExeptionDto;
import com.scaler.productservicemod.Dto.ExceptionDto;
import com.scaler.productservicemod.exeptions.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmaticExeptionDto> handlerArithmaticExeption(){
        ArithmaticExeptionDto arithmaticExeptionDto = new ArithmaticExeptionDto();
        arithmaticExeptionDto.setMessage("Something has gone Wrong");
        return new ResponseEntity<>(arithmaticExeptionDto, HttpStatus.OK);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundException() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistsExeption(ProductNotExistsException exception){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetails("Check the product id. It probably does't exist. ");

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> genricException(Exception e){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
