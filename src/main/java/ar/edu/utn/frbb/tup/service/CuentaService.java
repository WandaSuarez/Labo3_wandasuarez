package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoSoportadaException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CuentaService {
    CuentaDao cuentaDao = new CuentaDao();
    private static final Map<TipoCuenta, TipoMoneda> cuentasSoportadas = new HashMap<TipoCuenta, TipoMoneda>() {{
        put(TipoCuenta.CAJA_AHORRO, TipoMoneda.PESOS);
        put(TipoCuenta.CUENTA_CORRIENTE, TipoMoneda.PESOS);
        put(TipoCuenta.CAJA_AHORRO, TipoMoneda.DOLARES);
    }};

    @Autowired
    ClienteService clienteService;

    //Generar casos de test para darDeAltaCuenta
    //    1 - cuenta existente
    //    2 - cuenta no soportada
    //    3 - cliente ya tiene cuenta de ese tipo
    //    4 - cuenta creada exitosamente
    public void darDeAltaCuenta(Cuenta cuenta, long dniTitular) throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, CuentaNoSoportadaException {
        if (cuentaDao.find(cuenta.getNumeroCuenta()) != null) {
            throw new CuentaAlreadyExistsException("La cuenta " + cuenta.getNumeroCuenta() + " ya existe.");
        }

        if (!tipoDeCuentaEstaSoportada(cuenta)) {
            throw new CuentaNoSoportadaException("El tipo de cuenta " + cuenta.getTipoCuenta() + " en " + cuenta.getMoneda() + " no está soportada.");
        }

        if (find(cuenta.getNumeroCuenta()) == cuentaDao.find(cuenta.getNumeroCuenta())) {
            throw new TipoCuentaAlreadyExistsException("La cuenta " + cuenta.getNumeroCuenta() + " ya existe.");
        }

        clienteService.agregarCuenta(cuenta, dniTitular);
        cuentaDao.save(cuenta);
    }

    
    public boolean tipoDeCuentaEstaSoportada(Cuenta cuenta) {
        return cuentasSoportadas.containsKey(cuenta.getTipoCuenta()) && cuentasSoportadas.get(cuenta.getTipoCuenta()) == cuenta.getMoneda();
    }

    public Cuenta find(long id) {
        return cuentaDao.find(id);
    }
}
