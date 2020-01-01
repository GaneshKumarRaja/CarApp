package com.ganesh.domain.common;

public abstract class Mapper<E, T> {
    public abstract T mapFrom(E from);
}
