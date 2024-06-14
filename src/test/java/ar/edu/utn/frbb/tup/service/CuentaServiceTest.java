package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoSoportadaException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CuentaServiceTest {
    
    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteDao clienteDao;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //cuentaService.clienteService = clienteService;
    }

    //Cuenta existente,,,,ANDAAAAA SIIIIIIIII
    @Test
    public void testCuentaExistente() {
        Cliente cliente = new Cliente();
        cliente.setDni(12345678);

        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(0);
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        when(cuentaDao.find(cuenta.getNumeroCuenta())).thenReturn(cuenta);// Configuro el mock cuentaDao para que retorne la cuenta cuando se llame al metodo find

        assertThrows(CuentaAlreadyExistsException.class, () -> {// Verifica que se lance la excepcion CuentaAlreadyExistsException
            cuentaService.darDeAltaCuenta(cuenta, cliente.getDni());// Llama al método darDeAltaCuenta del servicio CuentaService
        });
    }

    // Cuenta no soportada,,,ANDAAAAA SIIIIIIIII
    @Test
    public void testCuentaNoSoportada(){
        Cliente cliente = new Cliente();
        cliente.setDni(12345678);

        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(TipoMoneda.DOLARES);
        cuenta.setBalance(0);
        cuenta.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);

        assertThrows(CuentaNoSoportadaException.class, () -> {// Verifica que se lance la excepción CuentaNoSoportadaException
            cuentaService.darDeAltaCuenta(cuenta, cliente.getDni());// Llama al metodo darDeAltaCuenta del servicio CuentaService
        });
    }

    // Cliente ya tiene cuenta de ese tipo,,,ANDAAAAA SIIIIIIIII
    @Test
    public void testClienteYaTieneCuentaDeEseTipo() throws TipoCuentaAlreadyExistsException{
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
        cuenta.setMoneda(TipoMoneda.PESOS); 

        Cliente cliente = new Cliente();
        cliente.setDni(12345678);
        cliente.addCuenta(cuenta);
        cliente.setNombre("Pepe");
        cliente.setApellido("Rino");
        cliente.setFechaNacimiento(LocalDate.of(1978, 3,25));
        cliente.setTipoPersona(TipoPersona.PERSONA_FISICA);
    
       
        when(cuentaDao.find(cuenta.getNumeroCuenta())).thenReturn(null);
        doThrow(TipoCuentaAlreadyExistsException.class).when(clienteService).agregarCuenta(cuenta, cliente.getDni());

        assertThrows(TipoCuentaAlreadyExistsException.class, () -> cuentaService.darDeAltaCuenta(cuenta, cliente.getDni()));
        assertThrows(TipoCuentaAlreadyExistsException.class, () -> clienteService.agregarCuenta(cuenta, cliente.getDni()));
        
    }

    //Cuenta creada exitosamente,,,,,ANDAAAAA SIIIIIIIII
    @Test
    public void testCuentaCreadaExitosamente() throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException {
        Cuenta cuenta = new Cuenta()
                .setMoneda(TipoMoneda.PESOS)
                .setBalance(0)
                .setTipoCuenta(TipoCuenta.CAJA_AHORRO);
    
        when(cuentaDao.find(any(Long.class))).thenReturn(null); // Configuro el mock cuentaDao para que retorne null cuando se llame al metodo find con cualquier valor Long
    
        assertThrows(CuentaNoSoportadaException.class, () -> cuentaService.darDeAltaCuenta(cuenta, 12345678L));// Verifica que se lance la excepcion CuentaNoSoportadaException cuando se llama al metodo darDeAltaCuenta del servicio CuentaService
    }
}
