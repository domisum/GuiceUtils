package io.domisum.lib.guiceutils;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import io.domisum.lib.auxiliumlib.annotations.API;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;

@API
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuiceMultibinder
{
	
	// CLASSES
	@API
	@SafeVarargs
	public static <T> void multibind(Binder binder, Class<T> parent, Class<? extends T>... children)
	{
		multibind(binder, parent, Arrays.asList(children));
	}
	
	@API
	public static <T> void multibind(Binder binder, Class<T> parent, Collection<Class<? extends T>> children)
	{
		var setBinder = Multibinder.newSetBinder(binder, parent);
		for(var child : children)
			setBinder.addBinding().to(child);
	}
	
	@API
	@SafeVarargs
	public static <T> void multibind(Binder binder, TypeLiteral<T> parent, Class<? extends T>... children)
	{
		var setBinder = Multibinder.newSetBinder(binder, parent);
		for(var child : children)
			setBinder.addBinding().to(child);
	}
	
	
	// INSTANCES
	@API
	@SafeVarargs
	public static <T, C extends T> void multibindInstances(Binder binder, Class<T> parent, C... children)
	{
		var setBinder = Multibinder.newSetBinder(binder, parent);
		for(var child : children)
			setBinder.addBinding().toInstance(child);
	}
	
}
