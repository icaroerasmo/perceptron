package com.icaroerasmo;

import java.util.Arrays;

import com.icaroerasmo.data.Perceptron;

public class Main {

	public static void main(String[] args) {
	
		Perceptron perceptron = new Perceptron(0.4, 0D, 0.5, Arrays.asList("001", "110"), Arrays.asList(-1D, 1D), Arrays.asList(0.4, -0.6, 0.6));
		perceptron.treina();
		System.out.println(perceptron.predict("111"));
		System.out.println(perceptron.predict("000"));
		System.out.println(perceptron.predict("100"));
		System.out.println(perceptron.predict("011"));
	}
}
