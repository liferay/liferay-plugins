/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.compat.portal.kernel.bean;

import com.liferay.portal.kernel.memory.EqualityWeakReference;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author Shuyang Zhou
 * @author Ethan Bustad
 */
public class ConstantsBeanFactoryUtil {

	public static Object getConstantsBean(Class<?> constantsClass) {
		Reference<?> constantsBeanReference = constantsBeans.get(
			new EqualityWeakReference<Class<?>>(constantsClass));

		Object constantsBean = null;

		if (constantsBeanReference != null) {
			constantsBean = constantsBeanReference.get();
		}

		if (constantsBean == null) {
			constantsBean = createConstantsBean(constantsClass);

			constantsBeans.put(
				new EqualityWeakReference<Class<?>>(
					constantsClass, constantsClassReferenceQueue),
				new WeakReference<Object>(constantsBean));
		}

		while (true) {
			EqualityWeakReference<Class<?>> staleConstantsClassReference =
				(EqualityWeakReference<Class<?>>)
					constantsClassReferenceQueue.poll();

			if (staleConstantsClassReference == null) {
				break;
			}

			constantsBeans.remove(staleConstantsClassReference);
		}

		return constantsBean;
	}

	protected static Object createConstantsBean(Class<?> constantsClass) {
		ClassLoader classLoader = constantsClass.getClassLoader();

		String constantsBeanClassName = constantsClass.getName() + "Bean";

		Class<?> constantsBeanClass = null;

		synchronized (classLoader) {
			try {
				constantsBeanClass = classLoader.loadClass(
					constantsBeanClassName);
			}
			catch (ClassNotFoundException cnfe) {
			}

			try {
				if (constantsBeanClass == null) {
					Method defineClassMethod = ReflectionUtil.getDeclaredMethod(
						ClassLoader.class, "defineClass", String.class,
						byte[].class, int.class, int.class);

					byte[] classData = generateConstantsBeanClassData(
						constantsClass);

					constantsBeanClass = (Class<?>)defineClassMethod.invoke(
						classLoader, constantsBeanClassName, classData, 0,
						classData.length);
				}

				return constantsBeanClass.newInstance();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected static byte[] generateConstantsBeanClassData(
		Class<?> constantsClass) {

		String constantsClassBinaryName = getClassBinaryName(constantsClass);

		String constantsBeanClassBinaryName = constantsClassBinaryName + "Bean";

		String objectClassBinaryName = getClassBinaryName(Object.class);

		ClassWriter classWriter = new ClassWriter(0);

		classWriter.visit(
			Opcodes.V1_5, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
			constantsBeanClassBinaryName, null, objectClassBinaryName, null);

		MethodVisitor methodVisitor = classWriter.visitMethod(
			Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

		methodVisitor.visitCode();
		methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
		methodVisitor.visitMethodInsn(
			Opcodes.INVOKESPECIAL, objectClassBinaryName, "<init>", "()V");
		methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(1, 1);
		methodVisitor.visitEnd();

		Field[] fields = constantsClass.getFields();

		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			Class<?> fieldClass = field.getType();

			Type fieldType = Type.getType(fieldClass);

			methodVisitor = classWriter.visitMethod(
				Opcodes.ACC_PUBLIC, "get" + field.getName(),
				"()" + fieldType.getDescriptor(), null, null);

			methodVisitor.visitCode();
			methodVisitor.visitFieldInsn(
				Opcodes.GETSTATIC, constantsClassBinaryName, field.getName(),
				fieldType.getDescriptor());

			int returnOpcode = Opcodes.ARETURN;

			if (fieldClass.isPrimitive()) {
				if (fieldClass == Float.TYPE) {
					returnOpcode = Opcodes.FRETURN;
				}
				else if (fieldClass == Double.TYPE) {
					returnOpcode = Opcodes.DRETURN;
				}
				else if (fieldClass == Long.TYPE) {
					returnOpcode = Opcodes.LRETURN;
				}
				else {
					returnOpcode = Opcodes.IRETURN;
				}
			}

			methodVisitor.visitInsn(returnOpcode);

			methodVisitor.visitMaxs(fieldType.getSize(), 1);

			methodVisitor.visitEnd();
		}

		Method[] methods = constantsClass.getMethods();

		for (Method method : methods) {
			if (!Modifier.isStatic(method.getModifiers())) {
				continue;
			}

			Class<?>[] parameterClasses = method.getParameterTypes();

			Type[] parameterTypes = new Type[parameterClasses.length];

			for (int i = 0; i < parameterClasses.length; i++) {
				parameterTypes[i] = Type.getType(parameterClasses[i]);
			}

			String methodDescriptor = Type.getMethodDescriptor(
				Type.getType(method.getReturnType()), parameterTypes);

			methodVisitor = classWriter.visitMethod(
				Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, method.getName(),
				methodDescriptor, null, null);

			methodVisitor.visitCode();

			int stackIndex = 0;

			for (Type parameterType : parameterTypes) {
				methodVisitor.visitVarInsn(
					parameterType.getOpcode(Opcodes.ILOAD), stackIndex);

				stackIndex += parameterType.getSize();
			}

			methodVisitor.visitMethodInsn(
				Opcodes.INVOKESTATIC, constantsClassBinaryName,
				method.getName(), methodDescriptor);

			Type returnType = Type.getType(method.getReturnType());

			methodVisitor.visitInsn(returnType.getOpcode(Opcodes.IRETURN));

			methodVisitor.visitMaxs(
				stackIndex + returnType.getSize(), parameterTypes.length + 1);

			methodVisitor.visitEnd();
		}

		classWriter.visitEnd();

		return classWriter.toByteArray();
	}

	protected static String getClassBinaryName(Class<?> clazz) {
		String className = clazz.getName();

		return className.replace('.', '/');
	}

	protected static ConcurrentMap
		<EqualityWeakReference<Class<?>>, Reference<?>>
			constantsBeans =
				new ConcurrentHashMap
					<EqualityWeakReference<Class<?>>, Reference<?>>();
	protected static ReferenceQueue<Class<?>> constantsClassReferenceQueue =
		new ReferenceQueue<Class<?>>();

}