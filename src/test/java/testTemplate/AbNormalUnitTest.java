/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package testTemplate;

import action.AbnormalTest;

import java.lang.reflect.Method;



/**
 * @author enqing.teq
 * @version $Id: AbNormalUnitTest, v0.1 2020年02月02日 下午4:16 enqing.teq Exp $
 */
public abstract class AbNormalUnitTest extends AbstractUnitTest implements AbnormalTest {

    public AbNormalUnitTest(Object object, Method method) {
        super(object, method);
    }

    public AbNormalUnitTest(Object object, String method) {
        super(object, method);
    }

    public AbNormalUnitTest(Object object) {
        super(object);
    }
}