import java.util.Arrays;

public class Drawer {

	public String[][] image;

	public String draw(String input) {

		String[] lines = input.split("\n");

		String[] size = lines[0].split(" ");

		image = new String[Integer.parseInt(size[1])][Integer.parseInt(size[0])];

		for (String[] array : image) {
			Arrays.fill(array, "0 0 0");
		}

		for (String line : lines) {

			String[] command = line.split(" ");

			switch (command[0]) {
			case "point":
				point(command);
				break;
			case "line":
				line(command);
				break;
			case "rect":
				rect(command);
				break;
			default:
				break;
			}
		}

		StringBuilder result = new StringBuilder("P3 " + size[0] + " " + size[1] + " 255");

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				result.append(" " + image[i][j]);
			}
		}

		return result.toString();
	}

	private void point(String[] pointCmd) {
		String color = getColor(pointCmd);
		int row = Integer.parseInt(pointCmd[4]);
		int col = Integer.parseInt(pointCmd[5]);

		image[row][col] = color;
	}

	private void line(String[] lineCmd) {
		String color = getColor(lineCmd);
		int startRow = Integer.parseInt(lineCmd[4]);
		int startCol = Integer.parseInt(lineCmd[5]);
		int endRow = Integer.parseInt(lineCmd[6]);
		int endCol = Integer.parseInt(lineCmd[7]);

		int rowSign = endRow - startRow > 0 ? 1 : -1;
		int colSign = endCol - startCol > 0 ? 1 : -1;

		if (Math.abs(endRow - startRow) >= Math.abs(endCol - startCol)) {
			double slope = Math.abs((double) (endCol - startCol) / (endRow - startRow));

			for (int i = 0; i < Math.abs(endRow - startRow); i++) {
				image[startRow + i * rowSign][startCol + (int) (i * slope * colSign)] = color;
			}
		} else {
			double slope = (double) (endRow - startRow) / (endCol - startCol);

			for (int i = 0; i < Math.abs(endCol - startCol); i++) {
				image[startRow + (int) (i * slope * rowSign)][startCol + i * colSign] = color;
			}
		}
	}

	private void rect(String[] rectCmd) {
		String color = getColor(rectCmd);
		int startRow = Integer.parseInt(rectCmd[4]);
		int startCol = Integer.parseInt(rectCmd[5]);
		int rows = Integer.parseInt(rectCmd[6]);
		int cols = Integer.parseInt(rectCmd[7]);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				image[i + startRow][j + startCol] = color;
			}
		}
	}

	private String getColor(String[] cmd) {
		return cmd[1] + " " + cmd[2] + " " + cmd[3];
	}
}
