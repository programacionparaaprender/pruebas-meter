package com.programacionparaaprender.meter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class AppMeter {
    public static void main(String[] args){
    	if(args.length > 0) {
    		System.out.println(args[0] + " " + args[1]);	
    	}
    	prueba2(args);
    }
    
    public static void prueba1(String[] args){
        System.out.println("Hola mundo");
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        Counter counter = registry.counter("numero.empleados", "oficina", "Benito Juarez");
        counter.increment();
        counter.increment(200);
        System.out.printf("Número de empleados: %f\n", counter.count());
    }
    
    public static void prueba2(String[] args){
        System.out.println("Hola mundo");
        //CompositeMeterRegistry compositeMeter = new CompositeMeterRegistry();
        CompositeMeterRegistry compositeMeter = Metrics.globalRegistry;
        Counter counter = compositeMeter.counter("numero.empleados", "oficina", "Benito Juarez");
        counter.increment();
        counter.increment(200);
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        SimpleMeterRegistry registry2 = new SimpleMeterRegistry();
        compositeMeter.add(registry);
        compositeMeter.add(registry2);
        counter.increment();
        counter.increment(200);
        System.out.printf("Número de empleados: %f\n", counter.count());
        foo(args);
    }
    
    public static void foo(String[] args){
        CompositeMeterRegistry compositeMeter = Metrics.globalRegistry;
        Counter counter = compositeMeter.counter("numero.empleados", "oficina", "Benito Juarez");
        counter.increment(150);
        System.out.printf("Número de empleados: %f\n", counter.count());
    }
}
