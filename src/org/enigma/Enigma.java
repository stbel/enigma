package org.enigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enigma {
	
	private List<String> alphabet;

	private List<Rotor> rotors = new ArrayList<>();
	
	private Reflector reflector;

	private int position;
	
	public Enigma(String[] alphabet) {
		this.alphabet = Arrays.asList(alphabet);
	}

	public Enigma setReflector(Integer[] reflector) {
		this.reflector = new Reflector(reflector);
		return this;
	}

	public Enigma addRotor(Integer[] forward, Integer[] backward) {
		
		this.rotors.add(new Rotor(forward, backward));
		return this;
	}
	
	public Enigma genReflector() {
		
		Integer[] reflector = Util.ordered(alphabet.size());
		Util.mirrorRnd(reflector);
		
		this.setReflector(reflector);
		
		return this;
	}

	public Enigma genRotor() {
		
		Integer[] forward = Util.genForwardRotor(alphabet.size());
		Integer[] backward = Util.genBackwardRotor(forward);
		
		this.addRotor(forward, backward);
		
		return this;
	}
			
	public void reset() {
		this.position = 0;
	}

	public String encode(String message) {
		
		String[] letters = message.split("");
		Integer[] indexes = new Integer[letters.length];

		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = alphabet.indexOf(letters[i]);
		}

		indexes = encode(indexes);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < indexes.length; i++) {
			sb.append(alphabet.get(indexes[i]));
		}

		return sb.toString();
	}
	
	private Integer[] encode(Integer[] indexes) {

		for (int i = 0; i < indexes.length; i++) {
			
			this.position++;
			
			int index = indexes[i];
			for (int j = 0; j < rotors.size(); j++) {
				int offset = Util.rotorPosition(this.position, alphabet.size(), 0);
				index = rotors.get(j).forward(index, offset);
			}
			index = reflector.reflect(index);
			for (int j = rotors.size() - 1; j >= 0; j--) {
				int offset = Util.rotorPosition(this.position, alphabet.size(), 0);
				index = rotors.get(j).backward(index, offset);
			}
			indexes[i] = index;
		}

		return indexes;
	}

	/**
	 * The rotor's job is to give the next index when the previous is provided. It holds it's forward and backward "faces", hence
	 * two methods with the same mechanics.
	 * */
	class Rotor {

		private final Integer[] forward;
		private final Integer[] backward;

		public Rotor(Integer[] forward, Integer[] backward) {
			this.forward = forward;
			this.backward = backward;
		}

		private int normalize(int index, int length) {
			return (index + length) % length;
		}

		public int forward(int index, int offset) {
			return normalize(forward[normalize(index + offset, forward.length)] - offset, forward.length);
		}

		public int backward(int index, int offset) {
			return normalize(backward[normalize(index + offset, backward.length)] - offset, backward.length);
		}
	}
	/**
	 * Similar to the rotors, the reflector's job is to provide the twin index for the index provided. But it is a mirror, and by that feature the original 
	 * gives the image and the image gives the original. Also, it does not rotates, so a modular normalization method to maintain the index inside the bounds is not
	 * necessary. 
	 * */
	class Reflector {

		private final Integer[] reflector;

		public Reflector(Integer[] reflector) {
			this.reflector = reflector;
		}

		public int reflect(int index) {
			return reflector[index];
		}
	}
}
