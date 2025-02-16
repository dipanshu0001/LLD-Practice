package DTO;

import java.util.concurrent.atomic.AtomicInteger;

public class EntranceGate {
    private static final AtomicInteger gateCounter=new AtomicInteger(1);
    private int gateId;

    public EntranceGate(){
        this.gateId=gateCounter.getAndIncrement();
    }
}
