package com.icaroerasmo.data;

import java.util.List;
import java.util.stream.Collectors;

public class Perceptron {
	
	private List<Double> saidas;
	private Double eta;
	private Double threshold;
	private Double bias;
	private List<String> valoresTeste;
	private List<Double> pesos;
	private int indexInstanciaTeste = 0;
	private double[] valoresF;
	
	public Perceptron(Double eta, Double threshold, Double bias, List<String> valoresTeste, List<Double> saidas, List<Double> pesos) {
		this.eta = eta;
		this.threshold = threshold;
		this.bias = bias;
		this.valoresTeste = valoresTeste;
		this.saidas = saidas;
		this.pesos = pesos;
	}
	
	public void treina() {
		
		atualizaFU();
		
		var valorPredito = calculaFu();
		var valorAlvo = saidas.get(indexInstanciaTeste);
		
		while(!valorPredito.equals(valorAlvo)) {
			
			atualizaBias(eta, valorPredito, valorAlvo);
			atualizaPesos(valorPredito);
			
			atualizaIndice();
			
			valorPredito = calculaFu();
			valorAlvo = saidas.get(indexInstanciaTeste);
		}
	}
	
	public Double atualizaBias(Double eta, Double y1, Double y) {
		return bias = bias + eta * (y - y1);
	}
	
	public void atualizaPesos(Double valorPredito) {
		pesos = pesos.stream().
				map(peso -> atualizaPeso(peso, eta, valorPredito, saidas.get(indexInstanciaTeste), convertePesoParaValor(peso))).
				collect(Collectors.toList());
	}
	
	public Double atualizaPeso(Double peso, Double eta, Double y1, Double y, Double xi) {
		return peso + eta * (y - y1) * xi;
	}
	
	public Double prediz(String valorTeste) {
		var u = bias;
		for(var peso : pesos) {
			var indexOf = pesos.indexOf(peso);
			var valor = Double.parseDouble(""+valorTeste.charAt(indexOf));
			u += valor * peso + threshold;
		}
		return u < threshold ? valoresF[0] : valoresF[1];
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
	
	private void atualizaIndice() {
		if(indexInstanciaTeste < valoresTeste.size() - 1) {
			indexInstanciaTeste++;
		} else {
			indexInstanciaTeste = 0;
		}
	}
	
	// Tenho que descobrir como atualizar
	// os valores de FU
	private void atualizaFU() {
		List<Double> uniqueVals = saidas.stream().distinct().collect(Collectors.toList());
		valoresF = new double[] {uniqueVals.get(0), uniqueVals.get(uniqueVals.size()-1)};
	}
	
	private Double calculaFu() {
		var u = calculaU();
		return u < threshold ? valoresF[0] : valoresF[1];
	}
}
