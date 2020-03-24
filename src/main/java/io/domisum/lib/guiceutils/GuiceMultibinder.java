package io.domisum.lib.guiceutils;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import io.domisum.lib.auxiliumlib.PHR;
import io.domisum.lib.auxiliumlib.util.java.ClassUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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
		var setBinder = Multibinder.newSetBinder(binder, parent);
		for(Class<? extends T> aClass : children)
			setBinder.addBinding().to(aClass);
	}

	@SafeVarargs
	public static <T> void multibind(Binder binder, TypeLiteral<T> parent, Class<? extends T>... children)
	{
		var setBinder = Multibinder.newSetBinder(binder, parent);
		for(Class<? extends T> aClass : children)
			setBinder.addBinding().to(aClass);
	}

	@SuppressWarnings("unchecked")
	public static <T> void multibind(Binder binder, Class<T> parent, String childrenPackage)
	{
		var packageClasses = ClassUtil.getClasses(childrenPackage);

		if(packageClasses.isEmpty())
			throw new IllegalArgumentException("package is empty: "+childrenPackage);

		var childrenClasses = new HashSet<Class<? extends T>>();
		for(Class<?> clazz : packageClasses)
			if(parent.isAssignableFrom(clazz))
				childrenClasses.add((Class<? extends T>) clazz);
			else
				throw new IllegalArgumentException(PHR.r("childrenPackage contained class which is not a child of {}: {}",
						parent.getName(),
						clazz.getName()
				));

		multibind(binder, parent, childrenClasses);
	}

}
