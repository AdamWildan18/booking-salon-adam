package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Reservation {
    
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)
    private static int nextID = 1;

    
    // private String reservationId = generateID();

    public Reservation(Customer customer, Employee employee, List<Service> services) {
        this.reservationId = "Rsv-" + String.format("%02d", nextID++);
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = reservationPrice;
        this.workstage = Workstage.IN_PROCESS.name();
        calculateReservationPrice();

    };

    public void calculateReservationPrice(){
        double totalPrice = 0;
        for(Service service: services){
            totalPrice += service.getPrice();
        }

        double discount = 0;
        if (customer != null && customer.getMember() != null) {
            switch (customer.getMember().getMembershipName()) {
                case "none":
                    discount = 0; // No discount for none members
                    break;
                case "Silver":
                    discount = 0.05; // 5% discount for silver members
                    break;
                case "Gold":
                    discount = 0.1; // 10% discount for gold members
                    break;
            }
        }
        setReservationPrice(totalPrice - (totalPrice * discount));
    }

    public enum Workstage {
        IN_PROCESS,
        FINISH,
        CANCELED
    }
}
