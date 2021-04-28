package basisFx.appCore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataStore {
    Sorting SORTING () default Sorting.NONE ;
    boolean AS_OUTER_ID() default   false;
    boolean ANALIZED_DATE() default   false;
    boolean NOT_CHECK_FOR_TRANSACTIONS() default   false;
}
