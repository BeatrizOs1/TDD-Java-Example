package com.example;

public class Relogio {

    private int horas; 
    private int minutos; 
    private int segundos; 
    private boolean cronometro; 
    private long inicio; 
    private long fim; 

    public Relogio() {
        horas = 0;
        minutos = 0;
        segundos = 0;
        cronometro = false;
        inicio = 0;
        fim = 0;
    }

    public void programar(int horas, int minutos, int segundos) {
        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59 || segundos < 0 || segundos > 59) {
            throw new IllegalArgumentException("Parâmetros inválidos para programar o relógio");
        }
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public void reiniciar() {
        horas = 0;
        minutos = 0;
        segundos = 0;
    }

    public void iniciarCronometro() {
        inicio = System.currentTimeMillis();
        cronometro = true;
    }

    public void pararCronometro() {
        fim = System.currentTimeMillis();
        cronometro = false;
    }

    public double tempoDecorrido() {
        double diferenca = (fim - inicio) / 1000.0;
        return diferenca;
    }

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

     public void atualizar(int segundos) {
        this.segundos += segundos;
        if (this.segundos > 59) {
            int minutosExtras = this.segundos / 60;
            this.segundos = 0;
            this.minutos += minutosExtras;
        }
        if (this.minutos > 59) {
            int horasExtras = this.minutos / 60;
            this.minutos = 0;
            this.horas += horasExtras;
        }
        if (this.horas > 23) {
            int diasExtras = this.horas / 24;
            this.horas = 0;
        }
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public boolean isCronometro() {
        return cronometro;
    }

    public long getInicio() {
        return inicio;
    }

    public long getFim() {
        return fim;
    }
}