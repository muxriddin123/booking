package uz.app.authapp.config;

import uz.app.authapp.entity.enums.Times;

public class Test {
    public static void main(String[] args) {
        for (Times value : Times.values()) {
            System.out.println(value.ordinal()+" "+value.name());
        }
    }
}
