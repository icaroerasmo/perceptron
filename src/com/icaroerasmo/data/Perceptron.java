package com.icaroerasmo.data;

import java.util.List;
import java.util.stream.Collectors;

public class Perceptron {

	private Double eta;
	private Double threshold;
	private Double bias;
	private List<Double> pesos;
	private int indexInstanciaTeste = 0;
	private double[] valoresF = {0D, 1D};
	
	public Perceptron(Double eta, Double threshold, Double bias, List<Double> pesos) {
		this.eta = eta;
		this.threshold = threshold;
		this.bias = bias;
		this.pesos = pesos;
	}
	
	public void treina(List<String> valoresTeste, List<Double> saidas) {
		
		atualizaFU(saidas);
		
		var valorPredito = prediz(valoresTeste.get(indexInstanciaTeste));
		var valorAlvo = saidas.get(indexInstanciaTeste);
		
		while(!valorPredito.equals(valorAlvo)) {
			
			atualizaBias(eta, valorPredito, valorAlvo);
			atualizaPesos(valorPredito, valoresTeste, saidas);
			atualizaIndice(valoresTeste);
			
			valorPredito = prediz(valoresTeste.get(indexInstanciaTeste));
			valorAlvo = saidas.get(indexInstanciaTeste);
		}
	}
	
	private Double calculaU(String valorTeste) {
		var u = bias;
		for(int i = 0; i < pesos.size(); i++) {
			var valor =  converteValorParaDouble(valorTeste, i);
			u += valor * pesos.get(i) + threshold;
		}
		return u;
	}
	
	public Double prediz(String valorTeste) {
		var u = calculaU(valorTeste);
		return u < threshold ? valoresF[0] : valoresF[1];
	}
	
	public Double atualizaBias(Double eta, Double y1, Double y) {
		return bias = bias + eta * (y - y1);
	}
	
	public void atualizaPesos(Double valorPredito, List<String> valoresTeste, List<Double> saidas) {
		final int[] counter = {0};
		pesos = pesos.stream().
				map(peso -> atualizaPeso(
						peso, eta, valorPredito,
						saidas.get(indexInstanciaTeste),
						converteValorParaDouble(valoresTeste.get(indexInstanciaTeste), counter[0]++))).
				collect(Collectors.toList());
	}
	
	public Double atualizaPeso(Double peso, Double eta, Double y1, Double y, Double xi) {
		return peso + eta * (y - y1) * xi;
	}
	
	private void atualizaIndice(List<String> valoresTeste) {
		if(indexInstanciaTeste < valoresTeste.size() - 1) {
			indexInstanciaTeste++;
		} else {
			indexInstanciaTeste = 0;
		}
	}
	
	// Tenho que descobrir como atualizar
	// os valores de FU
	private void atualizaFU(List<Double> saidas) {
		List<Double> uniqueVals = saidas.stream().distinct().collect(Collectors.toList());
		valoresF = new double[] {uniqueVals.get(0), uniqueVals.get(uniqueVals.size()-1)};
	}
	
	private Double converteValorParaDouble(String valorTeste, int index) {
		return Double.parseDouble("" + valorTeste.charAt(index));
	}
}
