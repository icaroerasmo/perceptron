package com.icaroerasmo.data;

import java.util.List;
import java.util.stream.Collectors;

public class Perceptron {

	private int indexInstanciaTeste = 0;
	
	private Double eta;
	private Double threshold;
	private Double bias;
	private List<Double> pesos;
	private Integer epochs;
	private double[] valoresF;
	
	public Perceptron(Double eta, Double threshold, Double bias, Integer epochs, List<Double> pesos) {
		this.eta = eta;
		this.threshold = threshold;
		this.bias = bias;
		this.epochs = epochs;
		this.pesos = pesos;
	}
	
	public void treina(List<Tupla> valoresTeste, List<Double> labels, String rotulo) {
		
		int counter = 1;
		
		atualizaFU(labels);
		
		Double valorPredito = prediz(valoresTeste.get(indexInstanciaTeste));
		Double valorAlvo = valoresTeste.get(indexInstanciaTeste).getAsDouble(rotulo);
		
		while(counter < epochs || !valorPredito.equals(valorAlvo)) {
			
			atualizaBias(eta, valorPredito, valorAlvo);
			atualizaPesos(valorPredito, valoresTeste, rotulo);
			atualizaIndice(valoresTeste);
			
			valorPredito = prediz(valoresTeste.get(indexInstanciaTeste));
			valorAlvo = valoresTeste.get(indexInstanciaTeste).getAsDouble(rotulo);
			
			counter++;
		}
		
		indexInstanciaTeste = 0;
	}
	
	private Double calculaU(Tupla valorTeste) {
		Double u = bias;
		for(int i = 0; i < pesos.size(); i++) {
			Double valor =  (Double) valorTeste.get(""+i).get();
			u += valor * pesos.get(i) + threshold;
		}
		return u;
	}
	
	public Double prediz(Tupla valorTeste) {
		Double u = calculaU(valorTeste);
		return u < threshold ? valoresF[0] : valoresF[1];
	}
	
	public void atualizaPesos(Double valorPredito, List<Tupla> valoresTeste, String rotulo ) {
		pesos = pesos.stream().
				map(peso -> atualizaPeso(
						peso, eta, valorPredito,
						(Double) valoresTeste.get(indexInstanciaTeste).get(rotulo).get(),
						(Double) valoresTeste.get(indexInstanciaTeste).getByIndex(pesos.indexOf(peso)))).
				collect(Collectors.toList());
	}
	
	public Double atualizaPeso(Double peso, Double eta, Double y1, Double y, Double xi) {
		return peso + eta * (y - y1) * xi;
	}
	
	public void atualizaBias(Double eta, Double y1, Double y) {
		bias = bias + eta * (y - y1);
	}
	
	private void atualizaIndice(List<Tupla> valoresTeste) {
		if(indexInstanciaTeste < valoresTeste.size() - 1) {
			indexInstanciaTeste++;
		} else {
			indexInstanciaTeste = 0;
		}
	}
	
	private void atualizaFU(List<Double> saidas) {
		List<Double> uniqueVals = saidas.stream().distinct().collect(Collectors.toList());
		valoresF = new double[] {uniqueVals.get(0), uniqueVals.get(uniqueVals.size()-1)};
	}
}
