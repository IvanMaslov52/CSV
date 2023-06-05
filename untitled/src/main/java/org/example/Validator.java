package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    private List<Predicate> predicateList =new ArrayList<>();
    public Predicate finalPredicate = null;
    public Predicate nameFilter = null;

    public static int indexOf(String s, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(s);
        if (matcher.find()) {
            return matcher.start();
        } else {
            return -1;
        }
    }



    public void validate(String string)
    {
        if(Validator.indexOf(string,"&|[||]") != -1)
        {
            Predicate predicate = parseTerm(string.substring(0,Validator.indexOf(string,"&|[||]")));
            string =  string.substring(Validator.indexOf(string,"&|[||]"));

            while (string!= null)
            {
                if(string.substring(0,2).equals("||"))
                {
                    predicateList.add(predicate);
                    string = string.substring(2,string.length());
                    if(Validator.indexOf(string,"&|[||]") != -1)
                    {

                       predicate = parseTerm(string.substring(0,Validator.indexOf(string,"&|[||]")));
                        string = string.substring(Validator.indexOf(string,"&|[||]"));
                    }
                    else {

                        predicate = parseTerm(string.substring(0,string.length()));
                        string  = string.substring(0,string.length());
                    }

                }
                else {
                    if(string.substring(0,1).equals("&"))
                    {
                        string = string.substring(1,string.length());
                        if(Validator.indexOf(string,"&|[||]") != -1)
                        {

                            predicate = mergePredicate(predicate, parseTerm(string.substring(0,Validator.indexOf(string,"&|[||]"))));
                            string = string.substring(Validator.indexOf(string,"&|[||]"));
                        }
                        else {

                            predicate = mergePredicate(predicate, parseTerm(string.substring(0,string.length())));
                            string  = string.substring(0,string.length());

                        }
                    }
                    else {

                        finalPredicate = predicate;
                        for(int i = 0;i <predicateList.size();i++)
                        {
                            finalPredicate   = Validator.finalMergePredicate(finalPredicate,predicateList.get(i));
                        }
                        break;
                    }
                }
            }
        }
        else
        {
         predicateList.add(parseTerm(string));
            finalPredicate = predicateList.get(0);
        }
    }


    public Predicate parseTerm(String string)
    {
        List<String> list;
        Predicate predicate = null;
        int a = Validator.indexOf(string,"<|>|[<>]|=");

        if(string.substring(a,a+2).equals("<>")) {
            list = List.of(string.split("<>"));
            switch (list.get(0)){
                case "column[1]":
                    List<String> finalList1 = list;
                    predicate= line -> !Objects.equals(line.getId(),Integer.parseInt(finalList1.get(1)));
                    break;
                case "column[2]":
                    List<String> finalList2 = list;
                    predicate = line -> !Objects.equals(line.getName(),finalList2.get(1));
                    break;
                case "column[3]":
                    List<String> finalList3 = list;
                    predicate = line ->!Objects.equals(line.getTown(),finalList3.get(1));
                    break;
                case "column[4]":
                    List<String> finalList4 = list;
                    predicate = line -> !Objects.equals(line.getContinent(),finalList4.get(1));
                    break;
                case "column[5]":
                    List<String> finalList5 = list;
                    predicate = line ->!Objects.equals(line.getTowncode(),finalList5.get(1));
                    break;
                case "column[6]":
                    List<String> finalList6 = list;
                    predicate = line -> !Objects.equals(line.getContinetCode(),finalList6.get(1));
                    break;
                case "column[7]":
                    List<String> finalList7 = list;
                    predicate = line -> !Objects.equals(line.getLatitude(),Double.parseDouble(finalList7.get(1)));
                    break;
                case "column[8]":
                    List<String> finalList8 = list;
                    predicate = line ->!Objects.equals(line.getLongitude(),Double.parseDouble(finalList8.get(1)) );
                    break;
                case "column[9]":
                    List<String> finalList9 = list;
                    predicate = line ->!Objects.equals(line.getCode(),Integer.parseInt(finalList9.get(1)) );
                    break;
                case "column[10]":
                    List<String> finalList10 = list;
                    predicate = line -> !Objects.equals(line.getTemperature(),Double.parseDouble(finalList10.get(1)));
                    break;
                case "column[11]":
                    List<String> finalList11 = list;
                    predicate = line ->!Objects.equals(line.getRegionCode(),finalList11.get(1));
                    break;
                case "column[12]":
                    List<String> finalList12 = list;
                    predicate = line ->!Objects.equals(line.getAdress(),finalList12.get(1));
                    break;
                case "column[13]":
                    List<String> finalList13 = list;
                    predicate = line -> !Objects.equals(line.getComplexType(),finalList13.get(1));
                    break;
                case "column[14]":
                    List<String> finalList14 = list;
                    predicate = line ->!Objects.equals(line.getOwned(),finalList14.get(1));
                    break;
            }
            return  predicate;

        }
        else {
            list = null;
            if(string.substring(a,a+1).equals("<")) {
                list = List.of(string.split("<"));
                if(list.get(1) == null)
                {
                    predicate = line ->  false;
                    predicateList.add(predicate);
                }
                else {
                switch (list.get(0)){
                    case "column[1]":
                        List<String> finalList = list;
                        predicate = line -> line.getId() < Integer.parseInt(finalList.get(1)); ;
                        break;
                    case "column[2]":
                        List<String> finalList15 = list;
                        predicate = line -> line.getName().compareTo(finalList15.get(1)) < 0;
                        break;
                    case "column[3]":
                        List<String> finalList16 = list;
                        predicate = line ->line.getTown().compareTo(finalList16.get(1))< 0;
                        break;
                    case "column[4]":
                        List<String> finalList17 = list;
                        predicate = line -> line.getContinent().compareTo(finalList17.get(1))<0;
                        break;
                    case "column[5]":
                        List<String> finalList18 = list;
                        predicate = line ->line.getTowncode().compareTo(finalList18.get(1))<0;
                        break;
                    case "column[6]":
                        List<String> finalList19 = list;
                        predicate = line -> line.getContinetCode().compareTo(finalList19.get(1))< 0;
                        break;
                    case "column[7]":
                        List<String> finalList20 = list;
                        predicate = line -> line.getLatitude().compareTo(Double.parseDouble(finalList20.get(1))) < 0;
                        break;
                    case "column[8]":
                        List<String> finalList21 = list;
                        predicate = line ->line.getLongitude().compareTo(Double.parseDouble(finalList21.get(1))) < 0 ;
                        break;
                    case "column[9]":
                        List<String> finalList22 = list;
                        predicate = line ->line.getCode() < Integer.parseInt(finalList22.get(1)) ;
                        break;
                    case "column[10]":
                        List<String> finalList23 = list;
                        predicate = line -> line.getTemperature().compareTo(Double.parseDouble(finalList23.get(1))) < 0 ;
                        break;
                    case "column[11]":
                        List<String> finalList24 = list;
                        predicate = line ->line.getRegionCode().compareTo(finalList24.get(1))<0 ;
                        break;
                    case "column[12]":
                        List<String> finalList25 = list;
                        predicate = line ->line.getAdress().compareTo(finalList25.get(1)) <0;
                        break;
                    case "column[13]":
                        List<String> finalList26 = list;
                        predicate = line -> line.getComplexType().compareTo(finalList26.get(1))<0;
                        break;
                    case "column[14]":
                        List<String> finalList27 = list;
                        predicate = line ->line.getOwned().compareTo(finalList27.get(1))<0 ;
                        break;
                }
                    return  predicate;
                }


            }
            else {
                if(string.substring(a,a+1).equals(">")){
                    list = List.of(string.split(">"));
                    if(list.get(1) == null)
                    {
                      return   line ->  false;
                    }
                    else{
                        switch (list.get(0)){
                            case "column[1]":
                                List<String> finalList = list;
                                predicate = line -> line.getId() > Integer.parseInt(finalList.get(1)); ;
                                break;
                            case "column[2]":
                                List<String> finalList15 = list;
                                predicate = line -> line.getName().compareTo(finalList15.get(1)) > 0;
                                break;
                            case "column[3]":
                                List<String> finalList16 = list;
                                predicate = line ->line.getTown().compareTo(finalList16.get(1))> 0;
                                break;
                            case "column[4]":
                                List<String> finalList17 = list;
                                predicate = line -> line.getContinent().compareTo(finalList17.get(1))>0;
                                break;
                            case "column[5]":
                                List<String> finalList18 = list;
                                predicate = line ->line.getTowncode().compareTo(finalList18.get(1))>0;
                                break;
                            case "column[6]":
                                List<String> finalList19 = list;
                                predicate = line -> line.getContinetCode().compareTo(finalList19.get(1))> 0;
                                break;
                            case "column[7]":
                                List<String> finalList20 = list;
                                predicate = line -> line.getLatitude().compareTo(Double.parseDouble(finalList20.get(1))) > 0;
                                break;
                            case "column[8]":
                                List<String> finalList21 = list;
                                predicate = line ->line.getLongitude().compareTo(Double.parseDouble(finalList21.get(1))) > 0 ;
                                break;
                            case "column[9]":
                                List<String> finalList22 = list;
                                predicate = line ->line.getCode() > Integer.parseInt(finalList22.get(1)) ;
                                break;
                            case "column[10]":
                                List<String> finalList23 = list;
                                predicate = line -> line.getTemperature().compareTo(Double.parseDouble(finalList23.get(1))) > 0 ;
                                break;
                            case "column[11]":
                                List<String> finalList24 = list;
                                predicate = line ->line.getRegionCode().compareTo(finalList24.get(1))>0 ;
                                break;
                            case "column[12]":
                                List<String> finalList25 = list;
                                predicate = line ->line.getAdress().compareTo(finalList25.get(1)) >0;
                                break;
                            case "column[13]":
                                List<String> finalList26 = list;
                                predicate = line -> line.getComplexType().compareTo(finalList26.get(1))>0;
                                break;
                            case "column[14]":
                                List<String> finalList27 = list;
                                predicate = line ->line.getOwned().compareTo(finalList27.get(1))>0 ;
                                break;
                        }
                        return  predicate;
                    }


                }
                else {
                    if(string.substring(a,a+1).equals("=")) {
                        list = List.of(string.split("="));
                        switch (list.get(0)){
                            case "column[1]":
                                List<String> finalList1 = list;
                                predicate= line -> Objects.equals(line.getId(),Integer.parseInt(finalList1.get(1)));
                                break;
                            case "column[2]":
                                List<String> finalList2 = list;
                                predicate = line -> Objects.equals(line.getName(),finalList2.get(1));
                                break;
                            case "column[3]":
                                List<String> finalList3 = list;
                                predicate = line ->Objects.equals(line.getTown(),finalList3.get(1));
                                break;
                            case "column[4]":
                                List<String> finalList4 = list;
                                predicate = line -> Objects.equals(line.getContinent(),finalList4.get(1));
                                break;
                            case "column[5]":
                                List<String> finalList5 = list;
                                predicate = line ->Objects.equals(line.getTowncode(),finalList5.get(1));
                                break;
                            case "column[6]":
                                List<String> finalList6 = list;
                                predicate = line -> Objects.equals(line.getContinetCode(),finalList6.get(1));
                                break;
                            case "column[7]":
                                List<String> finalList7 = list;
                                predicate = line -> Objects.equals(line.getLatitude(),Double.parseDouble(finalList7.get(1)));
                                break;
                            case "column[8]":
                                List<String> finalList8 = list;
                                predicate = line ->Objects.equals(line.getLongitude(),Double.parseDouble(finalList8.get(1)) );
                                break;
                            case "column[9]":
                                List<String> finalList9 = list;
                                predicate = line ->Objects.equals(line.getCode(),Integer.parseInt(finalList9.get(1)) );
                                break;
                            case "column[10]":
                                List<String> finalList10 = list;
                                predicate = line -> Objects.equals(line.getTemperature(),Double.parseDouble(finalList10.get(1)));
                                break;
                            case "column[11]":
                                List<String> finalList11 = list;
                                predicate = line ->Objects.equals(line.getRegionCode(),finalList11.get(1));
                                break;
                            case "column[12]":
                                List<String> finalList12 = list;
                                predicate = line ->Objects.equals(line.getAdress(),finalList12.get(1));
                                break;
                            case "column[13]":
                                List<String> finalList13 = list;
                                predicate = line -> Objects.equals(line.getComplexType(),finalList13.get(1));
                                break;
                            case "column[14]":
                                List<String> finalList14 = list;
                                predicate = line ->Objects.equals(line.getOwned(),finalList14.get(1));
                                break;
                        }
                        return  predicate;
                    }
                }
            }
        }

    return null;}


    public Line printLine(Line line)
    {
       if(finalPredicate!=null)
       {
           finalPredicate = mergePredicate(finalPredicate,nameFilter);
           if(finalPredicate.validate(line))
           {
               System.out.println(line);
               return line;
           }
           else

               return null;

       }
       else
       {
           if(nameFilter.validate(line))
           {
               System.out.println(line);
               return line;
           }
           else return null;
       }
    }
    public static Predicate mergePredicate(Predicate p1, Predicate p2) {
        return (el) -> p1.validate(el) && p2.validate(el);
    }

    public static Predicate finalMergePredicate(Predicate p1, Predicate p2) {
        return (el) -> p1.validate(el) || p2.validate(el);
    }
    public void addFilter(String string)
    {
        nameFilter =line ->line.getName().substring(0,string.length()).equals(string) ;
    }
    public void clearList()
    {
        predicateList.clear();
    }



}
