package com.krakedev.peaje.servicios;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.util.ValidadorUtil;

public class EstacionPeaje {

	private int codigoEstacion = 500;
	private double tarifaLiviano = 1.00;
	private double tarifaPesado = 2.50;

	public EstacionPeaje() {

	}

	public int getCodigoEstacion() {
		return codigoEstacion;
	}

	public void setCodigoEstacion(int codigoEstacion) {
		this.codigoEstacion = codigoEstacion;
	}

	public double getTarifaLiviano() {
		return tarifaLiviano;
	}

	public void setTarifaLiviano(double tarifaLiviano) {
		this.tarifaLiviano = tarifaLiviano;
	}

	public double getTarifaPesado() {
		return tarifaPesado;
	}

	public void setTarifaPesado(double tarifaPesado) {
		this.tarifaPesado = tarifaPesado;
	}

	public Vehiculo registrarVehiculo(String placa, String tipo, Conductor propietario, String idTag) {

		if (ValidadorUtil.esTipoValido(tipo)) {

			Vehiculo vehiculo = new Vehiculo(placa);
			vehiculo.setTipo(tipo);
			vehiculo.setPropietario(propietario);

			TagElectronico tag = new TagElectronico(idTag);
			vehiculo.setTag(tag);

			return vehiculo;

		} else {
			return null;
		}
	}

	public boolean recargarTag(double monto, TagElectronico tag) {

		if (ValidadorUtil.esMontoValido(monto)) {
			tag.setSaldo(tag.getSaldo() + monto);
			return true;
		} else {
			return false;
		}
	}

	public boolean cobrarPeaje(Vehiculo vehiculo) {

		double tarifa;

		if (vehiculo.getTipo().equals("L")) {
			tarifa = tarifaLiviano;
		} else {
			tarifa = tarifaPesado;
		}

		if (vehiculo.getTag().getSaldo() >= tarifa) {
			vehiculo.getTag().setSaldo(vehiculo.getTag().getSaldo() - tarifa);
			return true;
		} else {
			return false;
		}
	}

	public boolean transferirSaldoTag(double monto, TagElectronico origen, TagElectronico destino) {

		if (ValidadorUtil.esMontoValido(monto) && origen.getSaldo() >= monto) {

			origen.setSaldo(origen.getSaldo() - monto);
			destino.setSaldo(destino.getSaldo() + monto);

			return true;

		} else {
			return false;
		}
	}

}
