package com.youmu.maven.utils.reflection;

import com.youmu.maven.utils.reflection.utils.YoumuReflectionUtil;

import java.lang.reflect.Method;


public class MethodDecorator {
        private final  Method method;
        private final  String methodNameWithParameters;
        private final Class[] parameterTypes;
        public MethodDecorator(Method method) {
            this.method=method;
            this.parameterTypes=method.getParameterTypes();
            methodNameWithParameters= YoumuReflectionUtil.generFullMethodName(method.getName(),this.parameterTypes);
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

        public Class[] getParameterTypes(){
            return this.parameterTypes;
        }
    }