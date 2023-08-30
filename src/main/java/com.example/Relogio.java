package com.example;

public class Relogio {

    // Atributos da classe Relogio
    private int horas; // Horas do relógio
    private int minutos; // Minutos do relógio
    private int segundos; // Segundos do relógio
    private boolean cronometro; // Indica se o cronômetro está ativado ou não
    private long inicio; // Tempo inicial do intervalo marcado pelo cronômetro em milissegundos
    private long fim; // Tempo final do intervalo marcado pelo cronômetro em milissegundos

    // Construtor da classe Relogio
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
        // Verifica se os parâmetros são válidos (entre 0 e 23 para horas, entre 0 e 59 para minutos e segundos)
        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59 || segundos < 0 || segundos > 59) {
            throw new IllegalArgumentException("Parâmetros inválidos para programar o relógio");
        }
        // Atribui os parâmetros aos atributos
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    // Método que zera os atributos de horas, minutos e segundos
    public void reiniciar() {
        // Atribui zero aos atributos
        horas = 0;
        minutos = 0;
        segundos = 0;
    }

    // Método que marca o tempo atual como o início do intervalo e ativa o atributo cronometro
    public void iniciarCronometro() {
        // Obtém o tempo atual em milissegundos e o atribui ao atributo inicio
        inicio = System.currentTimeMillis();
        // Ativa o atributo cronometro
        cronometro = true;
    }

    // Método que marca o tempo atual como o fim do intervalo e desativa o atributo cronometro
    public void pararCronometro() {
        // Obtém o tempo atual em milissegundos e o atribui ao atributo fim
        fim = System.currentTimeMillis();
        // Desativa o atributo cronometro
        cronometro = false;
    }

    // Método que retorna a diferença entre o fim e o início do intervalo em segundos
    public double tempoDecorrido() {
        // Calcula a diferença em milissegundos e divide por 1000 para obter em segundos
        double diferenca = (fim - inicio) / 1000.0;
        return diferenca;
    }

    // Método que retorna uma string com a hora no formato desejado (24h ou AM/PM)
    public String imprimirHora(boolean ampm) {
        // Cria uma variável para armazenar a string da hora
        String hora = "";
        
        if (ampm) { 
            // Se for AM/PM, converte as horas para o intervalo de 1 a 12
            int horasAMPM = horas % 12;
            if (horasAMPM == 0) {
                // Se for meia-noite, ajusta para 12
                horasAMPM = 12;
            }
            // Formata a string da hora com dois dígitos para cada campo e separados por dois pontos
            hora = String.format("%02d:%02d:%02d", horasAMPM, minutos, segundos);
            // Adiciona o sufixo AM ou PM de acordo com as horas
            if (horas < 12) {
                hora += " AM";
            } else {
                hora += " PM";
            }
        } else {
            // Se for 24h, formata a string da hora com dois dígitos para cada campo e separados por dois pontos
            hora = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        }
        // Retorna a string da hora
        return hora;
        
        }