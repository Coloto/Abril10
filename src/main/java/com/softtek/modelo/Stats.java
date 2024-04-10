package com.softtek.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stats {
    private ArrayList<Integer> lista;

    public int minimo(){
        int menor = lista.get(0);
        for (int i=1;i<lista.size();i++){
            if (lista.get(i)<menor) menor = lista.get(i);
        }
        return menor;
    }

    public int maximo(){
        int mayor = lista.get(0);
        for (int i=1;i<lista.size();i++){
            if (lista.get(i)>mayor) mayor = lista.get(i);
        }
        return mayor;
    }

    public int media(){
        int total=0;
        for (int n : lista){
            total+=n;
        }
        return total/lista.size();
    }
}
