package examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteCSV {

  public static void main(String[] args) throws IOException {
    // Check if args[0] is given
    if (args.length <= 0) {
      System.out.println("Please provide a filename.");
      System.exit(1);
    }
    // Open the file
    FileReader fr = new FileReader(args[0]);
    BufferedReader br = new BufferedReader(fr);

    // Read the file and manipulate
    String line; // create local variable
    String[] columns;
    List<String[]> newExchangeRate = new ArrayList<>(); // Temporary variable to store our new file, will process in FileWriter.

    // Read column headers and update to our ArrayList
    columns = br.readLine().split(",");
    newExchangeRate.add(columns);

    while ((line = br.readLine()) != null) { // loop to end of file
      // Check if line is empty
      if (line.length() <= 0) {
        continue; // move to next iteration if empty
      }
      // Split COMMA seperated values file by comma
      columns = line.split(",");
      // Lets change our exchange rate to become stronger against USD..
      // Check for SGD and change the rate
      if (columns[0].equals("SGD")) {
        columns[1] = " 1.1";
      }
      // update our newExchangeRate variable
      newExchangeRate.add(columns);
    }

    try {
      FileWriter myWriter = new FileWriter("newExchangeRate.csv");
      // for each column in each row, append to writer
      for (String[] rows : newExchangeRate) {
        for (int i = 0; i < rows.length; i++) {
          myWriter.append(rows[i]);
          // add comma until second last word
          if (i < (rows.length - 1)) myWriter.append(",");
        }
        // append new line
        myWriter.append(System.lineSeparator());
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
