package com.icaroerasmo;

import java.util.Arrays;
import java.util.List;

import com.icaroerasmo.data.Perceptron;
import com.icaroerasmo.data.Tupla;
import com.icaroerasmo.data.TuplaBuilder;

public class Main {

	public static void main(String[] args) {
		
		Double eta = 0.4;
		Double threshold = 0D;
		Double bias = 0.5;
		Integer epochs = 0;
		
		TuplaBuilder builder = new TuplaBuilder();
		
		List<Tupla> instancias = Arrays.asList(
				builder.add("0", 0).add("1", 0).add("2", 1).add("3", -1).build(),
				builder.add("0", 1).add("1", 1).add("2", 0).add("3", 1).build());
		
		List<Double> labels = Arrays.asList(-1D, 1D);
		List<Double> pesos = Arrays.asList(0.4, -0.6, 0.6);
	
		Perceptron perceptron = new Perceptron(eta, threshold, bias, epochs, pesos);
		perceptron.treina(instancias, labels, "3");
		
		
		List<Tupla> testes = Arrays.asList(
				builder.add("0", 1).add("1", 1).add("2", 1).build(),
				builder.add("0", 1).add("1", 0).add("2", 0).build(),
				builder.add("0", 0).add("1", 0).add("2", 0).build(),
				builder.add("0", 0).add("1", 1).add("2", 1).build());
		
		for(Tupla teste : testes) {
			System.out.println(perceptron.prediz(teste));
		}
	}
}
