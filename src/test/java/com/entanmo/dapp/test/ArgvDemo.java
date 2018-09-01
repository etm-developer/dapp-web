package com.entanmo.dapp.test;

public class ArgvDemo {

	public static void main(String[] args) {
		for (String argv: args) {
			System.out.print(argv + " ");
		}
		System.out.println();
	}

}
