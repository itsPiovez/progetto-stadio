package Azioni;

public class Tifoso {

    public String nome;
    public Tifoso(String nome){
        this.nome = nome;
    }
    public String GetNome(){
        return nome;
    }/*
    public static int GetID(String input){
        int length = input.length();
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (input.charAt(i) == ' ') {
                String lastWord = input.substring(i + 1, length);
                result = Integer.parseInt(lastWord);
                break;
            }
        }
        return result;
    }*/

}

