package org.guins.armeria.testbed;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class Generics {
    class A {
    }

    class B extends A {
    }

    class C extends B {
    }

    class D extends C {
    }

    class D2 extends C {
    }

    class E extends D {
    }

    @Test
    void testSuperList() {
        List<? super C> list = new ArrayList<B>();
        // writing can be done on subclasses of C (including C)
        list.add(new D());
        list.add(new D2());
        list.add(new E());

        System.out.println(list);

        // reading can be done only as object
        Object o = list.get(0);
    }

    @Test
    void testExtendsList() {
        List<? extends C> list = new ArrayList<D>();
        // cannot write
//        list.add(new C());

        System.out.println(list);

        // reading can be done as C (or superclass of C)
        C o = list.get(0);
        B b = list.get(0);
    }

    @Test
    void testFnSuperExtends() {
        Function<? super C, ? extends C> f;
        f = new Function<B, D>() {
            @Override
            public D apply(B a) {
                return new D();
            }
        };

        // Can input subclasses (including C)
        f.apply(new C());
        f.apply(new D());

        // Can get superclasses (including C)
        final C c = f.apply(new C());
        final B b = f.apply(new C());
    }

    @Test
    void testFn() {
        Function<C, C> f;
//        f = new Function<B, D>() {
//            @Override
//            public D apply(B a) {
//                return new D();
//            }
//        };
        // Only able to accept function with strict requirement C -> C
        f = Function.identity();

        // Can input subclasses (including C)
        f.apply(new C());
        f.apply(new D());

        // Can get superclasses (including C)
        final C c = f.apply(new C());
        final B b = f.apply(new C());
    }

    @Test
    void testFnExtendsSuper() {
        Function<? extends C, ? super C> f;
//        f = new Function<B, D>() {
//            @Override
//            public D apply(B a) {
//                return new D();
//            }
//        };
        // unable to use
        f = Function.identity();

        // Cannot receive input
//        f.apply(new C()); // could be D,E
//        f.apply(new D()); // could be C,E
//        f.apply(new E()); // could be C,D

        Function<? super C, ? super C> g = Function.identity();
        // Can only get object
        final Object o = g.apply(new C());
    }
}
