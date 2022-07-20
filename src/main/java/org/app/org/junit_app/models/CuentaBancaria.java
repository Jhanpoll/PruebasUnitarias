package org.app.org.junit_app.models;

import java.math.BigDecimal;

import org.app.org.junit_app.exceptions.DineroInsuficienteException;

public class CuentaBancaria {
	
	private Banco banco;
	private String persona;
	private BigDecimal saldo;
	
	
	
	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	
	public CuentaBancaria(String persona, BigDecimal saldo) {
		this.persona=persona;
		this.saldo = saldo;
	}
	
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public void debito(BigDecimal monto) {
		BigDecimal nuevoSaldo = this.saldo.subtract(monto);
		if(nuevoSaldo.compareTo(BigDecimal.ZERO)<0) {
				throw new DineroInsuficienteException("DINERO INSUFICIENTE");
 		}
		this.saldo = nuevoSaldo;
	}
	public void credito(BigDecimal monto) {
		this.saldo = this.saldo.add(monto);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (! (obj instanceof CuentaBancaria)) {
			return false;
		}
		CuentaBancaria c = (CuentaBancaria)obj;
		
		return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
	}
	
	
}
