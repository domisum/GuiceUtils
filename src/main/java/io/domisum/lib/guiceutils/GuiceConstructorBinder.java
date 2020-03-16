package io.domisum.lib.guiceutils;

import com.google.inject.Binder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuiceConstructorBinder
{

	public static <I> void bindToOnlyOwn(Binder binder, Class<I> implementingClass)
	{
		bindToOnly(binder, implementingClass, implementingClass);
	}

	public static <A, I extends A> void bindToOnly(
			Binder binder, Class<A> abstractClass, Class<I> implementingClass)
	{
		@SuppressWarnings("unchecked")
		Constructor<I>[] constructorsArray = (Constructor<I>[]) implementingClass.getConstructors();
		List<Constructor<I>> constructors = Arrays.asList(constructorsArray);

		if(constructors.isEmpty())
			throw new IllegalArgumentException("no constructor to bind to found in class "+implementingClass.getName());
		if(constructors.size() > 1)
			throw new IllegalArgumentException("too many constructors to bind to in class "+implementingClass.getName());

		Constructor<I> constructor = constructors.get(0);
		binder.bind(abstractClass).toConstructor(constructor);
	}

}
