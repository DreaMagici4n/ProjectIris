/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author lucas
 */
public class IrisController {
    private static IrisController instance;
    
    private IrisController(){}
    
    
    public static IrisController getInstance(){
        if (instance==null)
            instance = new IrisController();
        return instance;
    }
    
    
}
