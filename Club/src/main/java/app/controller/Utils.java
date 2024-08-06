/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import java.sql.Date;
import java.util.Scanner;


public abstract class Utils {
   private static Scanner reader = new Scanner(System.in);
   
   public static Date getDate(){
       return new Date(System.currentTimeMillis());
   }
   public static Scanner getReader(){
       return reader;
   }
}
