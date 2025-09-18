package com.project;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonAdd1;
    @FXML
    private Button buttonAdd2;
    @FXML
    private Button buttonAdd3;
    @FXML
    private Button buttonAdd4;
    @FXML
    private Button buttonAdd5;
    @FXML
    private Button buttonAdd6;
    @FXML
    private Button buttonAdd7;
    @FXML
    private Button buttonAdd8;
    @FXML
    private Button buttonAdd9;
    @FXML
    private Button buttonAdd10;
    @FXML
    private Button buttonAdd11;
    @FXML
    private Button buttonAdd12;
    @FXML
    private Button buttonAdd13;
    @FXML
    private Button buttonAdd14;
    @FXML
    private Button buttonAdd15;

    @FXML
    private Text textCounter;

    

    @FXML
    private void actionButton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String txt = btn.getText();
        textCounter.setText(textCounter.getText() + txt);
    }
    @FXML
    private void actionButtonIgual(ActionEvent event) {
        String expr = textCounter.getText();

        try {
            double resultado = calculo(expr);
            textCounter.setText(String.valueOf(resultado));
        } catch (Exception e) {
            textCounter.setText("Error");
        }
    }

    private double calculo(String expr){
        expr = expr.replace("x", "*");
        

        List<String> tokens = new ArrayList<>();
        StringBuilder numero = new StringBuilder();
        
        //separamos el texto
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                numero.append(c);
            //comprobamos negativos
            }else if (c == '-') {
            
                if (numero.length() == 0 && (tokens.isEmpty() )) {
                    
                    numero.append(c);
                } else {
                    if (numero.length() > 0) {
                        tokens.add(numero.toString());
                        numero.setLength(0);
                    }
                    tokens.add(String.valueOf(c));
                }
            } else {
                if (numero.length() > 0) {
                    tokens.add(numero.toString());
                    numero.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }
        if (numero.length() > 0) {
            tokens.add(numero.toString());
        }

        //primero hacemos la multiplicacion y division
        for (int i = 0; i < tokens.size(); ) {
            String t = tokens.get(i);
            if (t.equals("*") || t.equals("/")) {
                    double izq = Double.parseDouble(tokens.get(i - 1));
                    double der = Double.parseDouble(tokens.get(i + 1));
                    double res = t.equals("*") ? izq * der : izq / der;
                    tokens.set(i - 1, String.valueOf(res));
                    tokens.remove(i);
                    tokens.remove(i);
            } else {
                i++;
            }
        }

        //despues sumamos o restamos
        double resultado = Double.parseDouble(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            double val = Double.parseDouble(tokens.get(i + 1));
            if (op.equals("+")) resultado += val;
            if (op.equals("-")) resultado -= val;
        }

        return resultado;
    }

    @FXML
    private void limpiar(ActionEvent event){
         textCounter.setText("");
    }
}
