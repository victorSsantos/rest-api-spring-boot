package br.com.java.rest.api.services;

import br.com.java.rest.api.services.exceptions.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Double sum (double n1, double n2){
        return n1 + n2;
    }

    public Double subtract(double n1, double n2) {
        return n1 - n2;
    }

    public Double multiply(double n1, double n2) {
        return n1 * n2;
    }

    public Double divide(double n1, double n2) {
        return n1 / n2;
    }

    public static Double convertToDouble(String text){
        try{
            return Double.parseDouble(text.replace(',','.'));
        }
        catch (NumberFormatException e){
            throw new UnsupportedMathOperationException("Please, set a numeric value :: " + e.getMessage());
        }
    }
}
