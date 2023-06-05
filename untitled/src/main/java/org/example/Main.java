package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Validator validator = new Validator();
        while(validator!=null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите фильтр или для выхода !quit");

            String filter = scanner.nextLine();
            if(filter.equals(""))
            {

                System.out.println("Введите название аэропорта");
                String nameFilter = scanner.nextLine();
                nameFilter =nameFilter.toLowerCase();
                validator.addFilter(nameFilter);

            }
            else {

                if (filter.equals("!quit"))
                {
                    return;
                }
            filter = filter.toLowerCase();
            filter = filter.replace("\'", "");
            validator.validate(filter);
            System.out.println("Введите название аэропорта");
            String nameFilter = scanner.nextLine();
            nameFilter = nameFilter.toLowerCase();
            validator.addFilter(nameFilter);

            }



            BufferedReader reader = new BufferedReader(new FileReader(
                    "F:\\CSV\\untitled\\airports.csv"));
            String string = null;
            int i = 0;
            Long start = System.currentTimeMillis();
            while ((string = reader.readLine()) != null) {
                Line line = new Line();
                Line buffer = null;
                String oneline = string.replace("\"", "");
                oneline = oneline.replace("\'", "");
                oneline =  oneline.toLowerCase();
                List<String> lineArray = List.of(oneline.split("[,](?=[^\\s])"));


                line.setId(Integer.parseInt(lineArray.get(0)));

                if (lineArray.get(1).equals("\\n"))
                    line.setName(null);
                else
                    line.setName(lineArray.get(1));

                if (lineArray.get(2).equals("\\n"))
                    line.setTown(null);
                else
                    line.setTown(lineArray.get(2));

                if (lineArray.get(3).equals("\\n"))
                    line.setContinent(null);
                else
                    line.setContinent(lineArray.get(3));


                if (lineArray.get(4).equals("\\n"))
                    line.setTowncode(null);
                else
                    line.setTowncode(lineArray.get(4));


                if (lineArray.get(5).equals("\\n"))
                    line.setContinetCode(null);
                else
                    line.setContinetCode(lineArray.get(5));


                if (lineArray.get(6).equals("\\n"))
                    line.setLatitude(null);
                else
                    line.setLatitude(Double.valueOf(lineArray.get(6)));

                if (lineArray.get(7).equals("\\n"))
                    line.setLongitude(null);
                else
                    line.setLongitude(Double.valueOf(lineArray.get(7)));
                if (lineArray.get(8).equals("\\n"))
                    line.setCode(null);
                else
                    line.setCode(Integer.parseInt(lineArray.get(8)));
                if (lineArray.get(9).equals("\\n"))
                    line.setTemperature(null);
                else
                    line.setTemperature(Double.valueOf(lineArray.get(9)));
                if (lineArray.get(10).equals("\\n"))
                    line.setRegionCode(null);
                else
                    line.setRegionCode(lineArray.get(10));
                if (lineArray.get(11).equals("\\n"))
                    line.setAdress(null);
                else
                    line.setAdress(lineArray.get(11));
                if (lineArray.get(12).equals("\\n"))
                    line.setComplexType(null);
                else
                    line.setComplexType(lineArray.get(12));
                if (lineArray.get(13).equals("\\n"))
                    line.setOwned(null);
                else
                    line.setOwned(lineArray.get(13));
                buffer = validator.printLine(line);
                if (buffer!= null)
                {
                    i++;
                }
            }
            System.out.println("Прошло времени, мс: " + (System.currentTimeMillis() - start));
            System.out.println("Кол-во записей = " + i);
            validator.clearList();
            reader.close();
        }
    }
}