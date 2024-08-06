/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import java.util.Date;
import java.util.Scanner;


public abstract class Utils {
   private static Scanner reader = new Scanner(System.in);
   private static Date date = new Date();
   
   public static Date getDate(){
       return date;
   }
   public static Scanner getReader(){
       return reader;
   }
}
