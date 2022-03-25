package com.devcoi.cursomc.utils;

import java.io.File;

public class FileUtils {

	public static String getExtensao(File file) {
		String nomeDoArquivo = file.getName();
		if (nomeDoArquivo.contains("."))
			return nomeDoArquivo.substring(nomeDoArquivo.lastIndexOf(".") + 1);
		else
			return "";
	}
	
	public static String getNomeSemExtensao (File file) {
		String nomeDoArquivo = file.getName();
		if (nomeDoArquivo.contains("."))
			return nomeDoArquivo.substring(0, nomeDoArquivo.lastIndexOf("."));
		else
			return "";
	}

}
