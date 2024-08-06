/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.validator;


public class PartnerValidator extends CommonsValidator{
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("La cantidad del socio", amount);
    }
    
}
