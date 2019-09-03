package com.mygdx.game.test;

import com.mygdx.game.Tools;

public class I18nTest {
    private Tools tools;

    public I18nTest () {
        tools=new Tools();
    }

    public void run() {
        tools.initI18nFR();
        test();
        tools.initI18nEN();
        test();
    }

    private void test() {
        resume("titre: "+tools.get("title"));
        resume("description: "+tools.get("description"));
        resume("hello: "+tools.get("hello"));
        resume("test: "+tools.format("test", "blabla"));
    }

    private void resume(String element) {
        Tools.debug("I18N", element);
    }
}
