package com.sukhendu.workflow;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class ReserveSeatOnShip implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String money = "0";
        String ticketType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        int moneyInt = Integer.parseInt(money);

        if(moneyInt >= 10000){
            ticketType = "First class";
        } else if (moneyInt >= 5000) {
            ticketType = "Second class";
        } else if (moneyInt <= 10) {
            ticketType = "Stowaway class";
            throw new BpmnError("Go_Back","No seat at this price. Go back to work.");

        }

        delegateExecution.setVariable("ticketType", ticketType);
    }
}
