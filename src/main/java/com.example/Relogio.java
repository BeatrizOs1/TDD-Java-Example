package com.example;

public class Relogio {

    private int horas; 
    private int minutos; 
    private int segundos; 
    private boolean cronometro; 
    private long inicio; 
    private long fim; 

    public Relogio() {
        // Inicializa os atributos com valores padrão
        horas = 0;
        minutos = 0;
        segundos = 0;
        cronometro = false;
        inicio = 0;
        fim = 0;
    }

    // Método que recebe as horas, minutos e segundos e os atribui aos atributos correspondentes
    public void programar(int horas, int minutos, int segundos) {
        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59 || segundos < 0 || segundos > 59) {
            throw new IllegalArgumentException("Parâmetros inválidos para programar o relógio");
        }
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    // Método que zera os atributos de horas, minutos e segundos
    public void reiniciar() {
        horas = 0;
        minutos = 0;
        segundos = 0;
    }

    // Método que marca o tempo atual como o início do intervalo e ativa o atributo cronometro
    public void iniciarCronometro() {
        inicio = System.currentTimeMillis();
        cronometro = true;
    }

    // Método que marca o tempo atual como o fim do intervalo e desativa o atributo cronometro
    public void pararCronometro() {
        fim = System.currentTimeMillis();
        cronometro = false;
    }

    // Método que retorna a diferença entre o fim e o início do intervalo em segundos
    public double tempoDecorrido() {
        double diferenca = (fim - inicio) / 1000.0;
        return diferenca;
    }

    // Método que retorna uma string com a hora no formato desejado (24h ou AM/PM)
    public String imprimirHora(boolean ampm) {
        String hora = "";
        
        if (ampm) { 
            int horasAMPM = horas % 12;
            if (horasAMPM == 0) {
                horasAMPM = 12;
            }
            hora = String.format("%02d:%02d:%02d", horasAMPM, minutos, segundos);
            if (horas < 12) {
                hora += " AM";
            } else {
                hora += " PM";
            }
        } else {
            hora = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        }
        return hora;
        
        }