package com.icaroerasmo;

import java.util.Arrays;
import java.util.List;

import com.icaroerasmo.data.Perceptron;

public class Main {

	public static void main(String[] args) {
		
		Double eta = 0.4;
		Double threshold = 0D;
		Double bias = 0.5;
		List<String> instancias = Arrays.asList("001", "110");
		List<Double> labels = Arrays.asList(-1D, 1D);
		List<Double> pesos = Arrays.asList(0.4, -0.6, 0.6);
	
		Perceptron perceptron = new Perceptron(eta, threshold, bias, instancias, labels, pesos);
		perceptron.treina();
		System.out.println(perceptron.prediz("111"));
		System.out.println(perceptron.prediz("000"));
		System.out.println(perceptron.prediz("100"));
		System.out.println(perceptron.prediz("011"));
	}
}
