package io.domisum.lib.guiceutils;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuiceMultibinder
{

	@SafeVarargs
	public static <T> void multibind(Binder binder, Class<T> parent, Class<? extends T>... children)
	{
		multibind(binder, parent, Arrays.asList(children));
	}

	public static <T> void multibind(Binder binder, Class<T> parent, Collection<Class<? extends T>> children)
	{
		Multibinder<T> setBinder = Multibinder.newSetBinder(binder, parent);
		for(Class<? extends T> aClass : children)
			setBinder.addBinding().to(aClass);
	}

	@SafeVarargs
	public static <T> void multibind(Binder binder, TypeLiteral<T> parent, Class<? extends T>... children)
	{
		Multibinder<T> setBinder = Multibinder.newSetBinder(binder, parent);
		for(Class<? extends T> aClass : children)
			setBinder.addBinding().to(aClass);
	}

}
