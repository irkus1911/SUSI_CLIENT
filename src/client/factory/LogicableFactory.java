/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.factory;

import lib.dataModel.User;
import lib.interfaces.Logicable;

/**
 *
 * @author UnaiUrtiaga
 */
public class LogicableFactory {
    
    /**
     * Metodo de la factoria del lado del cliente para implementar la clase 
     * DataTraffic
     * @return Devuelve un objeto de la interfaz Logicable el cual va a servir
     * para luego implementar la siguiente clase
     */
    
    public Logicable getDataTraffic(){
        
        Logicable dataTraffic = new DataTraffic();
        
        return dataTraffic;
    }
    
}
