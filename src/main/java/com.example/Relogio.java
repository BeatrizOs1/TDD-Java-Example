package com.example;

public class Relogio {
    private int horas;
    private int minutos;
    private int segundos;

    public Relogio() {
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
    }

    public void programarTempo(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public void reiniciar() {
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
    }

    public String getTempo() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
}
