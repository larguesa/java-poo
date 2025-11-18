package com.example;

import io.javalin.http.Context;
import java.time.LocalDateTime;
import java.util.Map;

public class TimeController {

    public void getNow(Context ctx) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        Map<String, Object> respostaJson = Map.of(
            "timestamp", dataHoraAtual.toString(),
            "data", dataHoraAtual.toLocalDate().toString(),
            "hora", dataHoraAtual.toLocalTime().toString()
        );

        ctx.json(respostaJson);
    }

    public void getGreeting(Context ctx) {
        LocalDateTime agora = LocalDateTime.now();
        int hora = agora.getHour();
        String saudacao;

        if (hora >= 5 && hora < 12) {
            saudacao = "Bom dia";
        } else if (hora >= 12 && hora < 18) {
            saudacao = "Boa tarde";
        } else if (hora >= 18 && hora < 24) {
            saudacao = "Boa noite";
        } else { // 00:00 a 04:59
            saudacao = "Vá dormir";
        }

        String resposta = String.format("Agora: %s. %s!", 
            agora.toLocalTime().toString(), 
            saudacao);
            
        ctx.contentType("text/plain"); // Define o tipo de resposta
        ctx.result(resposta);
    }

}