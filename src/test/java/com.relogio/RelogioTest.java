import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class RelogioTest {

    private Relogio relogio;

    @Before
    public void setUp() {
        relogio = new Relogio();
    }

    @Test
    public void testConstrutor() {
        assertEquals(0, relogio.getHoras());
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
        assertFalse(relogio.isCronometro());
        assertEquals(0, relogio.getInicio());
        assertEquals(0, relogio.getFim());
    }

    @Test
    public void testProgramar() {
        relogio.programar(12, 34, 56);
        assertEquals(12, relogio.getHoras());
        assertEquals(34, relogio.getMinutos());
        assertEquals(56, relogio.getSegundos());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProgramarInvalido() {
        relogio.programar(25, 60, 61);
    }

    @Test
    public void testReiniciar() {
        relogio.programar(12, 34, 56);
        relogio.reiniciar();
        assertEquals(0, relogio.getHoras());
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
    }

    @Test
    public void testIniciarCronometro() {
        long tempo = System.currentTimeMillis();
        relogio.iniciarCronometro();
        assertTrue(relogio.isCronometro());
        assertEquals(tempo, relogio.getInicio(), 10); 
    }

    @Test
    public void testPararCronometro() {
        long tempo = System.currentTimeMillis();
        relogio.pararCronometro();
        assertFalse(relogio.isCronometro());
        assertEquals(tempo, relogio.getFim(), 10); 
    }

    @Test
    public void testTempoDecorrido() {
        relogio.iniciarCronometro();
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        relogio.pararCronometro();
        assertEquals(5, relogio.tempoDecorrido(), 0.1); 
    }

    @Test
    public void testImprimirHora24h() {
        relogio.programar(12, 34, 56);
        assertEquals("12:34:56", relogio.imprimirHora(false)); 
    }

    @Test
    public void testImprimirHoraAMPM() {
        relogio.programar(12, 34, 56);
        assertEquals("12:34:56 PM", relogio.imprimirHora(true)); 
    }

    @Test
    public void testAtualizar() {
        relogio.programar(12, 34, 56);
        relogio.atualizar(5); 
        assertEquals(12, relogio.getHoras());
        assertEquals(35, relogio.getMinutos());
        assertEquals(1, relogio.getSegundos());
    }

    @Test
    public void testAtualizarMudancaDeUnidade() {
        relogio.programar(23, 59, 59);
        relogio.atualizar(1); 
        assertEquals(0, relogio.getHoras()); 
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
    }
}
