import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class RelogioTest {

    // Cria um objeto da classe Relogio para usar nos testes
    private Relogio relogio;

    // Inicializa o objeto antes de cada teste
    @Before
    public void setUp() {
        relogio = new Relogio();
    }

    // Testa se o construtor da classe Relogio atribui os valores corretos aos atributos
    @Test
    public void testConstrutor() {
        assertEquals(0, relogio.getHoras());
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
        assertFalse(relogio.isCronometro());
        assertEquals(0, relogio.getInicio());
        assertEquals(0, relogio.getFim());
    }

    // Testa se o método programar recebe os parâmetros corretos e os atribui aos atributos
    @Test
    public void testProgramar() {
        relogio.programar(12, 34, 56);
        assertEquals(12, relogio.getHoras());
        assertEquals(34, relogio.getMinutos());
        assertEquals(56, relogio.getSegundos());
    }

    // Testa se o método programar lança uma exceção se receber parâmetros inválidos
    @Test(expected = IllegalArgumentException.class)
    public void testProgramarInvalido() {
        relogio.programar(25, 60, 61);
    }

    // Testa se o método reiniciar zera os atributos de horas, minutos e segundos
    @Test
    public void testReiniciar() {
        relogio.programar(12, 34, 56);
        relogio.reiniciar();
        assertEquals(0, relogio.getHoras());
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
    }

    // Testa se o método iniciarCronometro marca o tempo atual como o início do intervalo e ativa o atributo cronometro
    @Test
    public void testIniciarCronometro() {
        long tempo = System.currentTimeMillis();
        relogio.iniciarCronometro();
        assertTrue(relogio.isCronometro());
        assertEquals(tempo, relogio.getInicio(), 10); // Permite uma margem de erro de 10 milissegundos
    }

    // Testa se o método pararCronometro marca o tempo atual como o fim do intervalo e desativa o atributo cronometro
    @Test
    public void testPararCronometro() {
        long tempo = System.currentTimeMillis();
        relogio.pararCronometro();
        assertFalse(relogio.isCronometro());
        assertEquals(tempo, relogio.getFim(), 10); // Permite uma margem de erro de 10 milissegundos
    }

    // Testa se o método tempoDecorrido retorna a diferença entre o fim e o início do intervalo em segundos
    @Test
    public void testTempoDecorrido() {
        relogio.iniciarCronometro();
        try {
            Thread.sleep(5000); // Espera 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        relogio.pararCronometro();
        assertEquals(5, relogio.tempoDecorrido(), 0.1); // Permite uma margem de erro de 0.1 segundos
    }

    // Testa se o método imprimirHora retorna uma string com a hora no formato 24h
    @Test
    public void testImprimirHora24h() {
        relogio.programar(12, 34, 56);
        assertEquals("12:34:56", relogio.imprimirHora(false)); // false indica que não é AM/PM
    }

    // Testa se o método imprimirHora retorna uma string com a hora no formato AM/PM
    @Test
    public void testImprimirHoraAMPM() {
        relogio.programar(12, 34, 56);
        assertEquals("12:34:56 PM", relogio.imprimirHora(true)); // true indica que é AM/PM
    }

    // Testa se o método atualizar recebe um parâmetro com a passagem do tempo em segundos e incrementa os atributos de horas, minutos e segundos
    @Test
    public void testAtualizar() {
        relogio.programar(12, 34, 56);
        relogio.atualizar(5); // Passaram 5 segundos
        assertEquals(12, relogio.getHoras());
        assertEquals(35, relogio.getMinutos());
        assertEquals(1, relogio.getSegundos());
    }

    // Testa se o método atualizar ajusta os atributos de horas, minutos e segundos quando há mudança de unidade
    @Test
    public void testAtualizarMudancaDeUnidade() {
        relogio.programar(23, 59, 59);
        relogio.atualizar(1); // Passou 1 segundo
        assertEquals(0, relogio.getHoras()); // Mudou de dia
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
    }
}
