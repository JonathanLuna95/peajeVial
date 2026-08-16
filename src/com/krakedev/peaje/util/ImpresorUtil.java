package com.krakedev.peaje.util;

import com.krakedev.peaje.entidades.Vehiculo;

public class ImpresorUtil {
	
	public static void imprimirVehiculo(Vehiculo vehiculo) {

		System.out.println("Placa: " + vehiculo.getPlaca());
		System.out.println("Tipo: " + vehiculo.getTipo());
		System.out.println("Cédula propietario: " + vehiculo.getPropietario().getCedula());
		System.out.println("Nombre propietario: " + vehiculo.getPropietario().getNombre());
		System.out.println("Apellido propietario: " + vehiculo.getPropietario().getApellido());
		System.out.println("ID Tag: " + vehiculo.getTag().getIdTag());
		System.out.println("Saldo disponible: " + vehiculo.getTag().getSaldo());
	}

}
