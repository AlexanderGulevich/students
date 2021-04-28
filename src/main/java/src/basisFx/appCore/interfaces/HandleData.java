package basisFx.appCore.interfaces;


@FunctionalInterface
public interface HandleData<F, T> {
    T convert(F from);
}
