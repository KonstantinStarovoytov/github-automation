package core.annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(TYPE)
@Retention(RUNTIME)
@Scope(value = "thread")
@Component
public @interface PageComponent {

}