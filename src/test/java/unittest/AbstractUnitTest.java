package unittest;
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;

import unittest.action.AbnormalTest;
import unittest.action.DataPrepare;
import unittest.action.NormalTest;

/**
 * @author enqing.teq
 * @version $Id: unittest.AbstractUnitTest, v0.1 2020年01月20日 上午11:15 enqing.teq Exp $
 */
public abstract class AbstractUnitTest implements DataPrepare {
    private Object object;

    private Method method;

    public AbstractUnitTest(Object object, Method method) {
        this.object = object;
        this.method = method;
        testCase();
    }

    public AbstractUnitTest(Object object, String methodName) {
        this.object = object;
        this.method = fetchMethod(object, methodName);
        testCase();
    }

    public AbstractUnitTest(Object object) {
        this.object = object;
        testCase();
    }

    private void testCase() {
        dataPrepare();
        if (this instanceof NormalTest) {
            ((NormalTest) this).normalTest();
        }
        if (this instanceof AbnormalTest) {
            ((AbnormalTest) this).abnormalTest();
        }
    }

    public Object normalCaseProcess(Object... args) {
        try {
            return doInvoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
            fail(failStr(args));
            return null;
        }
    }

    public Object normalCaseProcessPrintResult(Object... args) {
        Object object = normalCaseProcess(args);
        System.out.println(object);
        return object;
    }

    public void normalCaseProcessWithResult(Object expect, Object... args) {
        try {
            Object res = doInvoke(object, args);
            System.out.println(res);
            Assert.assertEquals(expect.toString(), res.toString());
        } catch (Throwable e) {
            e.printStackTrace();
            fail(failStr(args));
        }
    }

    public void exceptionWithMsg(String errorMsg, Object... args) {
        String failMsg = failStr(args);
        try {
            doInvoke(object, args);
            fail(failMsg);
        } catch (InvocationTargetException e) {
            Assert.assertEquals(errorMsg, e.getTargetException().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail(failMsg);
        }
    }

    public void exceptionAnyMsg(Object... args) {
        String failMsg = failStr(args);
        try {
            doInvoke(object, args);
            fail(failMsg);
        } catch (InvocationTargetException e) {
            Assert.assertNotEquals(failMsg, e.getTargetException().getMessage());
        } catch (Exception e) {
            fail(failMsg);
        }
    }

    private Object doInvoke(Object object, Object[] args) throws InvocationTargetException,
                                                          IllegalAccessException {
        if (args == null) {
            args = new Object[1];
            args[0] = null;
        }
        if (method == null) {
            Method localMethod = fetchMethod(object, args[0]);
            localMethod.setAccessible(true);
            Object[] newArgs = new Object[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            return localMethod.invoke(object, newArgs);
        }
        method.setAccessible(true);
        return method.invoke(object, args);
    }

    private String failStr(Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + (method != null ? method.getName() : "null") + "]"
                  + "test result is not expected :");
        if (args == null) {
            return sb.append("null").toString();
        }

        for (Object i : args) {
            sb.append("\n ").append(String.valueOf(i));
        }
        return sb.toString();
    }

    private Method fetchMethod(Object object, Object methodObject) {
        if (methodObject instanceof Method) {
            return (Method) methodObject;
        }
        Method[] methods = object.getClass().getDeclaredMethods();
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equals((String) methodObject)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new RuntimeException("no such method: " + methodObject);
        }
        return method;
    }

}
