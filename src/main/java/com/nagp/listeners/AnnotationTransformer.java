package com.nagp.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements  IAnnotationTransformer
{	
	@Override
	public void transform(ITestAnnotation annotation, Class testclass, Constructor testConstructor, Method met) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
