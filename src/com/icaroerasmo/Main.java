package com.icaroerasmo;

import java.util.Arrays;

import com.icaroerasmo.data.Perceptron;

public class Main {

	public static void main(String[] args) {
	
		Perceptron perceptron = new Perceptron(0.4, 0D, 0.5, Arrays.asList("001", "110"), -1D, Arrays.asList(0.4, -0.6, 0.6));
		perceptron.treina();
	}
}
