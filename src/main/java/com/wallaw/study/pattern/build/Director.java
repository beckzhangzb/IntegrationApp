package com.wallaw.study.pattern.build;

public class Director {
	private Builder builder; 

	public Director(Builder builder){
		this.builder = builder;
	}
	/***将部件partA partB partC组装到一起***/
	public void construct() { 
	builder.buildPartA();
    builder.buildPartB();
    builder.buildPartC();
	}
}
