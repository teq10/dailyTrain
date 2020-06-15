/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package unittest.testTemplate;

import unittest.AbstractUnitTest;
import unittest.action.AbnormalTest;
import unittest.action.NormalTest;

import java.lang.reflect.Method;

/**
 * @author enqing.teq
 * @version $Id: FullUnitTest, v0.1 2020年02月02日 下午4:16 enqing.teq Exp $
 */
public abstract class FullUnitTest extends AbstractUnitTest implements NormalTest, AbnormalTest {

    public FullUnitTest(Object object, Method method) {
        super(object, method);
    }
    public FullUnitTest(Object object, String method) {
        super(object, method);
    }
    public FullUnitTest(Object object) {
        super(object);
    }
}