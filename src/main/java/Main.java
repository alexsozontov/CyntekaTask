import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {

    System.out.println("Введите абсолютный путь до входного файла: ");

    Scanner scan = new Scanner(System.in);
    String path = scan.nextLine();
    scan.close();

    ArrayList<String> lines = new ArrayList<>(
        Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
    while (lines.remove(""));

    List<String> firstGroup = lines.subList(1, Integer.parseInt(lines.get(0)) + 1);
    List<String> secondGroup = lines.subList(Integer.parseInt(lines.get(0)) + 2, lines.size());

    System.out.println("Выходной файл \"output.txt\" лежит здесь:\n" + writeInFile("output.txt",
        matchingGroup(firstGroup, secondGroup)));
  }

  public static String writeInFile(String nameFile, List<String> nameGroup) throws IOException {
    File file = new File(nameFile);
    FileWriter fr = new FileWriter(file);
    for (String str : nameGroup) {
      fr.write(str + "\n");
    }
    fr.flush();
    fr.close();
    return file.getAbsolutePath();
  }

  public static List<String> matchingGroup(List<String> firstGroup, List<String> secondGroup) {

    ArrayList<String> output = new ArrayList<>();
    if (firstGroup.size() >= secondGroup.size()) {
      output.addAll(0, firstGroup);
      for (String value : secondGroup) {
        for (int j = 0; j < firstGroup.size(); j++) {
          List<String> secondSplit = List.of(value.split(" "));
          for (String s : secondSplit) {
            if (firstGroup.get(j).contains(s)) {
              firstGroup.set(j, firstGroup.get(j) + " : " + value);
              break;
            }
          }
        }
      }
      for (String s : output) {
        for (int j = 0; j < firstGroup.size(); j++) {
          if (s.contains(firstGroup.get(j))) {
            firstGroup.set(j, s + " : ?");
          }
        }
      }
      return firstGroup;
    } else {
      output.addAll(0, secondGroup);
      for (int i = 0; i < secondGroup.size(); i++) {
        for (int j = 0; j < firstGroup.size(); j++) {
          List<String> firstSplit = List.of(secondGroup.get(i).split(" "));
          for (String s : firstSplit) {
            if (secondGroup.get(j).contains(s)) {
              secondGroup.set(i, firstGroup.get(j) + " : " + secondGroup.get(i));
              break;
            }
          }
        }
      }
      for (String s : output) {
        for (int j = 0; j < secondGroup.size(); j++) {
          if (s.contains(secondGroup.get(j))) {
            secondGroup.set(j, s + " : ?");
          }
        }
      }
    }
    return secondGroup;
  }
}


