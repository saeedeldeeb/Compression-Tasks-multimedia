/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multimediatasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author computer market
 */
public final class Quantization {

    private final int quantizer,input;
    private int N, R, delta;
    int sequence[] = {550, 600, -100, 150, -300, 900, 0, 850};
    ArrayList<Integer> levels = new ArrayList<>();

    public Quantization() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the quantizer: ");
        quantizer = sc.nextInt();
        System.out.print("Enter Your test valu: ");
        input = sc.nextInt();
        Arrays.sort(sequence);
intialCalc();
getLevels();
        System.out.println(getError());
    }

    void intialCalc() {
        N = (int) Math.pow(2, quantizer);
        R = sequence[sequence.length - 1] - sequence[0];
        delta = R / N;
    }
    
    void getLevels(){
        int check = sequence[0];
        while(check <= sequence[sequence.length - 1]){
            levels.add(check);
          check +=delta;  
        }
    }
    int getError(){
        int i,medium,error;
       for(i=0;i<levels.size();i++){
           if(input<=levels.get(i))
               break;
       }
       medium = (levels.get(i)+levels.get(i-1))/2;
       error= input-medium;
        System.out.println("--------Answer----------");
       System.out.println("You are in level:" + i);
        System.out.print("Error is: ");
        return Math.abs(error);
    }

    public static void main(String[] args) {
new Quantization();
        
    }
}
