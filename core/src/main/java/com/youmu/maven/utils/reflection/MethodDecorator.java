package com.youmu.maven.utils.reflection;

import com.youmu.maven.utils.reflection.utils.YoumuReflectionUtil;

import java.lang.reflect.Method;


public class MethodDecorator {
        private Method method;
        private String methodNameWithParameters;

        public MethodDecorator(Method method) {
            this.method=method;
            methodNameWithParameters= YoumuReflectionUtil.generFullMethodName(method);
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public String getMethodNameWithParameters() {
            return methodNameWithParameters;
        }

        public void setMethodNameWithParameters(String methodNameWithParameters) {
            this.methodNameWithParameters = methodNameWithParameters;
        }

        public boolean isSameMethod(Method method){
            return YoumuReflectionUtil.generFullMethodName(method).equals(getMethodNameWithParameters());
        }

        public boolean isSameMethod(String methodName,Class ...parameterTypes){
            return YoumuReflectionUtil.generFullMethodName(methodName,parameterTypes).equals(getMethodNameWithParameters());
        }
    }