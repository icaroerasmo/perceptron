package com.icaroerasmo.data;

import java.util.List;
import java.util.stream.Collectors;

public class Perceptron {
	
	private Double y;
	private Double eta;
	private Double threshold;
	private Double bias;
	private List<String> valoresTeste;
	private List<Double> pesos;
	private int indexInstanciaTeste = 0;
	
	public Perceptron(Double eta, Double threshold, Double bias, List<String> valoresTeste, Double y, List<Double> pesos) {
		this.eta = eta;
		this.threshold = threshold;
		this.bias = bias;
		this.valoresTeste = valoresTeste;
		this.y = y;
		this.pesos = pesos;
	}
	
	public void treina() {
		var valorPredito = calculaFu();
		while(!valorPredito.equals(y)) {
			atualizaBias(eta, valorPredito, y);
			atualizaPesos(valorPredito);
			indexInstanciaTeste++;
			valorPredito = calculaFu();
			System.out.println(valorPredito);
		}
	}
	
	public Double atualizaBias(Double eta, Double y1, Double y) {
		return bias = bias + eta * (y - y1);
	}
	
	public void atualizaPesos(Double valorPredito) {
		pesos = pesos.stream().
				map(peso -> atualizaPeso(peso, eta, valorPredito, y, convertePesoParaValor(peso))).
				collect(Collectors.toList());
	}
	
	public Double atualizaPeso(Double peso, Double eta, Double y1, Double y, Double xi) {
		return peso + eta * (y - y1) * xi;
	}
	
	private Double calculaU() {
		var u = bias;
		for(var peso : pesos) {
			var valor =  convertePesoParaValor(peso);
			u += valor * peso + threshold;
		}
		return u;
	}
	
	private Double convertePesoParaValor(Double peso) {
		var indexOf = pesos.indexOf(peso);
		return Double.parseDouble("" + valoresTeste.get(indexInstanciaTeste).charAt(indexOf));
	}
	
	// Tenho que descobrir como saber
	// como calcular os valores de saída
	private Double calculaFu() {
		var u = calculaU();
		return u < threshold ? 0D : 1D;
	}
}
