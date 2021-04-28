package basisFx.appCore.interfaces;

public interface Observer <T>{
    public void notice();
    public void notice(T t);
}
