package com.example.reactiveprogramming;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public class MonoFluxEx {
	public static void main(String args[]) {
		Mono<String> monoex=Mono.just("Hello mono");
		monoex.subscribe(System.out::println);
		Flux<String> fluxex=Flux.just("Apple","Banana","Orange","Mango");
		fluxex.subscribe(element->System.out.println(element));
	}
}
