package org.enigma;

import java.io.FileNotFoundException;

public class Main {
	
	// Unlike the real Enigma, we can define any characters for our alphabet.
	private static final String[] alphabet = { " ", "!", "(", ")", ",", "-", ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "?", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z" };

//	private static Integer[] forward1 = { 39, 7, 55, 35, 51, 22, 4, 28, 40, 36, 32, 17, 6, 59, 12, 18, 48, 19, 1, 41, 26, 34, 27, 15, 56, 54, 2, 45, 64, 30, 11, 58, 53, 67, 44, 9, 16, 25,
//			14, 29, 49, 63, 68, 65, 10, 47, 42, 57, 5, 3, 52, 23, 24, 61, 33, 62, 69, 31, 8, 66, 21, 46, 13, 20, 38, 60, 0, 37, 43, 50 };
//	private static Integer[] backward1 = { 66, 18, 26, 49, 6, 48, 12, 1, 58, 35, 44, 30, 14, 62, 38, 23, 36, 11, 15, 17, 63, 60, 5, 51, 52, 37, 20, 22, 7, 39, 29, 57, 10, 54, 21, 3, 9, 67,
//			64, 0, 8, 19, 46, 68, 34, 27, 61, 45, 16, 40, 69, 4, 50, 32, 25, 2, 24, 47, 31, 13, 65, 53, 55, 41, 28, 43, 59, 33, 42, 56 };
//	private static Integer[] reflector = { 38, 5, 9, 66, 65, 1, 30, 54, 58, 2, 34, 29, 51, 16, 23, 21, 13, 25, 19, 18, 50, 15, 53, 14, 43, 17, 48, 33, 63, 11, 6, 40, 67, 27, 10, 41, 44,
//			42, 0, 52, 31, 35, 37, 24, 36, 64, 57, 60, 26, 68, 20, 12, 39, 22, 7, 62, 69, 46, 8, 61, 47, 59, 55, 28, 45, 4, 3, 32, 49, 56 };

	public static void main(String[] args) throws FileNotFoundException {
		
		Enigma enigma = new Enigma(alphabet).genReflector()
				.genRotor().genRotor().genRotor();

		String message = "This project is just a study. Put your message manually here or chage it to accept the message from args. I "
				+ "was testing ways to use. Currently the rotors are generated, used, and not saved. Its running the encode, printing, "
				+ "running the decode and printing the clear text back. The front panel also is not implemented. ";
		
		System.out.println(String.format(" [INPUT]: %s", message));
		
		message = enigma.encode(message);
		
		System.out.println(String.format("[ENCODE]: %s", message));
		
		enigma.reset();
		message = enigma.encode(message);
		
		System.out.println(String.format("[DECODE]: %s", message));
				
//		Integer[] rotor1forward = Util.genForwardRotor(alphabet.length);
//		Integer[] rotor2forward = Util.genForwardRotor(alphabet.length);
//		Integer[] rotor3forward = Util.genForwardRotor(alphabet.length);
//		
//		System.out.println(String.format("const forwardRotor1 = %s", Arrays.toString(rotor1forward)));
//		System.out.println(String.format("const backwardRotor1 = %s", Arrays.toString(Util.genBackwardRotor(rotor1forward))));
//		System.out.println(String.format("const forwardRotor2 = %s", Arrays.toString(rotor2forward)));
//		System.out.println(String.format("const backwardRotor2 = %s", Arrays.toString(Util.genBackwardRotor(rotor2forward))));
//		System.out.println(String.format("const forwardRotor3 = %s", Arrays.toString(rotor3forward)));
//		System.out.println(String.format("const backwardRotor3 = %s", Arrays.toString(Util.genBackwardRotor(rotor3forward))));
//		
//		Integer[] ordered = Util.ordered(alphabet.length);
//		Util.mirrorRnd(ordered);
//		System.out.println(String.format("const reflector = %s",Arrays.toString(ordered)));
//		
//		System.out.println(Util.rotorPosition(1, 5, 8));
	}
}
