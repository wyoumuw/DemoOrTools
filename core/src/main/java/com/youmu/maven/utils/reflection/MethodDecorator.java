package com.youmu.maven.utils.reflection;

import com.youmu.maven.utils.reflection.utils.YoumuReflectionUtil;

import java.lang.reflect.Method;


public class MethodDecorator {
        private final  Method method;
        private final  String methodNameWithParameters;

        public MethodDecorator(Method method) {
            this.method=method;
            methodNameWithParameters= YoumuReflectionUtil.generFullMethodName(method);
        }

        public Method getMethod() {
            return method;
        }


        public String getMethodNameWithParameters() {
            return methodNameWithParameters;
        }

        public boolean isSameMethod(Method method){
            return YoumuReflectionUtil.generFullMethodName(method).equals(getMethodNameWithParameters());
        }

        public boolean isSameMethod(String methodName,Class ...parameterTypes){
            return YoumuReflectionUtil.generFullMethodName(methodName,parameterTypes).equals(getMethodNameWithParameters());
        }
    }