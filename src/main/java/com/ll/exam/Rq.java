package com.ll.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    HttpServletRequest req;
    HttpServletResponse resp;



    public Rq(HttpServletRequest req, HttpServletResponse resp) {


        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIntParam(String parm, int defaultValue){
        String value = req.getParameter(parm);
        if(value == null)
            return defaultValue;

        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException e){
            System.out.println(e);
        }
        return defaultValue;
    }


    public void appendBody(String formatted) {
        try {
            resp.getWriter().append(formatted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAttr(String name, Object value) {
        req.setAttribute(name, value);
    }

    public void view(String path){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp" + path + ".jsp");

        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getPath() {
        return req.getRequestURI();
    }
    public String getActionPath() {
        String[] bits = req.getRequestURI().split("/");

        return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);
    }
    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);
        if(value == null || value.trim().length() == 0)
            return defaultValue;

        return value;
    }

    public long getLongPathValueByIndex(int index, int defaultValue){

        String value = getPathValueByIndex(index, null);
        if( value == null)
            return defaultValue;

        try {
            return Long.parseLong(value);
        }catch (NumberFormatException e ){
            return defaultValue;
        }

    }

    private String getPathValueByIndex(int index, String defaultValue) {
        String[] bits = req.getRequestURI().split("/");

        try {
            return bits[4 + index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return defaultValue;
        }
    }


    public String getMethod() {
        return req.getMethod();
    }
}
