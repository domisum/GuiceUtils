package io.domisum.lib.guiceutils.decorator;

import com.google.inject.Binder;
import io.domisum.lib.auxiliumlib.annotations.API;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@API
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuiceDecoratorBinder
{
	
	/*
	Wrapping decorator class first, then contained classes.
	 */
	@API
	@SafeVarargs
	public static <T> void bindDecoratorChain(Binder binder, Class<T> base, Class<? extends T>... implementingClasses)
	{
		Class<? extends T> previous = null;
		for(var implClass : implementingClasses)
		{
			var bindingBuilder = binder.bind(base);
			if(previous != null)
				bindingBuilder.annotatedWith(DependencyInClass.of(previous));
			bindingBuilder.to(implClass);
			
			previous = implClass;
		}
	}
	
}
