package com.actdigital.votacao.utils;

public class Validacoes {

	public static void ValidateIfNotEmptyOrNull(String value, String message) throws Exception {
		if (value == null || value.isEmpty())
			throw new Exception(message);
	}
	
}
