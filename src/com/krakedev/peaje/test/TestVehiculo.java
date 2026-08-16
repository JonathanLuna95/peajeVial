package com.krakedev.peaje.test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;
import com.krakedev.peaje.util.ImpresorUtil;

public class TestVehiculo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conductor conductor = new Conductor("1723919591", "Jonathan", "Luna");

		Vehiculo vehiculo = new Vehiculo("PDE-0123");

		TagElectronico tag = new TagElectronico("TAG001");

		vehiculo.setPropietario(conductor);
		vehiculo.setTag(tag);

		vehiculo.imprimir();

		System.out.println("----------INFO COMPLETA DEL VEHÍCULO------------");

		ImpresorUtil.imprimirVehiculo(vehiculo);

		EstacionPeaje estacion = new EstacionPeaje();

		Vehiculo vehiculoRegistrado = estacion.registrarVehiculo("GST-2111", "P", conductor, "TAG002");

		System.out.println("----------REGISTRAR VEHÍCULO------------");

		ImpresorUtil.imprimirVehiculo(vehiculoRegistrado);

		System.out.println("----------RECARGAR TAG----------");

		boolean recarga = estacion.recargarTag(25.00, vehiculoRegistrado.getTag());

		System.out.println("Recarga realizada: " + recarga);
		System.out.println("Saldo actual: " + vehiculoRegistrado.getTag().getSaldo());

		System.out.println("----------COBRAR PEAJE----------");

		boolean cobro = estacion.cobrarPeaje(vehiculoRegistrado);

		System.out.println("Cobro realizado: " + cobro);
		System.out.println("Saldo después del peaje: " + vehiculoRegistrado.getTag().getSaldo());

		System.out.println("----------TRANSFERIR SALDO----------");

		estacion.recargarTag(10.00, vehiculo.getTag());

		boolean transferencia = estacion.transferirSaldoTag(5.00, vehiculo.getTag(), vehiculoRegistrado.getTag());

		System.out.println("Transferencia realizada: " + transferencia);
		System.out.println("Saldo TAG001: " + vehiculo.getTag().getSaldo());
		System.out.println("Saldo TAG002: " + vehiculoRegistrado.getTag().getSaldo());

	}

}
