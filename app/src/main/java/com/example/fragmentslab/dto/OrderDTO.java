package com.example.fragmentslab.dto;

import java.util.Objects;

public class OrderDTO {

    private String customer;
    private String phoneNumber;
    private String pizzaName;
    private boolean spicy;
    private boolean withCocaCola;

    public OrderDTO() {

    }

    public OrderDTO(String customer, String phoneNumber, String pizzaName, boolean spicy, boolean withCocaCola) {
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.pizzaName = pizzaName;
        this.spicy = spicy;
        this.withCocaCola = withCocaCola;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    public boolean isWithCocaCola() {
        return withCocaCola;
    }

    public void setWithCocaCola(boolean withCocaCola) {
        this.withCocaCola = withCocaCola;
    }

    public String getSpicyText() {
        return spicy ? "Так" : "Ні";
    }

    public String getWithColaText() {
        return withCocaCola ? "Додати колу" : "Не потрібно";
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "customer='" + customer + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pizzaName='" + pizzaName + '\'' +
                ", spicy=" + spicy +
                ", withCocaCola=" + withCocaCola +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return spicy == orderDTO.spicy &&
                withCocaCola == orderDTO.withCocaCola &&
                Objects.equals(customer, orderDTO.customer) &&
                Objects.equals(phoneNumber, orderDTO.phoneNumber) &&
                Objects.equals(pizzaName, orderDTO.pizzaName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, phoneNumber, pizzaName, spicy, withCocaCola);
    }
}
