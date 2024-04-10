package com.softtek.presentacion;

import com.softtek.modelo.Client;
import com.softtek.modelo.Person;
import com.softtek.modelo.Stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //ejemplo1();
        //ejemplo2();
        //ejemplo9();

        //ejercicio1();
        ejercicio2();
        //ejercicio3();
    }

    private static void ejemplo1() {
        Function<String, Integer> fx = x -> x.length();
        Integer rpta = fx.apply("mitocode");
        System.out.println(rpta);
    }

    private static void ejemplo2() {
        Function<String, Integer> fx1 = x -> x.length();
        Function<Integer, Integer> fx2 = x -> x + 10;

        Integer rpta = fx1.andThen(fx2).apply("mitocode");
        System.out.println(rpta);
    }

    private static void ejemplo3() {
        Function<Integer, Integer> fx1 = x -> x * 2;
        Function<Integer, Integer> fx2 = x -> x + 10;

        Integer rpta = fx1.andThen(fx2).apply(5);
        System.out.println(rpta);
    }

    private static void ejemplo4() {
        Function<Integer, Integer> fx1 = x -> x * 2;
        Function<Integer, Integer> fx2 = x -> x + 10;

        Integer rpta = fx1.compose(fx2).apply(5);
        System.out.println(rpta);
    }

    public static void ejemplo5() {
        //f(x) = x
        Function<Client, Client> fx = Function.identity();
        System.out.println(fx.apply(new Client()));
    }

    public static void ejemplo6() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "Mito", 32));
        list.add(new Person(2, "Code", 33));
        list.add(new Person(3, "Jaime", 34));

        Map<Integer, Person> map = list.stream()
                .collect(Collectors.toMap(Person::getId, Function.identity())); //e-> e.getId()

        System.out.println(map);
    }

    public static void ejemplo7() {
        Function<Integer, Integer> f1 = Function.identity();
        Function<Integer, Integer> f2 = Function.identity();
        Function<Integer, Integer> f3 = Function.identity();

        Function<Integer, Integer> f4 = t -> t;
        Function<Integer, Integer> f5 = t -> t;
        Function<Integer, Integer> f6 = t -> t;

        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println("====================================");
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);
    }

    private static void ejemplo8() {
        IntFunction<Double> fn1 = x -> x * 2.5;
        DoubleFunction<String> fn2 = x -> "Value is " + x;
        LongFunction<String> fn3 = x -> "Value is " + x;

        double result1 = fn1.apply(5);
        String result2 = fn2.apply(30.99);
        String result3 = fn3.apply(50);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    private static void ejemplo9() {
        ToIntFunction<Person> showAge = Person::getAge;
        ToIntBiFunction<Person, Integer> calculateNewAge = (p, i) -> p.getAge() + i;
        DoubleToIntFunction fx = x -> 5;

        System.out.println(fx.applyAsInt(10));

        Integer newAge = calculateNewAge.applyAsInt(new Person(1, "mito", 15), 20);
        System.out.println(newAge);
    }

    public static void ejercicio1(){
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i=1;i<=10;i++){
            numeros.add(i);
        }
        Function<ArrayList<Integer>, ArrayList<Integer>> filtrar = listaNumeros -> {
            ArrayList<Integer> numerosFiltrado = new ArrayList<>();
            for(int numero : listaNumeros){
                if (numero%2==0) numerosFiltrado.add(numero);
            }
            return numerosFiltrado;
        };
        ArrayList<Integer> numerosFiltrados = filtrar.apply(numeros);
        System.out.println("Lista de numeros: " + numeros);
        System.out.println("Lista de numeros filtrados por numeros pares: "+ numerosFiltrados);
    }

    public static void ejercicio2(){
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(2);
        numeros.add(44);
        numeros.add(34);
        numeros.add(7);
        numeros.add(29);

        Function<ArrayList<Integer>, Stats> estadisticas = listaNumeros -> {
            int min = Collections.min(listaNumeros);
            int max = Collections.max(listaNumeros);
            int total=0;
            for (int n : listaNumeros){
                total+=n;
            }
            double media = total/listaNumeros.size();

            return new Stats(min, max, media);
        };


        Stats stats = estadisticas.apply(numeros);
        System.out.println(stats.toString());
    }

    public static void ejercicio3(){
        String pass = "Hola_1234";

        /*Function<String, Boolean> validarContra = contra -> {
            boolean mayus = contra.chars()
                    .anyMatch(c -> Character.isUpperCase(c));
            boolean minus = contra.chars()
                    .anyMatch(c -> Character.isLowerCase(c));
            boolean num = contra.chars()
                    .anyMatch(c -> Character.isDigit(c));
            boolean caracterEspecial = contra.chars()
                    .anyMatch(c -> !Character.isLetterOrDigit(c));

            if (contra.length()>=8 && mayus && minus && num && caracterEspecial) return true;
            return false;
        };
        System.out.println(validarContra.apply(pass));*/

        Function<String, Boolean> mayus = contra -> contra.chars()
                .anyMatch(c -> Character.isUpperCase(c));
        Function<String, Boolean> minus = contra -> contra.chars()
                .anyMatch(c -> Character.isLowerCase(c));
        Function<String, Boolean> num = contra -> contra.chars()
                .anyMatch(c -> Character.isDigit(c));
        Function<String, Boolean> caracterEspecial = contra -> contra.chars()
                .anyMatch(c -> !Character.isLetterOrDigit(c));

        if (mayus.apply(pass)
                && minus.apply(pass)
                && num.apply(pass)
                && caracterEspecial.apply(pass)) System.out.println("Contraseña valida");


    }
}
