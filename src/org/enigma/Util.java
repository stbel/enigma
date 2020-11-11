package org.enigma;

import java.util.Arrays;
import java.util.Random;

public class Util {

	public static void genRotorVars(Integer[] forward, Integer[] backward) {

		System.out.println("Integer[] forward = " + Arrays.toString(forward).replace("[", "{").replace("]", "}") + ";");
		System.out.println("Integer[] backward = " + Arrays.toString(backward).replace("[", "{").replace("]", "}") + ";");

	}

	public static void genReflectorVars(Integer[] reflector) {

		System.out.println("Integer[] reflector = " + Arrays.toString(reflector).replace("[", "{").replace("]", "}") + ";");

	}

	public static Integer[] genForwardRotor(int rotorSize) {

		Integer[] rotor = new Integer[rotorSize];

		for (int i = 0; i < rotor.length; i++) {
			rotor[i] = i;
		}

		shuffle(6, rotor);
		
		return rotor;

	}

	private static void shuffle(int cycles, Integer[] rotor) {
		Random random = new Random(System.currentTimeMillis());

		for (int c = 0; c < cycles; c++) {
			try {
				Thread.sleep(37);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < rotor.length; i++) {

				int temp = rotor[i];
				int j = random.nextInt(rotor.length);
				// int j = (int) (Math.random() * rotorSize);
				rotor[i] = rotor[j];
				rotor[j] = temp;
			}
		}
	}

	public static Integer[] genBackwardRotor(Integer[] rotor) {
		Integer[] reverse = new Integer[rotor.length];

		for (int i = 0; i < reverse.length; i++) {
			reverse[rotor[i]] = i;
		}
		return reverse;
	}

	public static Integer[] ordered(int size) {
		Integer[] ordered = new Integer[size];

		for (int i = 0; i < ordered.length; i++) {
			ordered[i] = i;
		}

		return ordered;
	}

	public static void mirrorRnd(Integer[] ints) {
		for (int i = 0; i < ints.length; i++) {
			if (i == ints[i]) {
				int select = (int) (Math.random() * ints.length);
				if (select == ints[select]) {
					ints[i] = select;
					ints[select] = i;
				}
			}
		}

		int unswapped = 0;
		for (int i = 0; i < ints.length; i++) {
			if (i == ints[i])
				unswapped++;
		}

		// The size of the array is always even or odd, so unless the unswapped is 0 or
		// 1
		// the random swap continues.
		if (ints.length % 2 != unswapped)
			mirrorRnd(ints);
	}

	/**
	 * 
	 * @param step  - The current step.
	 * @param base  - The numerical base: The size of the alphabet
	 * @param order - The order of the rotor - The same of the numerical magnitude
	 *              of the base. The FastRotor must have order zero, the MediumRotor
	 *              is order 1 and so on.
	 * @return - The local position the rotor.
	 */
	public static int rotorPosition(int step, int base, int order) {

		return (int) (step / Math.pow(base, order)) % base;
	}
}
