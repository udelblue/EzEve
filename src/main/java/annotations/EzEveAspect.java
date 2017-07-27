package annotations;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class EzEveAspect {
	
	@After(value = "@within(gov.nj.doh.annotations.EzEve) || @annotation(gov.nj.doh.annotations.EzEve)")
	public void after(JoinPoint joinPoint) throws Throwable {
		System.out.println("after");

		System.out.println("class: " + joinPoint.getSignature().getDeclaringType().getSimpleName());
		System.out.println(" method: " + joinPoint.getSignature().getName());
		
	    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	    Method method = signature.getMethod();
	    EzEve ezEve = method.getAnnotation(EzEve.class);
	    Boolean principleAsInvoker = ezEve.principleAsInvoker();
	    if( principleAsInvoker ){
			String username;
		/*	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !authentication.isAuthenticated()) {
				username = "";
			}
			username = authentication.getName();*/
	    }
	    
	    String eventType  = ezEve.eventType();
	    
		
	}

}
