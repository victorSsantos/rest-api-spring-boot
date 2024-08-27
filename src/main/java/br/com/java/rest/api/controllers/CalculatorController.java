package br.com.java.rest.api.controllers;

import br.com.java.rest.api.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static br.com.java.rest.api.services.CalculatorService.convertToDouble;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService service;

    @RequestMapping("/sum")
    public ResponseEntity<Double> sum(@RequestParam(value="number_1", defaultValue = "0.0") String n1,
                                      @RequestParam(value="number_2", defaultValue = "0.0") String n2){

        return ResponseEntity.ok(service.sum(convertToDouble(n1),convertToDouble(n2)));
    }

    @RequestMapping("/subtract")
    public ResponseEntity<Double> subtract(@RequestParam(value="number_1", defaultValue = "0.0") String n1,
                                      @RequestParam(value="number_2", defaultValue = "0.0") String n2){

        return ResponseEntity.ok(service.subtract(convertToDouble(n1),convertToDouble(n2)));
    }


    @RequestMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestParam(value="number_1", defaultValue = "0.0") String n1,
                                           @RequestParam(value="number_2", defaultValue = "0.0") String n2){

        return ResponseEntity.ok(service.multiply(convertToDouble(n1),convertToDouble(n2)));
    }

    @RequestMapping("/divide")
    public ResponseEntity<Double> divide(@RequestParam(value="number_1", defaultValue = "0.0") String n1,
                                           @RequestParam(value="number_2", defaultValue = "0.0") String n2){

        return ResponseEntity.ok(service.divide(convertToDouble(n1),convertToDouble(n2)));
    }
}
