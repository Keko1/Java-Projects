import java.util.*;

public class LZW {
	/*
	 * compress function takes text of Char returns list of intgers refers to
	 * each char.
	 */
	public static List<Integer> compresstion(String text) {
		// dictionary
		int DicSize = 128;
		HashMap<String, Integer> Dictionary = new HashMap<String, Integer>();
		for (int i = 0; i < 128; i++)
			Dictionary.put("" + (char) i, i);

		String word = "";

		List<Integer> result = new ArrayList<Integer>();
		for (char myInput : text.toCharArray()) {
			String T = word + myInput;
			if (Dictionary.containsKey(T))
				word = T;
			else {
				result.add(Dictionary.get(word));
				// Add temp to the dictionary.
				Dictionary.put(T, DicSize++);
				word = "" + myInput;
			}
		}
		if (!word.equals(""))
			result.add(Dictionary.get(word));
		return result;
	}

	/*
	 * Function decompretion of the Data
	 */
	public static String decompresstion(List<Integer> dataCompressed) {
		// int dicSize = 128;
		// creating the Dictionary
		HashMap<Integer, String> dictionary = new HashMap<Integer, String>();
		for (int i = 0; i < 128; i++)
			dictionary.put(i, "" + (char) i);

		String result = "";
		String temp = "";
		String pastTemp = dictionary.get(dataCompressed.get(0));
		result += pastTemp;
		for (int i = 1; i < dataCompressed.size(); i++) {
			if (dataCompressed.get(i) < dictionary.size()) {

				temp = dictionary.get(dataCompressed.get(i));
				result += temp;
				String sub = pastTemp + temp.charAt(0);
				pastTemp = temp;
				if (!dictionary.containsValue(sub))
					dictionary.put(dictionary.size(), sub);
			} else if (dataCompressed.get(i) > dictionary.size()) {
				temp = pastTemp + pastTemp.charAt(0);
				result += temp;
				pastTemp = temp;
				//if (!dictionary.containsValue(temp))
				dictionary.put(dictionary.size(), temp);
			}
		}
		return result;
	}
}
