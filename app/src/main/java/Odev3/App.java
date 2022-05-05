package Odev3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;


public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    //get("/", (req, res) -> "Hello, World");


    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        get("/compute",
        (rq, rs) -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "compute.mustache");
        },
        new MustacheTemplateEngine() //Tarayıcaki görseli görmemiz için çalıştırmamız gerekiyor
        );


        post("/compute", (req, res) -> {
           
            String input1 = req.queryParams("input1"); //complate.mustache kısmında textarea kısmına yazlığımız inputlar buraya geliyor

            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));// Okuma sirasinda farklı karakterlerin okunmamasını sagliyor.
                inputList.add(value);
            }
            sc1.close();
            System.out.println(inputList);

            String input2 = req.queryParams("input2").replaceAll("\\s","");
            int input2AsInt = Integer.parseInt(input2);// search metodunda input2 aranacak eleman ve int deger.

            boolean result = App.search(inputList, input2AsInt);
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
            }, 
            new MustacheTemplateEngine()
            );


    }

    public static boolean search(ArrayList<Integer> array, int e)
    {
        System.out.println("Inside Search");

        if(array == null ) return false;
        for(int elt:array)
        {
            if(elt == e) return true;

        }

        return false;   

    }
}
