package io.domisum.lib.guiceutils.decorator;

import io.domisum.lib.auxiliumlib.util.ValidationUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BindForClass
		implements SelfClass
{
	
	private final Class<?> value;
	
	
	// INIT
	public static SelfClass of(Class<?> clazz)
	{
		ValidationUtil.notNull(clazz, "clazz");
		return new BindForClass(clazz);
	}
	
	
	// OBJECT
	@Override
	public String toString()
	{
		return "@"+SelfClass.class.getName()+"(value="+value+")";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof SelfClass))
			return false;
		
		var other = (SelfClass) o;
		return value.equals(other.value());
	}
	
	@Override
	public int hashCode()
	{
		// This is specified in java.lang.Annotation
		return (127*"value".hashCode())^value.hashCode();
	}
	
	
	// ANNOTATION
	@Override
	public Class<?> value()
	{
		return value;
	}
	
	@Override
	public Class<? extends Annotation> annotationType()
	{
		return SelfClass.class;
	}
	
}
