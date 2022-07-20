package org.app.org.junit_app.models;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.app.org.junit_app.exceptions.DineroInsuficienteException;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


//@TestInstance(Lifecycle.PER_CLASS)
public class CuentaBancariaTest {
	
	public void initMetodoTest() {
		System.out.println("iniciando un metodo");
	}

	@Test
	@DisplayName("probando el nombre de la cuenta")
	public void testNombreCuenta() {
		CuentaBancaria cuenta = new CuentaBancaria("jesus", new BigDecimal(45.5));
		// cuenta.setPersona("jesus");
		String esperado = "jesus";
		String real = cuenta.getPersona();
		Assertions.assertEquals(esperado, real, () -> "el nombre de la cuenta no es la que esperaba");
		assertTrue(real.equals("jesus"), () -> "nombre cuenta esparada debe ser igua a la real");
	}

	@Test
	public void testSaldoCuenta() {
		CuentaBancaria cuentaBancaria = new CuentaBancaria("jhanpoll", new BigDecimal(40.5));
		double value = cuentaBancaria.getSaldo().doubleValue();
		double delta = 40.4;
		assertEquals(40.5, value, delta, "resultado");

	}

	@Test
	public void testReferenciaCuenta() {

		CuentaBancaria cuentaBancaria = new CuentaBancaria("isaias", new BigDecimal("89.7"));
		CuentaBancaria cuentaBancaria2 = new CuentaBancaria("isaias", new BigDecimal("89.7"));

		assertEquals("son iguales", cuentaBancaria, cuentaBancaria2);
	}

	@Test
	public void testDebitoCuenta() {
		CuentaBancaria cuentaBancaria = new CuentaBancaria("chero", new BigDecimal("1000.12345"));
		cuentaBancaria.debito(new BigDecimal(100));
		assertNotNull(cuentaBancaria.getSaldo());
		assertEquals(900, cuentaBancaria.getSaldo().intValue());
		assertEquals("900.12345", cuentaBancaria.getSaldo().toPlainString());
	}

	@Test
	public void testCreditoCuenta() {
		CuentaBancaria cuentaBancaria = new CuentaBancaria("chero", new BigDecimal("1000.12345"));
		cuentaBancaria.credito(new BigDecimal(100));
		assertNotNull(cuentaBancaria.getSaldo());
		assertEquals(1100, cuentaBancaria.getSaldo().intValue());
		assertEquals("1100.12345", cuentaBancaria.getSaldo().toPlainString());
	}

	@Test
	public void testDineroInsuficienteExceptionCuenta() {
		CuentaBancaria cuenta = new CuentaBancaria("jose", new BigDecimal("1000.12345"));
		Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
			cuenta.debito(new BigDecimal("1500"));
		});
		String actual = exception.getMessage();
		String esperado = "DINERO INSUFICIENTE";
		assertEquals(esperado, actual);
	}

	@Test
	public void testTranferirDineroCuentas() {
		CuentaBancaria cuenta = new CuentaBancaria("jhon", new BigDecimal("3000"));
		CuentaBancaria cuenta2 = new CuentaBancaria("conor", new BigDecimal("2000.12345"));
		Banco banco = new Banco();
		banco.setNombre("banco de jhon");
		banco.transferir(cuenta2, cuenta, new BigDecimal("500.12345"));
		assertEquals("1500.00000", cuenta2.getSaldo().toPlainString());
		assertEquals("3500.12345", cuenta.getSaldo().toPlainString());
	}

	@Test

	public void testRelacionBancoCuentas() {
		CuentaBancaria cuenta = new CuentaBancaria("jhon", new BigDecimal("3000"));
		CuentaBancaria cuenta2 = new CuentaBancaria("conor", new BigDecimal("2000.12345"));
		Banco banco = new Banco();
		banco.addCuenta(cuenta);
		banco.addCuenta(cuenta2);
		banco.setNombre("banco de jhon");
		banco.transferir(cuenta2, cuenta, new BigDecimal("500.12345"));

		assertAll(() -> assertEquals("1500.00000", cuenta2.getSaldo().toPlainString()),
				() -> assertEquals("3500.12345", cuenta.getSaldo().toPlainString()),
				() -> assertEquals(2, banco.getCuentas().size()),
				() -> assertEquals("banco de jhon", cuenta.getBanco().getNombre()), () -> {
					assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("jhon")));
				});
		
		
	}

}
