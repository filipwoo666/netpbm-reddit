import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Draw {

	public static void main(String[] args) {

		File output = new File("image.ppm");
		File input = new File("input.txt");

		String currentLine = "";
		StringBuilder inputAsString = new StringBuilder();

		Drawer image = new Drawer();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(output));
				BufferedReader br = new BufferedReader(new FileReader(input))) {

			while ((currentLine = br.readLine()) != null) {
				inputAsString.append(currentLine + "\n");
			}

			bw.write(image.draw(inputAsString.toString()));

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file: " + input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
