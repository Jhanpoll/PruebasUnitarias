package org.app.org.junit_app.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private List<CuentaBancaria> cuentas;
	private String nombre;
	
	public Banco() {
		cuentas = new ArrayList<>();
	}
	
	public List<CuentaBancaria> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaBancaria> cuentas) {
		this.cuentas = cuentas;
	}
	public void addCuenta(CuentaBancaria cuenta) {
		cuentas.add(cuenta);
		cuenta.setBanco(this );
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void transferir(CuentaBancaria origen, CuentaBancaria destino, BigDecimal monto) {
		
		origen.debito(monto);
		destino.credito(monto);
	}

}
