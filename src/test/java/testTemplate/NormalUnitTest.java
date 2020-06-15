/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package testTemplate;

import action.NormalTest;

import java.lang.reflect.Method;



/**
 * @author enqing.teq
 * @version $Id: NormalUnitTest, v0.1 2020年02月02日 下午4:15 enqing.teq Exp $
 */
public abstract class NormalUnitTest extends AbstractUnitTest implements NormalTest {

    public NormalUnitTest(Object object, Method method) {
        super(object, method);
    }

    public NormalUnitTest(Object object, String method) {
        super(object, method);
    }
    public NormalUnitTest(Object object) {
        super(object);
    }

}