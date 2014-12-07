import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	public static String s = "";

	public static void main(String[] args) {

		s = JOptionPane.showInputDialog("please enter ur data :");

		List<Integer> data = LZW.compresstion(s);
		JOptionPane.showMessageDialog(null, "the compress data is: "+data,"the data compressed"
				,JOptionPane.PLAIN_MESSAGE);

		String decompress = LZW.decompresstion(data);
		JOptionPane.showMessageDialog(null,"Decompression is: "+decompress,"the data Decompressed"
				,JOptionPane.PLAIN_MESSAGE);

		try {
			BufferedWriter in = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("keko.txt"))));
			in.write(data.toString());
			in.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
