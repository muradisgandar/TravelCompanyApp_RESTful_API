/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dto;

import lombok.Data;

/**
 *
 * @author murad_isgandar
 */
@Data
public class ResponseDTO {
    
    private String message;
    private int statusCode;
    private Object obj;

    
    public ResponseDTO(String message) {
        this.message = message;
    }
    
    public ResponseDTO(Object obj) {
        this.obj = obj;
    }

    public ResponseDTO(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
    
    public ResponseDTO(String message, int statusCode, Object obj) {
        this.message = message;
        this.statusCode = statusCode;
        this.obj = obj;
    }

    
    

    
    
    
    
    
    
    
    
}
