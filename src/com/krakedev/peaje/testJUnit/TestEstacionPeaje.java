package com.krakedev.peaje.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;

public class TestEstacionPeaje {

	@Test
	public void testRegistrarVehiculo() {

		EstacionPeaje estacion = new EstacionPeaje();

		Conductor conductor = new Conductor("1723919591", "Jonathan", "Luna");

		Vehiculo vehiculo = estacion.registrarVehiculo("PDE-0123", "L", conductor, "TAG001");

		assertEquals("PDE-0123", vehiculo.getPlaca());
		assertEquals("L", vehiculo.getTipo());
		assertEquals("1723919591", vehiculo.getPropietario().getCedula());
		assertEquals("TAG001", vehiculo.getTag().getIdTag());
	}

	@Test
	public void testRecargarTag() {

		EstacionPeaje estacion = new EstacionPeaje();
		TagElectronico tag = new TagElectronico("TAG002");

		boolean resultado = estacion.recargarTag(25.00, tag);

		assertEquals(true, resultado);
		assertEquals(25.00, tag.getSaldo(), 0.0001);
	}

	@Test
	public void testCobrarPeaje() {

		EstacionPeaje estacion = new EstacionPeaje();

		Conductor conductor = new Conductor("1723919591", "Jonathan", "Luna");

		Vehiculo vehiculo = estacion.registrarVehiculo("GST-2111", "P", conductor, "TAG003");

		estacion.recargarTag(20.00, vehiculo.getTag());

		boolean resultado = estacion.cobrarPeaje(vehiculo);

		assertEquals(true, resultado);
		assertEquals(17.50, vehiculo.getTag().getSaldo(), 0.0001);
	}

	@Test
	public void testTransferirSaldoTag() {

		EstacionPeaje estacion = new EstacionPeaje();

		TagElectronico origen = new TagElectronico("TAG004");
		TagElectronico destino = new TagElectronico("TAG005");

		estacion.recargarTag(30.00, origen);

		boolean resultado = estacion.transferirSaldoTag(10.00, origen, destino);

		assertEquals(true, resultado);
		assertEquals(20.00, origen.getSaldo(), 0.0001);
		assertEquals(10.00, destino.getSaldo(), 0.0001);
	}

}
