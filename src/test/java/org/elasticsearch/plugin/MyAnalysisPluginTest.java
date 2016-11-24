package org.elasticsearch.plugin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lynn_lin on 2016/11/18.
 */
public class MyAnalysisPluginTest {
    private MyAnalysisPlugin analysisPinyinPlugin;

    @Before
    public void setUp() throws Exception {
        analysisPinyinPlugin = new MyAnalysisPlugin();
    }


    @Test
    public final void testName() {
        // when
        String name = analysisPinyinPlugin.name();

        // then
        assertEquals("Name must be equal to My Plugin", "My Plugin", name);
    }

    @Test
    public final void testDescription() {
        // when
        String description = analysisPinyinPlugin.description();

        // then
        assertEquals("description must be equal to This is a simple es plugin example.", "This is a simple es plugin example.", description);
    }
}
