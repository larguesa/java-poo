package com.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        
        Javalin app = Javalin.create().start(7070);
        System.out.println("Servidor rodando em http://localhost:7070");
        // Rota / (raiz)
        app.get("/", ctx -> {
            ctx.result("Hello World, Javalin!");
        });
        // Rota /now
        TimeController timeController = new TimeController();
        app.get("/now", timeController::getNow);
        app.get("/greeting", timeController::getGreeting);
    }
}